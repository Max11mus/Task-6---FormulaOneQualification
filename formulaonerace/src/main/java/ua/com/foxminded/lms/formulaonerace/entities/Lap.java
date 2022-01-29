package ua.com.foxminded.lms.formulaonerace.entities;

import java.time.Duration;
import java.time.Instant;
import java.util.Objects;

public class Lap {
	 private Racer racer;
	 private Instant startTime;
	 private Instant endTime;
 
	 public Lap(Racer racer, Instant startTime, Instant endTime) {
		this.racer = racer;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	 public Racer getRacer() {
		return racer;
	}
	public void setRacer(Racer racer) {
		this.racer = racer;
	}
	public Instant getStartTime() {
		return startTime;
	}
	public void setStartTime(Instant startTime) {
		this.startTime = startTime;
	}
	public Instant getEndTime() {
		return endTime;
	}
	public void setEndTime(Instant endTime) {
		this.endTime = endTime;
	}

	public Duration getLapTime() {
		return Duration.between(startTime, endTime);
	}
	
	public int compareTo(Lap another) {
		return this.getLapTime().compareTo(another.getLapTime());
	
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(racer);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lap other = (Lap) obj;
		return Objects.equals(racer, other.racer);
	}

}
