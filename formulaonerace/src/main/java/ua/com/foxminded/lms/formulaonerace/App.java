package ua.com.foxminded.lms.formulaonerace;

public class App {

	public static void main(String[] args) {
		QualificationReport report = new QualificationReport();    
		
		report.clearData();
		report.loadRacersDataFromResourse("abbreviations.txt");
		report.loadLapsDataFromResource("start.log", "end.log");
		    
		System.out.println(report.getReportAscendingLapTimeSplitAfterFirstFifteen());
						
	}

}
