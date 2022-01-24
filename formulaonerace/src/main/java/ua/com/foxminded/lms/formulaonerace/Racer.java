package ua.com.foxminded.lms.formulaonerace;

import java.util.Objects;

public class Racer {
	private String abbrevation;
	private String name;
	private String team;

	public Racer(String abbrevation, String name, String team) {
		this.abbrevation=abbrevation;
		this.name=name;
		this.team=team;
	}

	public String getAbbrevation() {
		return abbrevation;
	}

	public void setAbbrevation(String abbrevation) {
		this.abbrevation = abbrevation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	@Override
	public int hashCode() {
		return Objects.hash(abbrevation);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Racer other = (Racer) obj;
		return Objects.equals(abbrevation, other.abbrevation);
	}
	
	
}
