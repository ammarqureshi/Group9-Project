package com.test;

import java.util.Scanner;
import java.util.Arrays;

public class PrioritiseProjects {

	public static boolean AllAllocated(String results, int[]priorityProjects) {
		boolean AllAllocated = true;
		Scanner scanner = new Scanner(results);
		for (int i = 0 ; i<priorityProjects.length; i++) {
			boolean projectAllocated = false;
			while(scanner.hasNextInt() == true) {
				scanner.nextInt():	//skips the group number in the results
				int nextResult = scanner.nextInt();
				if (priorityProjects[i] == nextResult) {
					projectAllocated = true;
				}
			}
			if (projectAllocated != true) {
				AllAllocated = false;
			}
			scanner = new Scanner(results);
		}
		return AllAllocated;
	}
	
	public static String bumpPriority (String groups, int[]priorityProjects) {
		String []preferences = groups.split("\n");
		for (int i = 1; i<preferences.length;i++) {	//start at 1 as 0 is num of projects
			Scanner scanner = new Scanner(preferences[i]);
			int preferencePos = 0;
			scanner.nextInt();  //removes group number 
			while (scanner.hasNextInt()) {
				int nextPreference = scanner.nextInt();
				preferencePos++;
				for (int j = 0; j<priorityProjects.length; j++) {
					if (priorityProjects[j] == nextPreference) {
						int[] a = new int[(preferences[i].length()/2)-1];
						Scanner stringScanner = new Scanner(preferences[i]);
						a[0]= stringScanner.nextInt();
						for (int k = 1; k<a.length; k++) {
							a[k] = stringScanner.nextInt();
						}
						int oldPref = a[preferencePos- 1];
						a[preferencePos - 1] = nextPreference;
						a[preferencePos] = oldPref;
						String newPrefs = Arrays.toString(a);
						preferences[i] = newPrefs;
						preferences[i] = preferences[i].replace("[", "")
						.replace("]", "")
						.replaceAll(",", "");
					}
				}
			}			
		}
		String newGroups = new String();
		for (int i = 0; i< preferences.length; i++) {
			newGroups += preferences[i], "\n";
		}
		return newGroups;
	}
}
