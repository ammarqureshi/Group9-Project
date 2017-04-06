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
		
		for (int i=1; i<groups.length; i++) {	
			Scanner resultsScanner = new Scanner(results);
			Scanner groupScanner = new Scanner(groups[i]);
			String groupNum = groupScanner.next();	//relies on fact that groups are listed sequentially
			String resultsGroup = resultsScanner.next();
			while (!groupNum.equals(resultsGroup)) {
				resultsScanner.nextInt();  //skips given project
				resultsGroup = resultsScanner.next();
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
		String[] twoDecimalPlaces = new String[3];
		for(int i = 0; i < results.length; i++) {
			twoDecimalPlaces[i] = String.format("%.2f", results[i]);
		}
		
		String result = "Groups receiving top 3 preference - " + twoDecimalPlaces[0] + "%\n" +
				 "Groups receiving preference 4-6 - " + twoDecimalPlaces[1] + "%\n" +
				 "Groups receiving other preference - " + twoDecimalPlaces[2] + "%\n";
		return result;
	}

}
