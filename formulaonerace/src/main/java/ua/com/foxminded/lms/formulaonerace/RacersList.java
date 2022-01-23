package ua.com.foxminded.lms.formulaonerace;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class RacersList {
	private ArrayList<Optional<Racer>> list;

	public RacersList() {
		this.list = new ArrayList<Optional<Racer>>();
	}

	public void loadListFromStream(Stream<String> abbreviations) {
		abbreviations.forEach(Line -> {
			String[] stringArray = Line.split("_");
			list.add(Optional.of(new Racer(stringArray[0], stringArray[1], stringArray[2])));
		});
	}

	public void clearListFromStream() {
		this.list.clear();
	}

	public Optional<Racer> getRacer(String abbreviation) {
		abbreviations.forEach(Line -> {
			String[] stringArray = Line.split("_");
			list.add(new Racer(stringArray[0], stringArray[1], stringArray[2]));
		});
	}
	
}
