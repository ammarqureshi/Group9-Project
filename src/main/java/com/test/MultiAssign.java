import java.util.Scanner;

public class MultiAssign {

    
	public static String[] CreateMultiGroups(String preferences, String info) {
	String[] multiGroups = new String[1];
	Scanner stringScan = new Scanner(info);
	int numOfAddedGroups = 0;
	String[] projectInfo = info.split("\n");	//splits the info into an array for easier to understand processing
	int endOfMultiGroups = 0;	//this and the following loop find where the multigroup info ends in the file by searching for the own projects header
	stringScan = new Scanner(projectInfo[1]); //get the num of projects
	int OriginalNumOfProjects = stringScan.nextInt();
	Scanner prefScanner = new Scanner(preferences);
	int numOfProjs = prefScanner.nextInt();	//this variable is duplicated as it changes inside the loop, but it's also needed for the loop
	for (int i = 2; i<OriginalNumOfProjects+2; i++) {	//the +2 is to account for the first two input lines which we skip
		stringScan = new Scanner(projectInfo[i]);
		int proNum = stringScan.nextInt();
		if (stringScan.hasNextInt()) {
			int numOfGroups = stringScan.nextInt();
			numOfAddedGroups += numOfGroups-1;	//-1 as the project already takes at least 1 group
			String newMGroup = proNum + " ";
			for (int j = numOfProjs+1; j<numOfProjs+numOfGroups; j++) {
				newMGroup += j + " ";
			}
			numOfProjs += numOfGroups-1;
			newMGroup += "\n";
			//save numbers to multiGroup string
			String[] extender = new String [multiGroups.length+1];
			for (int j=0; j<multiGroups.length; j++) {
				extender[j] = multiGroups[j];
			}
			extender[extender.length-1] = newMGroup;
			multiGroups = extender;			
		}
	}
	multiGroups[0] = numOfAddedGroups + "\n";
	return multiGroups;
}

	public static String editPreferences(String preferences, String[] multiGroups) {
		Scanner prefScanner = new Scanner(preferences);
		//increase numOfProjectsInPreferences
		int numOfProjs = prefScanner.nextInt();
		Scanner multiScanner = new Scanner (multiGroups[0]);
		int addedGroups = multiScanner.nextInt();
		int newNumOfProjs = numOfProjs + addedGroups;
		String[] groupPrefs = preferences.split("\n");
		groupPrefs[0] = newNumOfProjs + " ";
		
		
		for (int i = 1; i<multiGroups.length; i++) {
			
			for (int j = 1; j<groupPrefs.length; j++) {
				multiScanner = new Scanner(multiGroups[i]);
				int proNum = multiScanner.nextInt();
				prefScanner = new Scanner(groupPrefs[j]);
				String newPref = new String();
				newPref += prefScanner.nextInt() + " ";
				//runs through preferences searching for multi group project number
				while(prefScanner.hasNextInt()) {
					int nextPref = prefScanner.nextInt();
					newPref += nextPref + " ";
					if (nextPref == proNum) {
						while (multiScanner.hasNextInt()) {
							newPref += multiScanner.nextInt() + " ";
						}
					}
				}
				groupPrefs[j] = newPref;
			}
		}
		String newGroups = new String();
		for (int i = 0; i< groupPrefs.length; i++) {
			newGroups += groupPrefs[i] + "\n";
		}
		return newGroups;
	}

	public static String editResults(String results, String[] multiGroups) {
		String[] splitResults = results.split("\n");
		for (int i = 1; i<multiGroups.length; i++) {	//start at 1 to skip num of added groups
			Scanner multiScan = new Scanner (multiGroups[i]);
			int proNum = multiScan.nextInt();
			while (multiScan.hasNextInt()) {
				int addedProj = multiScan.nextInt();
				for (int j=0; j<splitResults.length; j++) {
					Scanner resultsScan = new Scanner(splitResults[j]);
					String newResult = resultsScan.nextInt() + " "; // group num
					int allocatedProj = resultsScan.nextInt();
					if (allocatedProj == addedProj) {
						newResult += proNum + "";
					}
					else {
						newResult += allocatedProj + "";
					}
					splitResults[j] = newResult;
				}	
			}
		}
		String newResults = new String();
		for (int i = 0; i< splitResults.length; i++) {
			newResults += splitResults[i] + "\n";
		}
		return newResults;
	}
}
