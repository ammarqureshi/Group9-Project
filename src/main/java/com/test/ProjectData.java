package com.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ProjectData {
	
	public static final int OWN_PROJECT = 999;
	// projects can map to multiple columns of the matrix
	// for multigroup or "own project" assignment
	private HashMap<Integer, HashSet<Integer>> projectNumToColNums = new HashMap<Integer, HashSet<Integer>>();
	
	//all cols must map back to one project number
	private HashMap<Integer, Integer> colNumsToProjectNum = new HashMap<Integer, Integer>();

	private int nextFreeColNumber = 0;
	private int maxOwnProjects = 0;

	//keep list of priority projects (contains multiple copies of multigroup priority projects)
	private LinkedList<Integer> priorityProjects = new LinkedList<Integer>();
	//keep list of allowed own projects
	private HashSet<String> vettedOwnProjects = new HashSet<String>();
	
	public int totalProjectColumns() {
		return nextFreeColNumber;
	}
	
	public LinkedList<Integer> getPriorityProjects() {
		return priorityProjects;
	}

	public HashSet<String> getVettedOwnProjects() {
		return vettedOwnProjects;
	}

	public HashSet<Integer> getColNumsForProjNum(int projNum) {
		return projectNumToColNums.get(projNum);
	}
	
	public int getProjNumForColNum(int colNum) {
		return colNumsToProjectNum.get(colNum);
	}
	
	//if you want to see what each column's assigned group is in a more grokkable way
	public String colHeadersToString(){
		String result = "";
		for(int i = 0; i < nextFreeColNumber; i++) {
			result += String.format("%5d", getProjNumForColNum(i));
		}
		return result;
	}
		
	
	public ProjectData(String projectInfo) {
		String[] lines = projectInfo.split("\n");
		if(!lines[0].equals("Projects")) {
			final String err = "Projects info file incorrectly formatted? Doesn't start with \"Projects\\n\"";
		    throw new IllegalArgumentException(err);
		}
		
		int i = 1;
		
		// read project info until "OwnProjects" or end of file
		while(i < lines.length) {
			if(!lines[i].equals("OwnProjects")) {
				String[] tokens = lines[i].split("\\s+");
				int projNum = Integer.parseInt(tokens[0]);
				int numOfColsNeeded = 1;
				
				for(int j = 1; j < tokens.length; j++) {
					if(tokens[j].equals("P")) {
						priorityProjects.add(projNum);
					}
					else if (tokens[j].matches("\\d+")){
						// is multigroup
						numOfColsNeeded = Integer.parseInt(tokens[j]);
						// if priority needs multiple copies in list of priority projects
						if(priorityProjects.contains(projNum)) {
							for(int k = 1; k < numOfColsNeeded; k++)
								priorityProjects.add(projNum);
						}
					}
					else{
						
						String err = "Token in project info for project " + projNum + " unrecognised : " + tokens[j];
					    throw new IllegalArgumentException(err);
					}
				}
				
				HashSet<Integer> allColsForThisProj = new HashSet<Integer>();
				
				for(int j = 0; j < numOfColsNeeded; j++){
					allColsForThisProj.add(nextFreeColNumber);
					colNumsToProjectNum.put(nextFreeColNumber, projNum);
					nextFreeColNumber++;
				}
				
				projectNumToColNums.put(projNum, allColsForThisProj);
				
				// go to next line
				i++;
				
			} else if(i < lines.length) {
				//skip line containing "OwnProjects" and parse rest
				if(lines[i].equals("OwnProjects"))
				{
					i++; //skip "OwnProjects"
					maxOwnProjects = Integer.parseInt(lines[i]);
					i++;
					
					HashSet<Integer> allColsForOwnProjs = new HashSet<Integer>();
					for(int j = 0; j < maxOwnProjects; j++){
						//these columns all match to a group selecting an own project
						allColsForOwnProjs.add(nextFreeColNumber);
						colNumsToProjectNum.put(nextFreeColNumber, OWN_PROJECT);
						nextFreeColNumber++;
					}
					
					projectNumToColNums.put(OWN_PROJECT, allColsForOwnProjs);
					
					while(i < lines.length) {
						vettedOwnProjects.add(lines[i]);
						i++;
					}
				}
			}
		}
	}

}
