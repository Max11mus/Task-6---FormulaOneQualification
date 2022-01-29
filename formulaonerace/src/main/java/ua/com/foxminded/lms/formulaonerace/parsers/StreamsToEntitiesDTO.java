package ua.com.foxminded.lms.formulaonerace.parsers;

import java.time.Instant;
import java.util.HashMap;
import java.util.stream.Stream;

import ua.com.foxminded.lms.formulaonerace.entities.Racer;
import ua.com.foxminded.lms.formulaonerace.entities.Lap;

public class StreamsToEntitiesDTO {
	private HashMap<String, Racer> racers;
	private HashMap<Racer, Lap> laps;

	public StreamsToEntitiesDTO() {
		racers = new HashMap<String, Racer>();
		laps = new HashMap<Racer, Lap>();
	}

	public void parseToEntities(Stream<String> abbreviations, Stream<String> startLog, Stream<String> endLog) {
		racers.clear();
		laps.clear();

		abbreviations.forEach(Line -> {
			String[] stringArray = Line.split("_");
			racers.put(stringArray[0], new Racer(stringArray[0], stringArray[1], stringArray[2]));
		});

		startLog.forEach(Line -> {
			String abbrevation = Line.substring(0, 3);
			Instant dataStamp = Instant.parse(Line.substring(3).replace("_", "T") + "Z");
			if (racers.containsKey((abbrevation))) {
				laps.put(racers.get(abbrevation), new Lap(racers.get(abbrevation), dataStamp, dataStamp));
			}
		});

		endLog.forEach(Line -> {
			String abbrevation = Line.substring(0, 3);
			Instant dataStamp = Instant.parse(Line.substring(3).replace("_", "T") + "Z");
			if (racers.containsKey(abbrevation)) {
				laps.get(racers.get(abbrevation)).setEndTime(dataStamp);
			}
		});
	}

	public HashMap<String, Racer> getRacers() {
		return racers;
	}

	public HashMap<Racer, Lap> getLaps() {
		return laps;
	}

}
