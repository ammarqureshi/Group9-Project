package com.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class MainProgram {

    
    public static String getAssignments(String preferences, String projDescriptions) {
    	
    	ProjectData pd = new ProjectData(projDescriptions);
    	PreferenceParser pp = new PreferenceParser(pd);
        int[][] cos = pp.getCostMatrixForPreferences(preferences);
		Hungarian hbm = new Hungarian(cos);
		int[] result = hbm.execute();
		
		HashMap<Integer, String> groupRowToName = pp.getRowToGroupName();
		
		String resultString = "";
	 	int matrixSize = pd.totalProjectColumns();
        for(int i=0; i<matrixSize; i++)
        {
        	//group name followed by project number
        	if (groupRowToName.get(i) != null) {
        		String groupRowName = groupRowToName.get(i);
        		int projectNum = pd.getProjNumForColNum(result[i]);
            	resultString += groupRowName + " " + projectNum + "\n";
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
   
    public static String assignFromFileInput(String preferencesPath, String groupsPath) {
    	String preferences = getStringFromFile(preferencesPath);
    	//null for now because we haven't got group descriptions sorted!
    	//String groupDescriptions = getStringFromFile(groupsPath);
    	return getAssignments(preferences, null);
    }
    
    public static void main(String[] args) throws Exception
    {
    	//run with command line args with first and second file
    	String result = assignFromFileInput(args[0], "not implemented yet but will be args[1]");
    	System.out.println(result);
	 }

}
