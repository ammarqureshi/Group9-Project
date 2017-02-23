package com.test;

import java.util.*;

public class InputHandel
{
	public int matrixSize;
    public int[] currentMaxForRow;
	int[][] costMatrix;
	
    private HashMap<Integer, Integer> projectNumberToMatrixCol = new HashMap<>();
    private HashMap<Integer, Integer> matrixColsToProjectNumbersMap = new HashMap<>();
    private int nextUnusedCol = 0;
    
    
	public int[][] parseInput(String input) {
		String matrixSizeArg="";
		 
	    List<String> myFileLines = new LinkedList<String>();
	    Scanner lineScanner = new Scanner(input);
	    
	    //get first number as dimension
    	matrixSizeArg = lineScanner.nextLine().replaceAll("\\s+",""); //removes whitespace
    	matrixSize = Integer.parseInt(matrixSizeArg);
    	currentMaxForRow = new int[matrixSize];
    	Arrays.fill(currentMaxForRow, 1);	//lowest weight available for preference is 1
    	costMatrix = new int[matrixSize][matrixSize];
    	
	    while(lineScanner.hasNextLine()) {
	    	myFileLines.add(lineScanner.nextLine());
	    }
    	
    	for(int i = 0; i < myFileLines.size(); i++) {
    		String thisLine = myFileLines.get(i);
    		String[] tokens = thisLine.split("\\s+");
    		for(int j = 1; j < tokens.length; j++) {
    			int projectPreference = Integer.parseInt(tokens[j]);
    			int correctCol;
    			if(projectNumberToMatrixCol.containsKey(projectPreference)){
    				correctCol = projectNumberToMatrixCol.get(projectPreference);
    			}
    			else{
    				correctCol = nextUnusedCol++;
    				projectNumberToMatrixCol.put(projectPreference, correctCol);
    				matrixColsToProjectNumbersMap.put(correctCol, projectPreference);
    			}
				costMatrix[i][correctCol] = currentMaxForRow[i];
				currentMaxForRow[i]++;
    		}
    	}
	    
	    //print(costMatrix);
	    weightUnfilledPreferences();
	    //print(costMatrix);
	    lineScanner.close();
		return costMatrix;
		
	}
	
	
	//TODO - properly map projects with no preferences at all!
	public int getColumnProjectNumber(int col) {
		Integer mappedProjectNum = matrixColsToProjectNumbersMap.get(col);
		if(mappedProjectNum == null) {
			nextUnusedCol++;
			return nextUnusedCol;
		}
		else
			return mappedProjectNum;
	}
	
	private void weightUnfilledPreferences() {
		for(int i = 0; i < matrixSize; i++)
		{
			for(int j = 0; j < matrixSize; j++){
				if(costMatrix[i][j] == 0) {
					costMatrix[i][j] = 2*currentMaxForRow[i];
				}
			}
		}
	}
	
	public static void print(int[][] array)
	{
		for (int[] row : array) 
		{
	        for (int col : row) 
	        {
	            System.out.printf("%5d ", col);
	        }
	        System.out.println();
		}
	}
	
	
}