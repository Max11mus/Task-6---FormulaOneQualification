package ua.com.foxminded.lms.formulaonerace;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import javax.sound.sampled.Line;

public class RacersHashSet {
	private HashSet hashSet;

	public RacersHashSet() {
		this.hashSet = new ArrayList<Optional<Racer>>();
	}

	public void loadListFromStream(Stream<String> abbreviations) {
		abbreviations.forEach(Line -> {
			String[] stringArray = Line.split("_");
			set.add(stringArray[0], Optional.of(new Racer(stringArray[0], stringArray[1], stringArray[2])));
		});
	}

	public void clearListFromStream() {
		this.hashSet.clear();
	}

	public Optional<Racer> getRacer(String abbreviation) {
		abbreviations.forEach(Line -> {
			String[] stringArray = Line.split("_");
			hashSet.add(new Racer(stringArray[0], stringArray[1], stringArray[2]));
		});
	}
	
}
