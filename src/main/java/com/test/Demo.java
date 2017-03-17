package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) throws FileNotFoundException {
		String lastYears = Hungarian.getStringFromFile("lastYearsPreferences.txt");
		Scanner scanner = new Scanner(new File("priorityProjects.txt"));
		int [] priProjects = new int [10];
		int i = 0;
		while(scanner.hasNextInt()){
		   priProjects[i++] = scanner.nextInt();
		}
		System.out.println("List of Priority Projects: ");
		for (int k = 0; k<priProjects.length; k++) {
			if(priProjects[k] != 0) {
			System.out.println(priProjects[k]);
			}
		}
		String result = Hungarian.getAssignments(lastYears, null);
		System.out.println(result);
//		double[] happinessScore = HungarianHappiness.happinessScore(lastYears, result);
//		String happinessResults = "The happiness score for this allocation is " +happinessScore[0] + "% Happy, " 
//			+happinessScore[1] + "% Middling and " +happinessScore[2] + "% Unhappy.";
//		System.out.println(happinessResults);
//		String lastYearsBumped = lastYears;
//		for (int j = 0; j<10; j++) {
//			lastYearsBumped = PrioritiseProjects.bump(lastYearsBumped, priProjects);
//			result = Hungarian.getAssignments(lastYearsBumped, null);
//			System.out.println(result);
//			happinessScore = HungarianHappiness.happinessScore(lastYears, result);
//			happinessResults = "The happiness score for this allocation is " +happinessScore[0] + "% Happy, " 
//			+happinessScore[1] + "% Middling and " +happinessScore[2] + "% Unhappy.";
//			System.out.println(happinessResults);
//		}
	}

}

