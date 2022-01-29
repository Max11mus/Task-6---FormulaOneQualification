package ua.com.foxminded.lms.formulaonerace.qualificationreport;

import java.util.ArrayList;

public class QualificationReport {
	public static final String END_OF_LINE = System.getProperty("line.separator");
	private ArrayList<String> columnsNames;
	private ArrayList<Integer> columnsWidth;
	private ArrayList<ArrayList<String>> data;
	private int rowsCount;
	private int columnsCount;

	public QualificationReport(ArrayList<String> columnsNames, ArrayList<Integer> columnsWidth, int rowsCount) {
		this.columnsNames = columnsNames;
		this.columnsWidth = columnsWidth;

		columnsCount = columnsNames.size();

		data = new ArrayList<ArrayList<String>>();
		this.rowsCount = rowsCount;
		for (int i = 0; i < rowsCount; i++) {
			data.add(new ArrayList<String>());
			for (int k = 0; k < columnsCount; k++) {
				data.get(i).add("");
			}
		}
	}

	public void clearData() {
		if (rowsCount != 0) {
			for (int i = 0; i < rowsCount; i++) {
				data.get(i).clear();
			}
		}
		data.clear();
		columnsNames.clear();
		rowsCount = 0;
		columnsCount = 0;
	}

	public String getCell(int row, int column) { // row - (from 0 to rowsCount-1), row - (from 0 to columnsCount-1)
		return data.get(row).get(column);
	}

	public void setCell(int row, int column, String value) { // row - (from 0 to rowsCount-1), row - (from 0 to
		// columnsCount-1)
		data.get(row).remove(column);
		data.get(row).add(column, value);
	}

	public String buildReport() {
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

}
