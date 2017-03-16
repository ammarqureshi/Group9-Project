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
		preferences= preferences.replaceAll("OP", "0");
		results = results.replaceAll("OP", "0");
		String []groups = preferences.split("\n");
		String []splitResults= results.split("\n");
		int numOfGroups = groups.length-1;
		double numOfHappy = 0;		//these are the number of groups that fall within our 3 percentiles
		double numOfMiddling = 0;
		double numOfUnhappy = 0;
		
		for (int i=1; i<groups.length; i++) {	//start at 1 cause 0 will be num of projects
			Scanner resultsScanner = new Scanner(splitResults[i-1]);
			Scanner groupScanner = new Scanner(groups[i]);
			int groupNum = groupScanner.nextInt();	//relies on fact that groups are listed sequentially
			int resultsGroup = resultsScanner.nextInt();
			while (groupNum != resultsGroup) {
				resultsScanner.nextInt();  //skips given project
				resultsGroup = resultsScanner.nextInt();
			}
			boolean notPreferenceProject = false;	//if a group receives a project that is not in their preferences
												//they are given the same score as they would receive if it was their last preference+1

			int projectPreference =0;
			/*if (splitResults[i-1].contains("OP")) {
				projectPreference = 1;
			}
			else {*/
			int givenProject = resultsScanner.nextInt();
			int chosenProject = 999;
			
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
			//}
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

}
