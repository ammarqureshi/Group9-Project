package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.AllPermission;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MainProgram {

    
    public static String getAssignments(String preferences, String projDescriptions) {
    	ProjectData pd = new ProjectData(projDescriptions);
    	PreferenceParser pp = new PreferenceParser(pd);
        int[][] cos = pp.getCostMatrixForPreferences(preferences);
		Hungarian hbm = new Hungarian(cos);
		int[] result = hbm.execute();
		String resultString = createResultsString(result, pp, pd);
		while(!allPriorityProjectsAllocated(resultString, pd)) {
			//pp.prioritizeProject(costMatrix, projNum);
		}
        return resultString;
    }

    private static boolean allPriorityProjectsAllocated(String resultString, ProjectData pd) {
    	
    	boolean allAssigned = true;
    	Scanner resultStringScanner = new Scanner(resultString);
    	HashSet<Integer> assignedProjects = new HashSet<Integer>();
    	
    	while(resultStringScanner.hasNextLine()) {
    		assignedProjects.add(Integer.parseInt(resultStringScanner.nextLine().split(" ")[1]));
    	}
    	
    	resultStringScanner.close();
    	
    	for(Integer priorityProj : pd.getPriorityProjects())
    	{
    		if(!assignedProjects.contains(priorityProj))
    			allAssigned = false;
    	}
    	return allAssigned;
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
