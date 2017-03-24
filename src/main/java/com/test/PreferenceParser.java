package com.test;

import java.util.HashMap;

public class PreferenceParser {
	
	public final static int VALUE_UNWANTED_PROJ = 101;
	
	ProjectData pd;
	HashMap<String, Integer> groupNameToRow = new HashMap<String, Integer>();
	HashMap<Integer, String> rowToGroupName = new HashMap<Integer, String>();
	int nextAvailableRow = 0;
	
	public HashMap<String, Integer> getGroupNameToRow() {
		return groupNameToRow;
	}

	public HashMap<Integer, String> getRowToGroupName() {
		return rowToGroupName;
	}

	public PreferenceParser(ProjectData pd){
		this.pd = pd;
	}
	
	public int[][] getCostMatrixForPreferences(String preferences) {
		int matrixSize = pd.totalProjectColumns();
		int[][] costMatrix = new int[matrixSize][matrixSize];
		int[] highestWeightForRow = new int[matrixSize];
		
		String[] lines = preferences.split("\n");
		for(String line : lines) {
			
			String[] tokens = line.split("\\s+");
			String groupName = tokens[0];
			int rowForGroup = nextAvailableRow++;
			
			groupNameToRow.put(groupName, rowForGroup);
			rowToGroupName.put(rowForGroup, groupName);
			
			boolean pickedAnOriginalProj = false;
			for(int i = 1; i < tokens.length; i++) {
				int projNum;
				if(tokens[i].equalsIgnoreCase("OP")) {
					projNum = ProjectData.OWN_PROJECT;
					pickedAnOriginalProj = true;
				}
				else
					projNum = Integer.parseInt(tokens[i]);
				int weight = 0;
				if(projNum == ProjectData.OWN_PROJECT && !pd.getVettedOwnProjects().contains(groupName))
					weight = VALUE_UNWANTED_PROJ;
				else
					weight = highestWeightForRow[rowForGroup] + 1;
				highestWeightForRow[rowForGroup]++;
				addPreference(costMatrix, rowForGroup, projNum, weight);
			}
			if(!pickedAnOriginalProj)
				// deals with edge case where group with few preferences is assigned "own project"
				// even if no preference would have been listed!
				addPreference(costMatrix, rowForGroup, ProjectData.OWN_PROJECT, VALUE_UNWANTED_PROJ);
		}
		
		weightUnfilledPreferences(costMatrix, highestWeightForRow);
		return costMatrix;
	}
	
	private void addPreference(int[][] costMatrix, int row, int projNum, int weight) {
		if(pd.getColNumsForProjNum(projNum) != null ) {
			for(int col : pd.getColNumsForProjNum(projNum)) {
				costMatrix[row][col] = weight;
			}
		}
	}
	

	private void weightUnfilledPreferences(int[][] costMatrix, int[] highestWeightForRow){
		for(int i = 0; i < costMatrix.length; i++)
		{
			for(int j = 0; j < costMatrix.length; j++){
				if(costMatrix[i][j] == 0) {
					if(rowToGroupName.containsKey(i))
						costMatrix[i][j] = 2*(highestWeightForRow[i]);
					else
						costMatrix[i][j] = VALUE_UNWANTED_PROJ;
				}
			}
		}
    }

}
