package ua.com.foxminded.lms.formulaonerace;

import java.time.Instant;
import java.util.HashMap;
import java.util.stream.Stream;

public class LapsHashMap {

	private HashMap<Racer, Lap> hashMap;
	private RacersHashMap racersHashMap; 

	public LapsHashMap() {
		hashMap = new HashMap<Racer, Lap>();
		racersHashMap = new RacersHashMap();
	}
	
	public void loadLapsDataFromStream(Stream<String> startLog, Stream<String> endLog) {
		startLog.forEach(Line -> {
			String abbrevation = Line.substring(0, 3);
			Instant dataStamp = Instant.parse(Line.substring(3).replace("_", "T") + "Z");
			if (racersHashMap.containsRacer(abbrevation)) {
				hashMap.put(racersHashMap.getRacer(abbrevation), 
						new Lap(racersHashMap.getRacer(abbrevation), dataStamp, dataStamp));
			}
		});
		
		endLog.forEach(Line -> {
			String abbrevation = Line.substring(0, 3);
			Instant dataStamp = Instant.parse(Line.substring(3).replace("_", "T") + "Z");
			if (racersHashMap.containsRacer(abbrevation)) {
				hashMap.get(racersHashMap.getRacer(abbrevation))
					.setEndTime(dataStamp);
			}
		});
		
	}
	
	public void loadRacersDataFromStream(Stream<String> abbreviations) {
		racersHashMap.clearData();
		racersHashMap.loadDataFromStream(abbreviations);				
	}

	public void clearData() {
		hashMap.clear();
		racersHashMap.clearData();
	}
	
	public Stream<Lap> toStream() {
		return hashMap.values().stream();
	}
}
