package com.test;

public class Demo {

	public static void main(String[] args) {
		String lastYears = Hungarian.getStringFromFile("lastYearsPreferences.txt");
		String result = Hungarian.getAssignments(lastYears, null);
		System.out.println(result);
		double[] happinessScore = HungarianHappiness.happinessScore(lastYears, result);
		String happinessResults = "The happiness score for this allocation is " +happinessScore[0] + "% Happy, " 
			+happinessScore[1] + "% Middling and " +happinessScore[2] + "% Unhappy.";
		/*while (PrioritiseProjects.AllAllocated(result, priorityProjects) == false) {
			lastYears = PrioritiseProjects.bumpPriority(lastYears, priorityProjects);
			result = Hungarian.getAssignments(lastYears, null);
		}*/
	}

}
