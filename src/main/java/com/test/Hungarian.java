package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JOptionPane;
 
public class Hungarian
{
    private final int[][] costMatrix;
    private final int groups, projects, dim; //rows = groups , projects = cols
    int[] labelByGroup;
	private final int[]   labelByProject;  //labelByWorker= labelByGroup  , labelByJob = labelByProject
    private final int[]      minSlackGroupByProject;
    private final int[]   minSlackValueByProject;
    private final int[]      matchProjectByGroup, matchGroupByProject;
    private final int[]      parentGroupByCommittedProject;
    private final boolean[]  committedGroups;
 
    public Hungarian(int[][] cos)
    {
        this.dim = Math.max(cos.length, cos[0].length);
        this.groups = cos.length;
        this.projects = cos[0].length;
        this.costMatrix = new int[this.dim][this.dim];
        for (int w = 0; w < this.dim; w++)
        {
            if (w < cos.length)
            {
                if (cos[w].length != this.projects)
                {
                    throw new IllegalArgumentException("Irregular cost matrix");
                }
                this.costMatrix[w] = Arrays.copyOf(cos[w], this.dim);
            }
            else
            {
                this.costMatrix[w] = new int[this.dim];
            }
        }
        labelByGroup = new int[this.dim];
        labelByProject = new int[this.dim];
        minSlackGroupByProject = new int[this.dim];
        minSlackValueByProject = new int[this.dim];
        committedGroups = new boolean[this.dim];
        parentGroupByCommittedProject = new int[this.dim];
        matchProjectByGroup = new int[this.dim];
        Arrays.fill(matchProjectByGroup, -1);
        matchGroupByProject = new int[this.dim];
        Arrays.fill(matchGroupByProject, -1);
    }
 
    protected void computeInitialFeasibleSolution()
    {
        for (int j = 0; j < dim; j++)
        {
            labelByProject[j] = (int) Double.POSITIVE_INFINITY;
        }
        for (int w = 0; w < dim; w++)
        {
            for (int j = 0; j < dim; j++)
            {
                if (costMatrix[w][j] < labelByProject[j])
                {
                    labelByProject[j] = costMatrix[w][j];
                }
            }
        }
    }
 
    public int[] execute()
    {
        /*
         * Heuristics to improve performance: Reduce rows and columns by their
         * smallest element, compute an initial non-zero dual feasible solution
         * and
         * create a greedy matching from workers to jobs of the cost matrix.
         */
        reduce();
        computeInitialFeasibleSolution();
        greedyMatch();
        int w = fetchUnmatchedWorker();
        while (w < dim)
        {
            initializePhase(w);
            executePhase();
            w = fetchUnmatchedWorker();
        }
        int[] result = Arrays.copyOf(matchProjectByGroup, groups);
        for (w = 0; w < result.length; w++)
        {
            if (result[w] >= projects)
            {
                result[w] = -1;
            }
        }
        return result;
    }
 
    protected void executePhase()
    {
        while (true)
        {
            int minSlackGroup = -1, minSlackJob = -1;
            double minSlackValue = Double.POSITIVE_INFINITY;
            for (int j = 0; j < dim; j++)
            {
                if (parentGroupByCommittedProject[j] == -1)
                {
                    if (minSlackValueByProject[j] < minSlackValue)
                    {
                        minSlackValue = minSlackValueByProject[j];
                        minSlackGroup = minSlackGroupByProject[j];
                        minSlackJob = j;
                    }
                }
            }
            if (minSlackValue > 0)
            {
                updateLabeling(minSlackValue);
            }
            parentGroupByCommittedProject[minSlackJob] = minSlackGroup;
            if (matchGroupByProject[minSlackJob] == -1)
            {
                /*
                 * An augmenting path has been found.
                 */
                int committedJob = minSlackJob;
                int parentWorker = parentGroupByCommittedProject[committedJob];
                while (true)
                {
                    int temp = matchProjectByGroup[parentWorker];
                    match(parentWorker, committedJob);
                    committedJob = temp;
                    if (committedJob == -1)
                    {
                        break;
                    }
                    parentWorker = parentGroupByCommittedProject[committedJob];
                }
                return;
            }
            else
            {
                /*
                 * Update slack values since we increased the size of the
                 * committed
                 * workers set.
                 */
                int worker = matchGroupByProject[minSlackJob];
                committedGroups[worker] = true;
                for (int j = 0; j < dim; j++)
                {
                    if (parentGroupByCommittedProject[j] == -1)
                    {
                        int slack = costMatrix[worker][j]
                                - labelByGroup[worker] - labelByProject[j];
                        if (minSlackValueByProject[j] > slack)
                        {
                            minSlackValueByProject[j] = slack;
                            minSlackGroupByProject[j] = worker;
                        }
                    }
                }
            }
        }
    }
 
    protected int fetchUnmatchedWorker()
    {
        int w;
        for (w = 0; w < dim; w++)
        {
            if (matchProjectByGroup[w] == -1)
            {
                break;
            }
        }
        return w;
    }
 
    protected void greedyMatch()
    {
        for (int w = 0; w < dim; w++)
        {
            for (int j = 0; j < dim; j++)
            {
                if (matchProjectByGroup[w] == -1
                        && matchGroupByProject[j] == -1
                        && costMatrix[w][j] - labelByGroup[w] - labelByProject[j] == 0)
                {
                    match(w, j);
                }
            }
        }
    }
 
    protected void initializePhase(int w)
    {
        Arrays.fill(committedGroups, false);
        Arrays.fill(parentGroupByCommittedProject, -1);
        committedGroups[w] = true;
        for (int j = 0; j < dim; j++)
        {
            minSlackValueByProject[j] = costMatrix[w][j] - labelByGroup[w]
                    - labelByProject[j];
            minSlackGroupByProject[j] = w;
        }
    }
 
    

	protected void match(int w, int j)
    {
        matchProjectByGroup[w] = j;
        matchGroupByProject[j] = w;
    }
 
    protected void reduce()
    {
        for (int w = 0; w < dim; w++)
        {
            double min = Double.POSITIVE_INFINITY;
            for (int j = 0; j < dim; j++)
            {
                if (costMatrix[w][j] < min)
                {
                    min = costMatrix[w][j];
                }
            }
            for (int j = 0; j < dim; j++)
            {
                costMatrix[w][j] -= min;
            }
        }
        double[] min = new double[dim];
        for (int j = 0; j < dim; j++)
        {
            min[j] = Double.POSITIVE_INFINITY;
        }
        for (int w = 0; w < dim; w++)
        {
            for (int j = 0; j < dim; j++)
            {
                if (costMatrix[w][j] < min[j])
                {
                    min[j] = costMatrix[w][j];
                }
            }
        }
        for (int w = 0; w < dim; w++)
        {
            for (int j = 0; j < dim; j++)
            {
                costMatrix[w][j] -= min[j];
            }
        }
    }
 
    protected void updateLabeling(double slack)
    {
        for (int w = 0; w < dim; w++)
        {
            if (committedGroups[w])
            {
                labelByGroup[w] += slack;
            }
        }
        for (int j = 0; j < dim; j++)
        {
            if (parentGroupByCommittedProject[j] != -1)
            {
                labelByProject[j] -= slack;
            }
            else
            {
                minSlackValueByProject[j] -= slack;
            }
        }
    }

    
    public static String getAssignments(String preferences, String groupDescriptions) {
    	InputHandel inputH = new InputHandel();
    	
        int[][] cos = inputH.parseInput(preferences);
		Hungarian hbm = new Hungarian(cos);
		int[] result = hbm.execute();
		
		HashMap<Integer, String> groupRowToName = groupRowsToNamesMap(preferences);
		
		String resultString = "";
	 	int r = inputH.matrixSize;
        for(int i=0;i<r; i++)
        {
        	int j = result[i];
        	//group name followed by project number
        	if (groupRowToName.get(i) != null) {
        		String groupRowName = groupRowToName.get(i);
        		int projectNum = inputH.getColumnProjectNumber(j);
            	resultString += groupRowName + " " + projectNum + "\n";
        	}
        	
        }
        return resultString;
    }
    
    private static HashMap<Integer, String> groupRowsToNamesMap(String preferences) {
		HashMap<Integer, String> groupNamesToMatrixRow = new HashMap<Integer, String>();
	    Scanner lineScanner = new Scanner(preferences);
	    lineScanner.nextLine(); //skip first line
	    int i = 0;
	    while(lineScanner.hasNextLine()) {
	    	// first token is groupname
	    	String groupName = lineScanner.nextLine().split(" ")[0];
	    	groupNamesToMatrixRow.put(i, groupName);
	    	i++;
	    }
	    lineScanner.close();
	    return groupNamesToMatrixRow;
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
	/*1) Read project info, for each multigroup project add enough extra "fake" projects so groups can be assigned.
         2) Every time a group has a preference for a multigroup project, add the same weight to each of the "fake" projects.
         3) Ensure these map back to the correct project number on outputs
*/
    public static int[][] CreateMultiGroups(String preferences) {
    	String info = getStringFromFile("testProjectsInfo.txt");
    	int[][] multiGroups = {{}};
    	Scanner stringScan = new Scanner(info);
    	String[] projectInfo = info.split("\n");	//splits the info into an array for easier to understand processing
    	int endOfMultiGroups = 0;	//this and the following loop find where the multigroup info ends in the file by searching for the own projects header
    	for (int i = 0; i<projectInfo.length; i++) {
    		if (projectInfo[i]=="OwnProjects") {
    			endOfMultiGroups = i;
    		}
    	}
    	for (int i = 2; i<endOfMultiGroups; i++) {
    		stringScan = new Scanner(projectInfo[i]);
    		int proNum = stringScan.nextInt();
    		if (stringScan.hasNextInt()) {
    			int numOfGroups = stringScan.nextInt();
    			int[] newMGroup = new int[numOfGroups+1];
    			newMGroup[0] = proNum;
    			Scanner prefScanner = new Scanner(preferences);
    			//increase numOfProjectsInPreferences
    			int numOfProjs = prefScanner.nextInt();
    			//numOfProjs= numOfGroups;
    			preferences = numOfProjs + preferences.substring(1, preferences.length());
    			//create new project with highest number
    			String[]groupPrefs = preferences.split("\n");
    			for (int j = 1; j<groupPrefs.length; j++) {
    				prefScanner = new Scanner(groupPrefs[j]);
    				int groupNum = prefScanner.nextInt();
    				while(prefScanner.hasNextInt()) {
    					if (proNum == prefScanner.nextInt()) {
    						int index = groupPrefs[j].indexOf(proNum);
    						String newPref = groupPrefs[j].substring(0,index);
    						int l = 1;
    						//change preferences strings
    						for (int k = numOfProjs+1; k<numOfProjs+numOfGroups; k++) {
    							newPref += ' ' + k;
    							newMGroup[l] = k;
    							l++;
    						}
    						newPref += groupPrefs[j].substring(index+numOfGroups, groupPrefs[j].length());
    						groupPrefs[j] = newPref;
    					}
    				}
    			}
    			//save number to multiGroup array
    			multiGroups[i-2] = newMGroup;
    			
    		}
    	}
    	return multiGroups;
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
	/*This main will implement the prioritisation of projects and generating happiness score.
	public static void main(String[] args) throws Exception
    	{
    	boolean priorityProjectsFilled = false;
    	while (priorityProjectsFilled == false) {
    	//run with command line args with first and second file
    	String result = assignFromFileInput(args[0], "not implemented yet but will be args[1]");
    	String preferences = getStringFromFile(args[0], "not implemented yet but will be args[1]");
    	System.out.println(result);
    	HungarianHappiness happy = new Hungarian Happiness();
    	PrioritiseProjects priority = new PrioritiseProjects();
    	if (priority.AllAllocated(results, "not yet implemented but int array of priority projects") == true {
    		double[] happyScore = happy.happinessScore(preferences, result);
    		System.out.println("All priority projects allocated! \nThe happiness score is " + happyScore[0] 
    			+ "% happy, " + happyScore[1] + "% middling, " + happyScore[2] + "% unhappy.");
    			priorityProjectsFilled = true;
    	}
    	else {
    		double[] happyScore = happy.happinessScore(preferences, result);
    		System.out.println("Not all priority projects allocated! \nThe happiness score is " + happyScore[0] 
    			+ "% happy, " + happyScore[1] + "% middling, " + happyScore[2] + "% unhappy.");    		
    	}
	 }*/
       
}

