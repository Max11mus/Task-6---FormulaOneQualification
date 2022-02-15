package ua.com.foxminded.lms.formulaonerace.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileLoader {

	public List<String> load(URL url) throws IOException, URISyntaxException {
		if (url == null) {
			throw new IllegalArgumentException("ERROR: Null Pointer Argument.");
		}
		
		List<String> lines;
		lines = null;

		try {
			Path path;
			path = Paths.get(url.toURI());
			lines = Files.readAllLines(path);

		} catch (URISyntaxException e) {
			e.printStackTrace();
			throw e; 
		} catch (IOException e) {
			e.printStackTrace();
			throw e; 
		}
				
		return lines;

	}

}
