package ua.com.foxminded.lms.formulaonerace.utils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import ua.com.foxminded.lms.formulaonerace.entities.Lap;
import ua.com.foxminded.lms.formulaonerace.entities.Racer;

public class Parser {

	public HashMap<Racer, Lap> parseQualificationResults(List<String> abbreviations, List<String> startLog, List<String> endLog) {

		HashMap<String, Racer> racers = abbreviations.stream()
				.collect(Collectors.toMap(key -> key.split("_")[0],
						value -> new Racer(value.split("_")[0], value.split("_")[1], value.split("_")[2]),
						(v1, v2) -> v2, HashMap::new));

		HashMap<Racer, Lap> laps = startLog.stream()
				.collect(Collectors.toMap(line -> racers.get(parseAbbrevation(line)),
						line -> new Lap(racers.get(parseAbbrevation(line)), parseTime(line), parseTime(line)),
						(v1, v2) -> v2, HashMap::new));

		for (Iterator<String> iterator = endLog.iterator(); iterator.hasNext();) {
			String line = iterator.next();
			String abbrevation = parseAbbrevation(line);
			Instant dataStamp = parseTime(line);
			if (racers.containsKey(abbrevation)) {
				laps.get(racers.get(abbrevation)).setEndTime(dataStamp);
			}
		}

		return laps;
	}
	
	private String parseAbbrevation(String input) {
		return input.substring(0, 3);
	}
	
	private Instant parseTime(String input) {
		return Instant.parse(input.substring(3).replace("_", "T") + "Z");
	}
}
