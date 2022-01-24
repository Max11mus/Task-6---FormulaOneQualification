package ua.com.foxminded.lms.formulaonerace;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) {
		InputStream abbreviationsFile = ClassLoader.getSystemResourceAsStream("abbreviations.txt");
		InputStreamReader abbreviationsStreamReader = new InputStreamReader(abbreviationsFile);
	    BufferedReader abbreviationsReader = new BufferedReader(abbreviationsStreamReader);
	    Stream<String> abbreviationsLines = abbreviationsReader.lines();
	    
	    InputStream endLog = ClassLoader.getSystemResourceAsStream("end.log");
		InputStreamReader endStreamReader = new InputStreamReader(endLog);
	    BufferedReader endReader = new BufferedReader(endStreamReader);
	    Stream<String> endLines = endReader.lines();
	    
	    InputStream startLog = ClassLoader.getSystemResourceAsStream("start.log");
		InputStreamReader startStreamReader = new InputStreamReader(startLog);
	    BufferedReader startReader = new BufferedReader(startStreamReader);
	    Stream<String> startLines = startReader.lines();
		
	    RacersSet racerList = new RacersSet();
	    racerList.loadListFromStream(abbreviationsLines);
	    
		
								
							
	}

}
