package ua.com.foxminded.lms.formulaonerace;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

import ua.com.foxminded.lms.formulaonerace.entities.Lap;
import ua.com.foxminded.lms.formulaonerace.entities.Racer;
import ua.com.foxminded.lms.formulaonerace.qualificationreport.QualificationReport;
import ua.com.foxminded.lms.formulaonerace.utils.FileLoader;
import ua.com.foxminded.lms.formulaonerace.utils.Parser;
import ua.com.foxminded.lms.formulaonerace.utils.ReportBuilder;

public class App {

	public static void main(String[] args) throws IOException {

		URL racersUrl = ClassLoader.getSystemResource("abbreviations.txt");
		URL startLogUrl = ClassLoader.getSystemResource("start.log");
		URL endLogUrl = ClassLoader.getSystemResource("end.log");

		FileLoader fileLoader = new FileLoader();
		List<String> racersStream = fileLoader.load(racersUrl);
		List<String> startLogStream = fileLoader.load(startLogUrl);
		List<String> endLogStream = fileLoader.load(endLogUrl);
		
		Parser parser = new Parser();
		HashMap <Racer, Lap> parseResult = parser.parseQualificationResults(racersStream, startLogStream, endLogStream);
		
		ReportBuilder reportBuilder = new ReportBuilder();
		QualificationReport report = reportBuilder.buildReport(parseResult);

		System.out.println(report.outputReport());

	}

}
