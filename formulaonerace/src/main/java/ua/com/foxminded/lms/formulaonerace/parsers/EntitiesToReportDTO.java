package ua.com.foxminded.lms.formulaonerace.parsers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;


import ua.com.foxminded.lms.formulaonerace.entities.Lap;
import ua.com.foxminded.lms.formulaonerace.entities.Racer;
import ua.com.foxminded.lms.formulaonerace.qualificationreport.QualificationReport;

public class EntitiesToReportDTO {
	private QualificationReport report;

	public void parseEntitiesToReportDTO  (HashMap<String, Racer> racers, HashMap<Racer, Lap> laps)
	{
		ArrayList<String> columnNames = new ArrayList<String>();
		ArrayList<Integer> columnsWidth = new ArrayList<Integer>();
		columnNames.add("№");
		columnsWidth.add(5);
		columnNames.add("Racer");
		columnsWidth.add(40);
		columnNames.add("Team");
		columnsWidth.add(40);
		columnNames.add("LapTime");
		columnsWidth.add(20);
		
		report= new QualificationReport(columnNames, columnsWidth, laps.size());				
		
		AtomicInteger  counter= new AtomicInteger(1);
		AtomicInteger  row= new AtomicInteger(0);
		
		
		laps.values()
			.stream()
			.sorted((lap1, lap2) -> lap1.compareTo(lap2))
			.forEach(lap -> {
				report.setCell(row.get(), 0, Integer.toString(counter.get()));
				report.setCell(row.get(), 1, lap.getRacer().getName());
				report.setCell(row.get(), 2, lap.getRacer().getTeam());
				report.setCell(row.get(), 3, lap.getLapTime().toString());
				counter.set(counter.get() + 1);
				row.set(row.get() + 1);
		});
	
		
}

	public QualificationReport getReport() {
		return report;
	}

}