package ua.com.foxminded.lms.formulaonerace.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.lms.formulaonerace.entities.Lap;
import ua.com.foxminded.lms.formulaonerace.entities.Racer;
import ua.com.foxminded.lms.formulaonerace.qualificationreport.QualificationReport;

class QualificationReportTest {
static QualificationReport report;
public static final String END_OF_LINE = System.getProperty("line.separator");

	@BeforeAll
	static void qualificationReportTestsSetUp() {
		report = new QualificationReport(); 	 	
	}
	
	@Test	
	void buildReport_raise_IllegalArgumentException_WhenArgumentIsNull() {
		assertThrows(IllegalArgumentException.class, () -> report.buildReport(null),
				"Must throw IllegalArgumentException exception when input null.");
	}

	@Test	
	void buildReport_raise_IllegalArgumentException_WhenArgumentIsEmpty() {
		assertThrows(IllegalArgumentException.class, () -> report.buildReport(new HashMap<Racer, Lap>()) ,
				"Must throw MalformedURLException exception when when url is invalid.");
	}
	
		@Test
	void buildReport_and_OutputReport_DataMustBeSameAsSampleData() {
				HashMap<Racer, Lap> input = new HashMap<Racer, Lap>();
				
				ArrayList<Racer> racer = new ArrayList<Racer>();
				racer.add(new Racer("CSR", "Carlos Sainz", "RENAULT"));
				racer.add(new Racer("VBM", "Valtteri Bottas", "MERCEDES"));
				racer.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER"));
				racer.add(new Racer("SVF", "Sebastian Vettel", "FERRARI"));
				racer.add(new Racer("KMH", "Kevin Magnussen", "HAAS FERRARI"));
				racer.add(new Racer("SVM", "Stoffel Vandoorne", "MCLAREN RENAULT"));
				racer.add(new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI"));
				racer.add(new Racer("SPF", "Sergio Perez", "FORCE INDIA MERCEDES"));
				racer.add(new Racer("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA"));
				racer.add(new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES"));
				racer.add(new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT"));
				racer.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES"));
				racer.add(new Racer("RGH", "Romain Grosjean", "HAAS FERRARI"));
				racer.add(new Racer("NHR", "Nico Hulkenberg", "RENAULT"));
				racer.add(new Racer("SSW", "Sergey Sirotkin", "WILLIAMS MERCEDES"));
				racer.add(new Racer("PGS", "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA"));
				racer.add(new Racer("EOF", "Esteban Ocon", "FORCE INDIA MERCEDES"));
				racer.add(new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI"));
				racer.add(new Racer("KRF", "Kimi Raikkonen", "FERRARI"));
				
				int count=0;
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:03:15.145Z"),
						Instant.parse("2018-05-24T12:04:28.095Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:00:00Z"),
						Instant.parse("2018-05-24T12:01:12.434Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:14:12.054Z"),
						Instant.parse("2018-05-24T12:15:24.067Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:02:58.917Z"),
						Instant.parse("2018-05-24T12:04:03.332Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:02:51.003Z"),
						Instant.parse("2018-05-24T12:04:04.396Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:18:37.735Z"),
						Instant.parse("2018-05-24T12:19:50.198Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:09:41.921Z"),
						Instant.parse("2018-05-24T12:10:54.750Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:12:01.035Z"),
						Instant.parse("2018-05-24T12:13:13.883Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:14:51.985Z"),
						Instant.parse("2018-05-24T12:16:05.164Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:06:13.511Z"),
						Instant.parse("2018-05-24T12:07:26.834Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:13:04.512Z"),
						Instant.parse("2018-05-24T12:14:17.169Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:18:20.125Z"),
						Instant.parse("2018-05-24T12:19:32.585Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:05:14.511Z"),
						Instant.parse("2018-05-24T12:06:27.441Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:02:49.914Z"),
						Instant.parse("2018-05-24T12:04:02.979Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:16:11.648Z"),
						Instant.parse("2018-05-24T12:17:24.354Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:07:23.645Z"),
						Instant.parse("2018-05-24T12:08:36.586Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:17:58.810Z"),
						Instant.parse("2018-05-24T12:19:11.838Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:04:45.513Z"),
						Instant.parse("2018-05-24T12:05:58.778Z")));
				input.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:03:01.250Z"),
						Instant.parse("2018-05-24T12:04:13.889Z")));

				String expected, actual;
				expected = "|№   |Racer                                  |Team                                   "
						+ "|LapTime            " + END_OF_LINE
						+ "---------------------------------------------------------------------------------------"
						+ "---------------------------------------------------------------" + END_OF_LINE
						+ "|1   |Sebastian Vettel                       |FERRARI                                "
						+ "|PT1M4.415S         " + END_OF_LINE
						+ "|2   |Daniel Ricciardo                       |RED BULL RACING TAG HEUER              "
						+ "|PT1M12.013S        " + END_OF_LINE
						+ "|3   |Valtteri Bottas                        |MERCEDES                               "
						+ "|PT1M12.434S        " + END_OF_LINE
						+ "|4   |Lewis Hamilton                         |MERCEDES                               "
						+ "|PT1M12.46S         " + END_OF_LINE
						+ "|5   |Stoffel Vandoorne                      |MCLAREN RENAULT                        "
						+ "|PT1M12.463S        " + END_OF_LINE
						+ "|6   |Kimi Raikkonen                         |FERRARI                                "
						+ "|PT1M12.639S        " + END_OF_LINE
						+ "|7   |Fernando Alonso                        |MCLAREN RENAULT                        "
						+ "|PT1M12.657S        " + END_OF_LINE
						+ "|8   |Sergey Sirotkin                        |WILLIAMS MERCEDES                      "
						+ "|PT1M12.706S        " + END_OF_LINE
						+ "|9   |Charles Leclerc                        |SAUBER FERRARI                         "
						+ "|PT1M12.829S        " + END_OF_LINE
						+ "|10  |Sergio Perez                           |FORCE INDIA MERCEDES                   "
						+ "|PT1M12.848S        " + END_OF_LINE
						+ "|11  |Romain Grosjean                        |HAAS FERRARI                           "
						+ "|PT1M12.93S         " + END_OF_LINE
						+ "|12  |Pierre Gasly                           |SCUDERIA TORO ROSSO HONDA              "
						+ "|PT1M12.941S        " + END_OF_LINE
						+ "|13  |Carlos Sainz                           |RENAULT                                "
						+ "|PT1M12.95S         " + END_OF_LINE
						+ "|14  |Esteban Ocon                           |FORCE INDIA MERCEDES                   "
						+ "|PT1M13.028S        " + END_OF_LINE
						+ "|15  |Nico Hulkenberg                        |RENAULT                                "
						+ "|PT1M13.065S        " + END_OF_LINE
						+ "--------------------------------------------------------------------------------------"
						+ "----------------------------------------------------------------" + END_OF_LINE
						+ "|16  |Brendon Hartley                        |SCUDERIA TORO ROSSO HONDA              "
						+ "|PT1M13.179S        " + END_OF_LINE
						+ "|17  |Marcus Ericsson                        |SAUBER FERRARI                         "
						+ "|PT1M13.265S        " + END_OF_LINE
						+ "|18  |Lance Stroll                           |WILLIAMS MERCEDES                      "
						+ "|PT1M13.323S        " + END_OF_LINE;

				report.buildReport(input);
				actual = report.outputReport();

				assertEquals(expected, actual, "The  data must be the same as the sample data.");

	}

}
