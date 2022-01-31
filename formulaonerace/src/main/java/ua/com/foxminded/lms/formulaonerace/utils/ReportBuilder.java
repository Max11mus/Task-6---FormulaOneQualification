package ua.com.foxminded.lms.formulaonerace.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import ua.com.foxminded.lms.formulaonerace.entities.Lap;
import ua.com.foxminded.lms.formulaonerace.entities.Racer;
import ua.com.foxminded.lms.formulaonerace.qualificationreport.QualificationReport;

public class ReportBuilder {

	public QualificationReport buildReport(HashMap<Racer, Lap> laps) {

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

		QualificationReport report = new QualificationReport(columnNames, columnsWidth, laps.size());

		List<Lap> lapsList = laps.values().stream().sorted((lap1, lap2) -> lap1.compareTo(lap2))
				.collect(Collectors.toList());
		
		int counter = 1;
		int row = 0;
		for (Iterator<Lap> iterator = lapsList.iterator(); iterator.hasNext();) {
			Lap lap =  iterator.next();
			report.setCell(row, 0, Integer.toString(counter));
			report.setCell(row, 1, lap.getRacer().getName());
			report.setCell(row, 2, lap.getRacer().getTeam());
			report.setCell(row, 3, lap.getLapTime().toString());
			counter++;
			row++;
		}
		
		
		return report;
	}
	
}