package ua.com.foxminded.lms.formulaonerace.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.lms.formulaonerace.entities.Lap;
import ua.com.foxminded.lms.formulaonerace.entities.Racer;
import ua.com.foxminded.lms.formulaonerace.utils.Parser;

class ParserTest {
	static Parser parser;
	static ArrayList<String> abbreviations, startLog, endLog;
	static int  randomIndex1 = 8;
	static int  randomIndex2 =18;
	static int  randomIndex3 = 2;
	

		
	@BeforeEach
	void setUpBeforeTest() throws Exception {
		parser = new Parser();

		abbreviations = (ArrayList<String>) Stream
				.of("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
						"SVF_Sebastian Vettel_FERRARI",
						"LHM_Lewis Hamilton_MERCEDES",
						"KRF_Kimi Raikkonen_FERRARI",
						"VBM_Valtteri Bottas_MERCEDES",
						"EOF_Esteban Ocon_FORCE INDIA MERCEDES",
						"FAM_Fernando Alonso_MCLAREN RENAULT",
						"CSR_Carlos Sainz_RENAULT",
						"SPF_Sergio Perez_FORCE INDIA MERCEDES",
						"PGS_Pierre Gasly_SCUDERIA TORO ROSSO HONDA",
						"NHR_Nico Hulkenberg_RENAULT",
						"SVM_Stoffel Vandoorne_MCLAREN RENAULT",
						"SSW_Sergey Sirotkin_WILLIAMS MERCEDES",
						"CLS_Charles Leclerc_SAUBER FERRARI",
						"RGH_Romain Grosjean_HAAS FERRARI",
						"BHS_Brendon Hartley_SCUDERIA TORO ROSSO HONDA",
						"MES_Marcus Ericsson_SAUBER FERRARI",
						"LSW_Lance Stroll_WILLIAMS MERCEDES",
						"KMH_Kevin Magnussen_HAAS FERRARI")
				.collect(Collectors.toList());

		startLog = (ArrayList<String>) Stream
				.of("SVF2018-05-24_12:02:58.917",
						"NHR2018-05-24_12:02:49.914",
						"FAM2018-05-24_12:13:04.512",
						"KRF2018-05-24_12:03:01.250",
						"SVM2018-05-24_12:18:37.735",
						"MES2018-05-24_12:04:45.513",
						"LSW2018-05-24_12:06:13.511",
						"BHS2018-05-24_12:14:51.985",
						"EOF2018-05-24_12:17:58.810",
						"RGH2018-05-24_12:05:14.511",
						"SSW2018-05-24_12:16:11.648",
						"KMH2018-05-24_12:02:51.003",
						"PGS2018-05-24_12:07:23.645",
						"CSR2018-05-24_12:03:15.145",
						"SPF2018-05-24_12:12:01.035",
						"DRR2018-05-24_12:14:12.054",
						"LHM2018-05-24_12:18:20.125",
						"CLS2018-05-24_12:09:41.921",
						"VBM2018-05-24_12:00:00.000")
				.collect(Collectors.toList());

		endLog = (ArrayList<String>) Stream
				.of("MES2018-05-24_12:05:58.778",
						"RGH2018-05-24_12:06:27.441",
						"SPF2018-05-24_12:13:13.883",
						"LSW2018-05-24_12:07:26.834",
						"DRR2018-05-24_12:15:24.067",
						"NHR2018-05-24_12:04:02.979",
						"CSR2018-05-24_12:04:28.095",
						"KMH2018-05-24_12:04:04.396",
						"BHS2018-05-24_12:16:05.164",
						"SVM2018-05-24_12:19:50.198",
						"KRF2018-05-24_12:04:13.889",
						"VBM2018-05-24_12:01:12.434",
						"SVF2018-05-24_12:04:03.332",
						"EOF2018-05-24_12:19:11.838",
						"PGS2018-05-24_12:08:36.586",
						"SSW2018-05-24_12:17:24.354",
						"FAM2018-05-24_12:14:17.169",
						"CLS2018-05-24_12:10:54.750",
						"LHM2018-05-24_12:19:32.585")
				.collect(Collectors.toList());
	}

	@AfterEach
	void setUpAfterTest() throws Exception {
		abbreviations.clear();
		startLog.clear();
		endLog.clear();
	}

	@Test
	void raise_IllegalArgumentException_WhenAtLeastOneArgumentIsNull() {
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(null, null, null),
				"Must throw IllegalArgumentException exception when input null.");
		thrown.printStackTrace();
		System.err.println();
	}

	@Test
	void raise_IllegalArgumentException_WhenAtLeastOneArgumentIsEmpty() {
		IllegalArgumentException thrown = assertThrows(
				IllegalArgumentException.class, () -> parser.parseQualificationResults(new ArrayList<String>(),
						new ArrayList<String>(), new ArrayList<String>()),
				"Must throw IllegalArgumentException exception when input Empty.");
		thrown.printStackTrace();
		System.err.println();
	}

	@Test
	void raiseIllegalArgumentException_WhenAtLeastOneLineFromAbbrevationsInputFileDoesntFitTheFormat() {
		abbreviations.remove(randomIndex1);
		abbreviations.add(randomIndex1, "hokerhp[ke[hke[hke[rk[jkekj[ekr[jk"); // line that
																											// not fit
																											// the
																											// format

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"Must throw IllegalArgumentException when at least one line from abrevation input file doesnt fit the"
						+ " format: ABR_RacerName_TeamName.");
		thrown.printStackTrace();
		System.err.println();
	}

	@Test
	void raiseIllegalArgumentException_WhenAbbrevationFromAbbrevationsListIsNotInUpperCase() {
		abbreviations.remove(randomIndex1);
		abbreviations.add(randomIndex1, "SpF_Sergio Perez_FORCE INDIA MERCEDES");
				// SpF - abbrevation not in UpperCase

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"Abbrevation Tag must use CAPITAL letters.");
		thrown.printStackTrace();
		System.err.println();
	}

	@Test
	void raiseIllegalArgumentException_WhenSizeOfAbbrevationFromAbbrevationsLisIstNotEqualThreeChars() {

		abbreviations.remove(randomIndex1);
		abbreviations.add(randomIndex1, 
				"SPFF_Sergio Perez_FORCE INDIA MERCEDES"); // SPFF - size not equal 3 chars

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"Abbrevation Tag must have size - 3 chars.");
		thrown.printStackTrace();
		System.err.println();
	}

	@Test
	void raiseIllegalArgumentException_WhenDuplication_AbbrevationsOccursInAbbrevationsList() {
		abbreviations.remove(randomIndex1);
		abbreviations.add(randomIndex1,
				"SPF_Sergio Perez_FORCE INDIA MERCEDES"); // SPF - Duplication Abbrevation
		abbreviations.remove(randomIndex1 + 1);
		abbreviations.add(randomIndex1 + 1,
				"SPF_Sergio Perez_FORCE INDIA MERCEDES"); // SPF - Duplication Abbrevation


		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"Abbrevation Tag in Abbrevations List must be uniqe.");
		thrown.printStackTrace();
		System.err.println();
	}

	@Test
	void raiseIllegalArgumentException_WheNumbersOfLinesInStartLogAndEndLogIsntSame() {
		startLog.add("SPF2018-05-24_12:12:01.035");
	
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"ERROR: startLog and endLog must contains same numbers of line.");
		thrown.printStackTrace();
		System.err.println();
	}	

	@Test
	void raiseIllegalArgumentException_WhenAbbrevationFromLapsLogsIsNotInUpperCase() {
		startLog.remove(randomIndex2);
		startLog.add(randomIndex2, "SpF2018-05-24_12:12:01.035"); // SpF - abbrevation not
																								// in UpperCase
		endLog.remove(randomIndex3);
		endLog.add(randomIndex3,"SpF2018-05-24_12:13:13.883");
		
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"Abbrevation Tag must use CAPITAL letters.");
		thrown.printStackTrace();
		System.err.println();
	}

	@Test
	void raiseIllegalArgumentException_WhenSizeOfAbbrevationFromLapsLogsIstNotEqualThreeChars() {
		startLog.remove(randomIndex2);
		startLog.add(randomIndex2, "SF2018-05-24_12:13:13.883"); // SF - size 
																								// Not Equal Three Chars
		endLog.remove(randomIndex3);
		endLog.add(randomIndex3,"SSSF2018-05-24_12:13:13.883");
		
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"Abbrevation Tag must use CAPITAL letters.");
		thrown.printStackTrace();
		System.err.println();
	}
	
	@Test
	void raiseIllegalArgumentException_WhenDuplication_AbbrevationsOccursInLapsLogs() {
		startLog.add("SPF2018-05-24_12:12:01.035");
		endLog.add("SPF2018-05-24_12:12:01.035");

		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"Abbrevation Tag in Laps Logs must be uniqe.");
		thrown.printStackTrace();
		System.err.println();
	}	

	@Test
	void raiseIllegalArgumentException_WhenAbbrevationFromLapsLogsDoesntExistInAbbrevationList() {
		startLog.add("ZZZ2018-05-24_12:12:01.035"); // ZZZ - abbrevation not in Abbrevation List
		endLog.add("ZZZ2018-05-24_12:12:01.035");
		
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"Abbrevation from Laps Log Must exist in Abbrevation List !!!.");
		thrown.printStackTrace();
		System.err.println();
	}

	@Test
	void raiseIllegalArgumentException_WhenAbbrevationFromLapsLogsMismatched() {
		startLog.remove(randomIndex2);
		startLog.add("SVM2018-05-24_12:00:00.000"); // VBM not matched with SVM
		
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"Abbrevations from Laps Log must be Matched !!!");
		thrown.printStackTrace();
		System.err.println();
	}
	
	@Test
	void raiseIllegalArgumentException_WhenTimeFromLapsLogsDoesntFitFormat() {
		startLog.remove(randomIndex2);
		startLog.add("VBM18-05-24_12:00:00.000"); // 18-05-24_12:00:00.000 doesnt fit format
		
		IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
				() -> parser.parseQualificationResults(abbreviations, startLog, endLog),
				"Time in Laps Log must fit format: YYYY-MM-DD_HH:MM:SS.MSS.");
		thrown.printStackTrace();
		System.err.println();
	}
	

	@Test
	void dataMustBeSameAsSampleData() {
		HashMap<Racer, Lap> expected = new HashMap<Racer, Lap>();
		HashMap<Racer, Lap> actual;		
		
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
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:03:15.145Z"),
				Instant.parse("2018-05-24T12:04:28.095Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:00:00Z"),
				Instant.parse("2018-05-24T12:01:12.434Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:14:12.054Z"),
				Instant.parse("2018-05-24T12:15:24.067Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:02:58.917Z"),
				Instant.parse("2018-05-24T12:04:03.332Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:02:51.003Z"),
				Instant.parse("2018-05-24T12:04:04.396Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:18:37.735Z"),
				Instant.parse("2018-05-24T12:19:50.198Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:09:41.921Z"),
				Instant.parse("2018-05-24T12:10:54.750Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:12:01.035Z"),
				Instant.parse("2018-05-24T12:13:13.883Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:14:51.985Z"),
				Instant.parse("2018-05-24T12:16:05.164Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:06:13.511Z"),
				Instant.parse("2018-05-24T12:07:26.834Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:13:04.512Z"),
				Instant.parse("2018-05-24T12:14:17.169Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:18:20.125Z"),
				Instant.parse("2018-05-24T12:19:32.585Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:05:14.511Z"),
				Instant.parse("2018-05-24T12:06:27.441Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:02:49.914Z"),
				Instant.parse("2018-05-24T12:04:02.979Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:16:11.648Z"),
				Instant.parse("2018-05-24T12:17:24.354Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:07:23.645Z"),
				Instant.parse("2018-05-24T12:08:36.586Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:17:58.810Z"),
				Instant.parse("2018-05-24T12:19:11.838Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:04:45.513Z"),
				Instant.parse("2018-05-24T12:05:58.778Z")));
		expected.put(racer.get(count), new Lap(racer.get(count++), Instant.parse("2018-05-24T12:03:01.250Z"),
				Instant.parse("2018-05-24T12:04:13.889Z")));

		actual = parser.parseQualificationResults(abbreviations, startLog, endLog);
			
		assertEquals(expected, actual,
				"The  data must be the same as the sample data.");
	}
}
