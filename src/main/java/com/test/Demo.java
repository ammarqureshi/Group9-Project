package com.test;

public class Demo {

	public static void main(String[] args) {
		String lastYears = Hungarian.getStringFromFile("lastYearsPreferences.txt");
		String result = Hungarian.getAssignments(lastYears, null);
		System.out.println(result);
		String happinessResults = null; // TODO - terlis
	}

}
