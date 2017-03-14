package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.AllPermission;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MainProgram {

    
    public static String getAssignments(String preferences, String projDescriptions) {
    	ProjectData pd = new ProjectData(projDescriptions);
    	PreferenceParser pp = new PreferenceParser(pd);
        int[][] cos = pp.getCostMatrixForPreferences(preferences);
        
		Hungarian hbm = new Hungarian(cos);
		int[] result = hbm.execute();
		
		String resultString = createResultsString(result, pp, pd);
		
		while(!findUnallocatedPriorityProjects(resultString, pd).isEmpty()) {

			for(int i = 0; i < cos.length; i++) {
				for(int j = 0; j < cos.length; j++)
					if(cos[i][j] != PreferenceParser.VALUE_UNWANTED_PROJ)
						cos[i][j]++;
			}
			
			for(Integer unallocNum : findUnallocatedPriorityProjects(resultString, pd)){
				for(Integer col : pd.getColNumsForProjNum(unallocNum)){
					for(int i = 0; i < cos.length; i++) {
						cos[i][col] = cos[i][col] - 1;
					}
				}
			}

			hbm = new Hungarian(cos);
			result = hbm.execute();
			resultString = createResultsString(result, pp, pd);
		}
		
		// uncomment this to run happiness testing for all tests easily
		// System.out.println(HungarianHappiness.resultToString(HungarianHappiness.happinessScore(preferences, resultString)));
        return resultString;
    }

    private static Set<Integer> findUnallocatedPriorityProjects(String resultString, ProjectData pd) {
    	
    	Scanner resultStringScanner = new Scanner(resultString);
    	Set<Integer> assignedProjects = new HashSet<Integer>();
    	
    	while(resultStringScanner.hasNextLine()) {
    		String token = resultStringScanner.nextLine().split(" ")[1];
    		if(!token.equalsIgnoreCase("OP"))
    			assignedProjects.add(Integer.parseInt(token));
    	}
    	
    	resultStringScanner.close();
    	
    	Set<Integer> unassignedPriorityProjects = (HashSet<Integer>) pd.getPriorityProjects().clone();

    	unassignedPriorityProjects.removeAll(assignedProjects);
    	return unassignedPriorityProjects;
    }
    
    private static String createResultsString(int[] result, PreferenceParser pp, ProjectData pd) {
		HashMap<Integer, String> groupRowToName = pp.getRowToGroupName();
		String resultString = "";
	 	int matrixSize = pd.totalProjectColumns();
        for(int i=0; i<matrixSize; i++)
        {
        	//group name followed by project number
        	if (groupRowToName.get(i) != null) {
        		String groupRowName = groupRowToName.get(i);
        		int projectNum = pd.getProjNumForColNum(result[i]);
        		String projNumString = projectNum == ProjectData.OWN_PROJECT ? "OP" : "" + projectNum;
            	resultString += groupRowName + " " + projNumString + "\n";
        	}
        }
        return resultString;
    }
    
    public static String getStringFromFile(String filename) {
    	String text = "";
		try {
			Scanner scanner = new Scanner( new File(filename) );
	    	text = scanner.useDelimiter("\\A").next();
	    	scanner.close();
		} catch (FileNotFoundException e) {
			System.err.println("File " + filename + " not found!");
			e.printStackTrace();
		}
    	return text;
    }
   
    public static String assignFromFileInput(String preferencesPath, String projectInfoPath) {
    	String preferences = getStringFromFile(preferencesPath);
    	String projDescriptions = getStringFromFile(projectInfoPath);
    	return getAssignments(preferences, projDescriptions);
    }
    
    public static void main(String[] args) throws Exception
    {
    	//run with command line args with first and second file
    	String result = assignFromFileInput(args[0], args[1]);
    	System.out.println(result);
	 }

}
