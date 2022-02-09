package ua.com.foxminded.lms.formulaonerace.utils;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import ua.com.foxminded.lms.formulaonerace.entities.Lap;
import ua.com.foxminded.lms.formulaonerace.entities.Racer;

public class Parser {

	public HashMap<Racer, Lap> parseQualificationResults(List<String> abbreviations, List<String> startLog,
			List<String> endLog) {
		if ((abbreviations == null) || (startLog == null) || (endLog == null) ) {
			throw new IllegalArgumentException("ERROR: Null Pointer Arguments.");
		}
		
		if ((abbreviations.size() == 0) || (startLog.size() == 0) || (endLog.size() == 0)) {
			throw new IllegalArgumentException("ERROR: Empty Arguments.");
		}
		
		HashMap<String, Racer> racers;
		try {
			racers = new HashMap <> (abbreviations.stream()
					.collect(Collectors.toMap(key -> parseAbbrevationFromAbbrevationList(key),
							value -> parseRacerFromAbbrevationList(value))));
		} catch (IllegalStateException e) {
			e.printStackTrace();
			System.err.println();
			throw new IllegalArgumentException("ERROR: Duplication abbrevations in abbrevations list.");
		}
		
		if (startLog.size() != endLog.size()) {
			throw new IllegalArgumentException("ERROR: startLog and endLog must contains same numbers of line.");
		}

		ArrayList<String> startLogCopy = (ArrayList<String>) startLog
				.stream()
				.collect(Collectors.toList());
		ArrayList<String> endLogCopy = (ArrayList<String>) endLog
				.stream()
				.collect(Collectors.toList());
		startLogCopy.sort(String::compareTo);
		endLogCopy.sort(String::compareTo);
		
		ArrayList<Pair<String, String>> lapsLogs = Pair.fromArrayListOfParams(startLogCopy, endLogCopy);
		
		Predicate<Pair<String, String>> equalsAbbrevaitions = p -> 
			parseAbbrevationFromLapsLogs(p.getFirst()).equals(parseAbbrevationFromLapsLogs(p.getSecond()));		
		lapsLogs.stream().filter(equalsAbbrevaitions.negate()).findFirst()
				.ifPresent(	p -> {
					throw new IllegalArgumentException("ERROR: Mismatch Abbrevation " + p.getFirst() + " "
						+ p.getSecond()+ " !!!.");
					});
					
			HashMap<Racer, Lap> laps; 

			Function<Pair<String, String>, Racer> getRacerFromRacers = p -> Optional
					.ofNullable(racers.get(parseAbbrevationFromLapsLogs(p.getFirst())))
					.orElseThrow(() -> new IllegalArgumentException("ERROR: " + p.getFirst() + " " + p.getSecond()
							+ " Abbrevation from Laps Log doesn't exist in " + "Abbrevation List !!!."));

			try {
				laps =  new HashMap <> (lapsLogs.stream()
						.map(pair -> new Lap(getRacerFromRacers.apply(pair), parseTime(pair.getFirst()),
								parseTime(pair.getSecond())))
						.collect(Collectors.toMap( key -> key.getRacer(), value -> value)));
			} catch (IllegalStateException e) {
				e.printStackTrace();
				System.err.println();
				throw new IllegalArgumentException("ERROR: Duplication abbrevations in Laps Logs.");
			}

		return laps;
	}
	
	private String parseAbbrevationFromLapsLogs(String input) {
		String abrevation = input.substring(0, 3);
		
		IntPredicate isUperCaseletter =  ch -> Character.isUpperCase(ch);
		if ( abrevation.chars().anyMatch(isUperCaseletter.negate()::test)){
			throw new IllegalArgumentException("ERROR: "+ abrevation + 
					" in  Laps Logs - Abbrevation Tag must use CAPITAL letters." );
		}
		
		return abrevation;
	}

	private String parseAbbrevationFromAbbrevationList(String input) {
		String abbrevation = input.split("_")[0];

		if (abbrevation.length() != 3) {
			throw new IllegalArgumentException(
					"ERROR: " + abbrevation + " in Abbrevation List - Abbrevation Tag must have size 3 chars.");
		}

		IntPredicate isUperCaseletter = ch -> Character.isUpperCase(ch);
		if (abbrevation.chars().anyMatch(isUperCaseletter.negate()::test)) {
			throw new IllegalArgumentException(
					"ERROR: " + abbrevation + " in Abbrevation List - Abbrevation Tag must use CAPITAL letters.");
		}

		return abbrevation;

	}
	
	private Racer parseRacerFromAbbrevationList(String input) {
		String[] parseResult = input.split("_");

		if (parseResult.length != 3) {
			throw new IllegalArgumentException("ERROR: "+ input + 
					"doesn't fit the format: ABR_RacerName_TeamName");
		}
		
		String abbrevation = parseResult[0];
		String racerName = parseResult[1];
		String teamName = parseResult[2];
		
		if (abbrevation.length() != 3) {
			throw new IllegalArgumentException("ERROR: "+ abbrevation + 
					" in Abbrevation List - Abbrevation Tag must have size 3 chars." );
		}
		
		IntPredicate isUperCaseletter =  ch -> Character.isUpperCase(ch);
		if ( abbrevation.chars().anyMatch(isUperCaseletter.negate()::test)){
			throw new IllegalArgumentException("ERROR: "+ abbrevation + 
					" in Abbrevation List - Abbrevation Tag must use CAPITAL letters." );
		}
				
		return new Racer(abbrevation, racerName, teamName);
	}
	
	private Instant parseTime(String input) {
		Instant time;
		try {
			time = Instant.parse(input.substring(3).replace("_", "T") + "Z");
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			System.err.println();
			throw new IllegalArgumentException("ERROR: time "+ input + 
					" in Laps Logs - doesn't fit format: YYYY-MM-DD_HH:MM:SS.MSS ." );
		}
		
		return time;
	}
}
