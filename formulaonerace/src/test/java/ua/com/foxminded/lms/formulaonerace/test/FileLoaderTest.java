package ua.com.foxminded.lms.formulaonerace.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ua.com.foxminded.lms.formulaonerace.utils.FileLoader;


class FileLoaderTest {
	static FileLoader fileLoader;
	
	@BeforeAll
	static void fileLoaderTestsSetUp() {
		fileLoader = new FileLoader(); 	 	
	}
	
	@Test	
	void fileLoader_raise_IllegalArgumentException_WhenArgumentIsNull() {
		assertThrows(IllegalArgumentException.class, () -> fileLoader.load(null),
				"Must throw IllegalArgumentException exception when input null.");
	}

	@Test	
	void fileLoader_raiseMalformedURLException_WhenUrlIsInvalid() {
		assertThrows(MalformedURLException.class, () -> fileLoader.load(new URL("h://www.oracle.com/index.html")),
				"Must throw MalformedURLException exception when when url is invalid.");
	}
	
	@Test	
	void fileLoader_ReadedDataMustBeSameAsSampleData() throws MalformedURLException, IOException, URISyntaxException{
		List<String> expected = Stream.of(
				"DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
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
				"KMH_Kevin Magnussen_HAAS FERRARI").collect(Collectors.toList());
		
		List<String> actual=fileLoader.load(ClassLoader.getSystemResource("abbreviations.txt")); 
		
				assertEquals(expected, actual,
				"The read data must be the same as the sample data.");
	}
	
}
