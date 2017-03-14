package com.test;

import java.util.Scanner;

public class MultigroupStrings {

	public static int [] projects;
	public static int [][] newGroups;
	
	public static String multiSort(String groups, String multiPs, String amountGroups)
	{
		int mulLength = 0, amountLength = 0,i; 
		int [] multiprojects, amounts;
		parsetoArray(groups);
		//InputHandel input = new InputHandel();
		//newGroups = input.parseInput(groups);
		//System.out.println(newGroups.length);
		
		Scanner mulScanner = new Scanner(multiPs);
		Scanner amScanner = new Scanner(amountGroups);
		while(mulScanner.hasNextInt())
		{
			mulLength++;
			mulScanner.nextInt();
			//System.out.println(mulLength);
		}
		
		while(amScanner.hasNextInt())
		{
			amountLength++;
			amScanner.nextInt();
		}
		
		multiprojects = new int [mulLength];
		amounts = new int [amountLength];
		
		Scanner mulScan = new Scanner(multiPs);
		Scanner amScan = new Scanner(amountGroups);
		
		for(i = 0; i < mulLength; i++)
		{
			multiprojects[i] = mulScan.nextInt();
			//System.out.println(multiprojects[i]);
		}
		
		for(i = 0; i < amountLength; i++)
		{
			amounts[i] = amScan.nextInt();
			//System.out.println(amounts[i]);
		}
		
		//newGroups = new int[groups.length()
		//System.out.println(newGroups);
//		newGroups = groups;
//		for(int i = 0; i < groups.length; i++)
//		{
//			for(int j =0; j < groups[i].length; j++)
//			{
//				System.out.println("Group: " + groups[i][j]);
//			}
//		}
		
//		for(int j =0; j < amountGroups.length; j++)
//		{
//			System.out.println("Groups per proj : " + amountGroups[j]);
//		}
		if(mulLength == amountLength)
		{
			for(i = 0; i < mulLength; i++)
			{
				//System.out.println("Running for project: " + multiprojs[i]);
				projects = assignForProject(multiprojects[i], amounts[i]);
	//			if(changeMade(groups, newGroups))
	//			{
				if(projects != null)
				{
					for(int j = 0; j < projects.length; j++)
					{
						System.out.println(multiprojects[i] + "   " + projects[j]);
					}
				}
	//				if(newGroups[i]== null)
	//				{
	//					System.out.println("newGroups " + i + " = " + newGroups[i]);
	//				}
	//			}
	//			else
	//				System.out.println("No change made");
			}
			groups = parsetoString(newGroups);
			return groups;
		}
		else 
			return null;
	}
	
	public static void parsetoArray(String groups)
	{
		String [] x = groups.split(";");
		
		newGroups = new int[x.length][];
		//System.out.println(x.length);
		for(int i = 0; i < x.length; i++)
		{
			String[] row = x[i].split(" ");
			newGroups[i] = new int[row.length];
			//System.out.println(row.length);
			for(int j = 0; j < row.length; j++)
			{
				newGroups[i][j] = Integer.parseInt(row[j]);
				//System.out.print(newGroups[i][j] + " ");
			}
			//System.out.print("\n");
		}
	}
	
	public static String parsetoString(int [][] groups)
	{
		String newString = "";
		for(int i = 0; i < groups.length; i++)
		{	
			if(groups[i] == null)
			{
				newString = newString + "null";
				//System.out.println(groups[i]);
			}
			else
			{
				System.out.println(newString);
					for(int j = 0; j < groups[j].length; j++)
					{
						newString = newString + " " + groups[i][j];
					}
			}
			newString = newString + "\n";
		}
		return newString;
	}
	public static int [] assignForProject(int project, int amount)
	{
		//System.out.println("Project: " + project + " Amount: " + amount);
		int choice = 0, max = findMax(newGroups);
		int [] newProjs  = new int[amount];
		boolean full = full(newProjs);
		while(full == false)
		{	
			newProjs = assignGroups(newGroups, newProjs, project,choice);
			full = full(newProjs);
			if(full == false)
			{
				//count++;
				choice++;
				if (choice > max)
				{
					full = true;
					//System.out.println("Error. Could not assign groups for project " + project);
					newProjs = null;
				}
			}
		}
		return newProjs;
	}
	
	public static boolean full(int [] projs)
	{
		for(int i= 0; i < projs.length; i++)
		{
			//System.out.println("Checking if full: " + projs[i]);
			if(projs[i] == 0)
			{
				//System.out.println("Not full");
				return false;
			}
		}
		return true;
	}
	
	public static int [] assignGroups(int [][] groups,int [] newProjs, int project,int choice)
	{
		int count =0;
		//boolean assigned;
		//System.out.println("Size = " + newProjs.length);
		for(int i = 0; i < newProjs.length; i++)
		{
			//System.out.println("Next Project: " + newProjs[i]);
			//assigned = false;
			for(int j = 0; j < groups.length; j++)
			{
				if((groups[j] != null)/* && (assigned == false)*/)
				{
					if((choice < groups[j].length) && (newProjs[i] == 0))
					{
						//System.out.println("DIS NIGGA CALLED");
						if(groups[j][choice] == project)
						{
							//System.out.println("Add element.");
							addElement(newProjs,i,j+1);
							//assigned = true;
						}
					}
				}
			}
//			if(count <= newProjs.length)
//			{
//				if((groups[i] != null) && (choice < groups[i].length))
//				{
//					if(groups[i][choice] == project)
//					{
//						addElement(newProjs,count,i);
//						count++;
//					}
//				}
//			}
		}
		return newProjs;
	}
	
	public static int [] addElement(int [] projs, int index, int element)
	{
		if(projs[index] == 0)
		{
			projs[index] = element;
			newGroups[element - 1] = null;
			//System.out.println("Element added: " + projs[index]);
		}
		//System.out.println("Element added, list is: " + full(projs));
		return projs;
	}
	
	public static boolean changeMade(int [][] groups, int [][] newGroups)
	{
		for(int i = 0; i < groups.length;i++)
		{
			for(int j = 0; j < groups[i].length;j++)
			{
				if(newGroups[i][j] != groups[i][j])
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static int findMax(int [][] groups)
	{
		int max = 0;
		for(int i = 0; i < groups.length; i++)
		{
			if((groups[i] != null) && (groups[i].length >= max))
			{
				max = groups[i].length;
			}
		}
		return max;
	}
}
