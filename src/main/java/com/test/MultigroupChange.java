package com.test;

public class MultigroupChange {

	public static int [] projects;
	
	public static int [] multiSort(int [][] groups, int [] multiprojs, int [] amountGroups)
	{
		for(int i = 0; i < multiprojs.length; i++)
		{
			projects = assignForProject(groups, multiprojs[i], amountGroups[i]);
			for(int j = 0; j < projects.length; j++)
			{
				System.out.println(projects[i] + "   " + (i+1));
			}
		}
		return null;
	}
	
	public static int [] assignForProject(int [][] groups, int number, int amount)
	{
		int index = 0, count = 0;
		int [] newProjs  = new int[amount];
		boolean full = full(projects);
		while(full == false)
		{
			for(int i = 0; i < groups.length; i++)
			{
				if(count < newProjs.length)
				{
					//if()
				}
			}
		}
		return projects;
	}
	
	public static boolean full(int [] projs)
	{
		for(int i= 0; i < projs.length; i++)
		{
			if(projs[i] == 0)
			{
				return false;
			}
		}
		return true;
	}
	
	public static int [] addElement(int [] projs, int index, int element)
	{
		//projs[index] 
		return null;
	}
}
