package ua.com.foxminded.lms.formulaonerace;

import java.util.ArrayList;
import java.util.stream.Stream;

import ua.com.foxminded.lms.formulaonerace.parsers.EntitiesToReportDTO;
import ua.com.foxminded.lms.formulaonerace.parsers.FileLoader;
import ua.com.foxminded.lms.formulaonerace.parsers.StreamsToEntitiesDTO;
import ua.com.foxminded.lms.formulaonerace.qualificationreport.QualificationReport;

public class App {

	public static void main(String[] args) {
		
		FileLoader fileLoader = new FileLoader ();    
		Stream<String> racers = fileLoader.loadRacersDataFromResourse("abbreviations.txt");
		ArrayList<Stream<String>> lapsLogs = fileLoader.loadLapsDataFromResource("start.log", "end.log");
		
		StreamsToEntitiesDTO streamsToEntitiesDTO = new StreamsToEntitiesDTO() ;
		streamsToEntitiesDTO.parseToEntities(racers, lapsLogs.get(0), lapsLogs.get(1));
				
		
		EntitiesToReportDTO entitiesToReportDTO = new EntitiesToReportDTO();
		entitiesToReportDTO.parseEntitiesToReportDTO(streamsToEntitiesDTO.getRacers(), streamsToEntitiesDTO.getLaps());
		
		QualificationReport report = entitiesToReportDTO.getReport();
		
		System.out.println(report.buildReport());
						
	}

}
