package ua.com.foxminded.lms.formulaonerace.qualificationreport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import ua.com.foxminded.lms.formulaonerace.entities.Lap;
import ua.com.foxminded.lms.formulaonerace.entities.Racer;

public class QualificationReport {
	public static final String END_OF_LINE = System.getProperty("line.separator");
	private ArrayList<String> columnsNames;
	private ArrayList<Integer> columnsWidth;
	private ArrayList<ArrayList<String>> data;
	private int rowsCount;
	private int columnsCount;

	public QualificationReport() {
		super();
		columnsNames = new ArrayList<String>();
		columnsWidth = new ArrayList<Integer>();
	}

	public void buildReport(HashMap<Racer, Lap> laps) {
		if (laps == null)  {
			throw new IllegalArgumentException("ERROR: Null Pointer Arguments.");
		}
		
		if (laps.size() == 0) {
			throw new IllegalArgumentException("ERROR: Empty Arguments.");
		}
		
		
		
		columnsNames.clear();
		columnsWidth.clear();
		columnsNames.add("№");
		columnsWidth.add(5);
		columnsNames.add("Racer");
		columnsWidth.add(40);
		columnsNames.add("Team");
		columnsWidth.add(40);
		columnsNames.add("LapTime");
		columnsWidth.add(20);

		columnsCount = columnsNames.size();

		data = new ArrayList<ArrayList<String>>();
		this.rowsCount = laps.size();
		for (int i = 0; i < rowsCount; i++) {
			data.add(new ArrayList<String>());
			for (int k = 0; k < columnsCount; k++) {
				data.get(i).add("");
			}
		}

		List<Lap> lapsList = laps.values().stream().sorted((lap1, lap2) -> lap1.compareTo(lap2))
				.collect(Collectors.toList());

		int counter = 1;
		int row = 0;
		for (Iterator<Lap> iterator = lapsList.iterator(); iterator.hasNext();) {
			Lap lap = iterator.next();
			setCell(row, 0, Integer.toString(counter));
			setCell(row, 1, lap.getRacer().getName());
			setCell(row, 2, lap.getRacer().getTeam());
			setCell(row, 3, lap.getLapTime().toString());
			counter++;
			row++;
		}
	}

	public String outputReport() {
		String report = "";
		for (int i = 0; i < columnsCount; i++) {
			report += alignRightWithSpace("|" + columnsNames.get(i), columnsWidth.get(i));
		}

		report += END_OF_LINE + multipleHyphens(150) + END_OF_LINE;

		for (int i = 0; i < rowsCount - 1; i++) {
			if (i == 15) {
				report += multipleHyphens(150) + END_OF_LINE;
			}
			for (int k = 0; k < columnsCount; k++) {
				report += alignRightWithSpace("|" + getCell(i, k), columnsWidth.get(k));
			}
			report += END_OF_LINE;

		}

		return report;
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

	private String getCell(int row, int column) { // row - (from 0 to rowsCount-1), row - (from 0 to columnsCount-1)
		return data.get(row).get(column);
	}

	private void setCell(int row, int column, String value) { // row - (from 0 to rowsCount-1), row - (from 0 to
		// columnsCount-1)
		data.get(row).remove(column);
		data.get(row).add(column, value);
	}

}
