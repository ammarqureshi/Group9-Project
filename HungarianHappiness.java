import java.util.Scanner;
public class HungarianHappiness {

	//calculates a 'happiness' score for the project allocations out of 100
	public static double happinessScore(String [] groups, String results, int numOfProjects) {
		int numOfGroups = groups.length;		
		int totalScore = numOfGroups * numOfProjects;
		double currentScore = 0;
		int topScore = totalScore/numOfGroups;	//the score given when a group gets first preference
		int scoreDecrease = topScore/numOfProjects; //how much the score given decreases for a lower preference
		Scanner resultsScanner = new Scanner(results);
		
		for (int i=0; i<groups.length; i++) {
			Scanner groupScanner = new Scanner(groups[i]);
			groupScanner.nextInt();
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
			int score = topScore -(scoreDecrease * (projectPreference-1));
			currentScore += score;
		}
		
		resultsScanner.close();
		currentScore = (int)((currentScore/totalScore) * 100);	//works out the score as a score out of 100
		return currentScore;
	}

}
