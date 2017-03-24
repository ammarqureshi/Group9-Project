package com.test;

import java.util.Scanner;
public class HungarianHappiness {

	/**
	 * Returns an array containing 3 percentages, the first percentage is the percentage of groups
	 * getting a project in their first 3 preferences (happy), the second is percentage of groups
	 * getting a project >3 <6 preferences(middling), the third is the percentage of groups 
	 * getting a project >6 preferences (unhappy)
	 */
	public static double[] happinessScore(String preferences, String results) {
		String preferencesFormatted = preferences.replace("OP", "0");
		results = results.replace("OP", "0");
		String []groups = preferencesFormatted.split("\n");
		int numOfGroups = groups.length-1;
		double numOfHappy = 0;		//these are the number of groups that fall within our 3 percentiles
		double numOfMiddling = 0;
		double numOfUnhappy = 0;
		
		for (int i=1; i<groups.length; i++) {	//start at 1 cause 0 will be num of projects
			Scanner resultsScanner = new Scanner(results);
			Scanner groupScanner = new Scanner(groups[i]);
			int groupNum = groupScanner.nextInt();	//relies on fact that groups are listed sequentially
			int resultsGroup = resultsScanner.nextInt();
			while (groupNum != resultsGroup) {
				resultsScanner.nextInt();  //skips given project
				resultsGroup = resultsScanner.nextInt();
			}
			int givenProject = resultsScanner.nextInt();
			int projectPreference = 0;
			boolean notPreferenceProject = false;	//if project a group receives is not in their preferences
													//they are given the same score as they would receive if it was their last preference+1
			int chosenProject = 999;	//set to an int not used
			
			while (chosenProject != givenProject && notPreferenceProject != true) {
				if (groupScanner.hasNextInt()) {
					projectPreference++;
					chosenProject = groupScanner.nextInt();
				}
				else {
					projectPreference++;
					notPreferenceProject = true;
				}
			}
			if (notPreferenceProject == true || projectPreference > 6) {
				numOfUnhappy++;
			}
			else if (projectPreference > 3) {
				numOfMiddling++;
			}
			else {
				numOfHappy++;
			}
		}
		numOfHappy = (numOfHappy/numOfGroups)*100;
		numOfMiddling = (numOfMiddling/numOfGroups)*100;
		numOfUnhappy = (numOfUnhappy/numOfGroups)*100;
		double[] finalScore = new double[]{numOfHappy, numOfMiddling, numOfUnhappy};
		return finalScore;
	}
	
	public static String resultToString(double[] results) {
		String result = "% in top 3 - " + results[0] + "\n" +
				 "% in next 3 - " + results[1] + "\n" +
				 "% other preference - " + results[2] + "\n";
		return result;
	}

}
