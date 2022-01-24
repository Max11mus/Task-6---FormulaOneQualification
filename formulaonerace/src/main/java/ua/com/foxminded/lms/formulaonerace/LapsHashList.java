package ua.com.foxminded.lms.formulaonerace;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Stream;

public class LapsHashList {

	private HashSet<Racer> hashSet;

	public LapsHashList() {
		hashSet = new ArrayList<Lap>();
	}
	
	public void loadListFromStream(Stream<String> startLog, Stream<String> endLog) {
		startLog.forEach(Line -> {
			String[] stringArray = Line.split("_");
			hashSet.add(new Racer(stringArray[0], stringArray[1], stringArray[2]));
		});
	}

	public void clearListFromStream() {
		this.hashSet.clear();
	}
	
}
