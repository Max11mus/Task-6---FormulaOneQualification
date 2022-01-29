package ua.com.foxminded.lms.formulaonerace.parsers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.stream.Stream;

public class FileLoader {

	public Stream<String> loadRacersDataFromResourse (String fileName) {
		InputStream abbreviationsFile = ClassLoader.getSystemResourceAsStream(fileName);
		InputStreamReader abbreviationsStreamReader = new InputStreamReader(abbreviationsFile);
	    BufferedReader abbreviationsReader = new BufferedReader(abbreviationsStreamReader);
	    Stream<String> abbreviationsLines = abbreviationsReader.lines();
	    
	    return abbreviationsLines;
	}
	
	public ArrayList <Stream<String>> loadLapsDataFromResource(String startLogFileName, String endLogFileName) {
		InputStream startLog = ClassLoader.getSystemResourceAsStream(startLogFileName);
		InputStreamReader startStreamReader = new InputStreamReader(startLog);
	    BufferedReader startReader = new BufferedReader(startStreamReader);
	    Stream<String> startLines = startReader.lines();
	    
	    InputStream endLog = ClassLoader.getSystemResourceAsStream(endLogFileName);
		InputStreamReader endStreamReader = new InputStreamReader(endLog);
	    BufferedReader endReader = new BufferedReader(endStreamReader);
	    Stream<String> endLines = endReader.lines();
	    
	    ArrayList <Stream<String>> result = new ArrayList <Stream<String>>();
	    result.add(startLines);
	    result.add(endLines);
	    
	    return result;
	}
	
}
