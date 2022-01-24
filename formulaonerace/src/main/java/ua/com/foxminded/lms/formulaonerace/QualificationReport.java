package ua.com.foxminded.lms.formulaonerace;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class QualificationReport {
	public static final String END_OF_LINE = System.getProperty("line.separator");
	private LapsHashMap lapsHashMap;
	private String report;
	int counter;

	public QualificationReport() {
		lapsHashMap = new LapsHashMap();
	} 

	public void clearData() {
		lapsHashMap.clearData();
	}

	public void loadRacersDataFromResourse (String fileName) {
		InputStream abbreviationsFile = ClassLoader.getSystemResourceAsStream(fileName);
		InputStreamReader abbreviationsStreamReader = new InputStreamReader(abbreviationsFile);
	    BufferedReader abbreviationsReader = new BufferedReader(abbreviationsStreamReader);
	    Stream<String> abbreviationsLines = abbreviationsReader.lines();
	    
	    lapsHashMap.loadRacersDataFromStream(abbreviationsLines);
	}
	
	public void loadLapsDataFromResource(String startLogFileName, String endLogFileName) {
		InputStream endLog = ClassLoader.getSystemResourceAsStream(startLogFileName);
		InputStreamReader endStreamReader = new InputStreamReader(endLog);
	    BufferedReader endReader = new BufferedReader(endStreamReader);
	    Stream<String> endLines = endReader.lines();
	    
	    InputStream startLog = ClassLoader.getSystemResourceAsStream(endLogFileName);
		InputStreamReader startStreamReader = new InputStreamReader(startLog);
	    BufferedReader startReader = new BufferedReader(startStreamReader);
	    Stream<String> startLines = startReader.lines();
	    
	    lapsHashMap.loadLapsDataFromStream(startLines, endLines);
	    
	}
	
	public String getReportAscendingLapTimeSplitAfterFirstFifteen() {
	    report="";
	    counter=1;
	    
	    Stream<Lap> lapsDataLines = lapsHashMap.toStream();
		lapsDataLines.sorted((lap1, lap2) -> lap2.compareTo(lap1))
				.forEach(lap -> {
					reportConcatString(alignRightWithSpace(Integer.toString(counter) + ". " , 5) + "|");
					reportConcatString(alignRightWithSpace(lap.getRacer().getName(), 40) + "|");
					reportConcatString(alignRightWithSpace(lap.getRacer().getTeam(),40) + "|");
					reportConcatString(lap.getLapTime().toString() + END_OF_LINE);
					if (counter==15) {
						reportConcatString( multipleHyphens(150) + END_OF_LINE);
					}
					incrementCounter();
		});
	    
		return report;
	}

	private void reportConcatString(String string) {
		report+=string;
		
	}
	
	private void incrementCounter() {
		counter++;
	
	}

	private String alignLeftWithSpace(String input, int length) {
		if ((length < 0) || (toString().length() < length)) {
			return input;
		} else {
			return String.format("%1$" + length + "s", input);
		}
	}

	private String alignRightWithSpace(String input, int length) {
		if ((length < 0) || (toString().length() < length)) {
			return input;
		} else {
			return String.format("%1$-" + length + "s", input);
		}
	}

	private String multipleHyphens(int length) {
		if (length > 0) {
			return String.format("%1$" + length + "s", "-").replace(" ", "-");
		} else {
			return new String("");
		}
	}
	
}
