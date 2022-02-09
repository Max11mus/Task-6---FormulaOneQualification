package ua.com.foxminded.lms.formulaonerace.utils;

import java.util.ArrayList;
import java.util.Objects;

public final class Pair <First extends Object, Second extends Object> {
	private First first;
	private Second  second;

	public Pair(First first, Second second) {
		this.first = first; 
		this.second = second;
	}

	public First getFirst() {
		return first;
	}

	public void setFirst(First first) {
		this.first = first;
	}

	public Second getSecond() {
		return second;
	}

	public void setSecond(Second second) {
		this.second = second;
	}

	
	
	@Override
	public int hashCode() {
		return Objects.hash(first, second);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Pair)) {
			return false;
		}
		Pair<?, ?> other = (Pair<?, ?>) obj;
		return Objects.equals(first, other.first) && Objects.equals(second, other.second);
	}
		
	@Override
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}
	
	public static  <First, Second> ArrayList<Pair<First, Second>> fromArrayListOfParams( ArrayList<First> firstArray,
			  ArrayList<Second> secondArray) {
		if (firstArray.size() != secondArray.size()) {
			throw new IllegalArgumentException("ERROR: firstArray and secondArray must be same size.");
		}
		ArrayList<Pair<First, Second>> result = new ArrayList<Pair<First, Second>>();
		
		for (int i = 0; i < firstArray.size(); i++) {
			result.add(new  Pair<First, Second>(firstArray.get(i), secondArray.get(i)));
		}
		return result;

	}
}
