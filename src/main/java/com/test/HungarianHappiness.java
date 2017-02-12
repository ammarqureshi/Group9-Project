import java.util.Scanner;
public class HungarianHappiness {

	/**
	 * Returns an array containing 3 percentages, the first percentage is the percentage of groups
	 * getting a project in their first 4 preferences (happy), the second is percentage of groups
	 * getting a project >4 <8 preferences(middling), the third is the percentage of groups 
	 * getting a project >8 preferences (unhappy)
	 */
	public static double[] happinessScore(String [] groups, String results) {
		int numOfGroups = groups.length;
		double numOfHappy = 0;		//these are the number of groups that fall within our 3 percentiles
		double numOfMiddling = 0;
		double numOfUnhappy = 0;
		Scanner resultsScanner = new Scanner(results);
		
		for (int i=0; i<groups.length; i++) {
			Scanner groupScanner = new Scanner(groups[i]);
			groupScanner.nextInt();	//used as the first int is the group number and not needed here
			int givenProject = resultsScanner.nextInt();
			int projectPreference = 0;
			boolean notPreferenceProject = false;	//if project a group receives is not in their preferences
													//they are given the same score as they would receive if it was their last preference+1
			int chosenProject = 0;
			
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
			if (notPreferenceProject == true || projectPreference > 8) {
				numOfUnhappy++;
			}
			else if (projectPreference > 4) {
				numOfMiddling++;
			}
			else {
				numOfHappy++;
			}
		}
		
		resultsScanner.close();
		numOfHappy = (numOfHappy/numOfGroups)*100;
		numOfMiddling = (numOfMiddling/numOfGroups)*100;
		numOfUnhappy = (numOfUnhappy/numOfGroups)*100;
		double[] finalScore = new double[]{numOfHappy, numOfMiddling, numOfUnhappy};
		return finalScore;
	}

}
