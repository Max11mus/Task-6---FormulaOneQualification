package ua.com.foxminded.lms.formulaonerace;

import java.util.HashMap;
import java.util.stream.Stream;

public class RacersHashMap {
	private HashMap<String, Racer> hashMap;

	public RacersHashMap() {
		this.hashMap = new HashMap<String, Racer>();
	}

	public void loadDataFromStream(Stream<String> abbreviations) {
		abbreviations.forEach(Line -> {
			String[] stringArray = Line.split("_");
			hashMap.put(stringArray[0], new Racer(stringArray[0], stringArray[1], stringArray[2]));
		});
	}

	public void clearData() {
		this.hashMap.clear();
	}

	public boolean containsRacer(String abbreviation) {
		return hashMap.containsKey(abbreviation);
	}
	
	public Racer getRacer(String abbreviation) {
		return hashMap.get(abbreviation);
	}
	
}
