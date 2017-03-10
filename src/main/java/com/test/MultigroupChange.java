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
	
	public static int [] assignForProject(int [][] groups, int project, int amount)
	{
		int choice = 1, count = 0;
		int [] newProjs  = new int[amount];
		boolean full = full(newProjs);
		if(full == false)
		{
			for(int i = 0; i < groups.length; i++)
			{
				if(count <= newProjs.length)
				{
					if(groups[i][choice] == project)
					{
						addElement(newProjs,count,i);
						count++;
					}
					full = full(newProjs);
				}
				else
				{
					full = true;
				}
			}
		}
		return newProjs;
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
	
	public static int [] assignGroups(int [][] groups, int count,int [] newProjs, int choice, int project)
	{
		for(int i = 0; i < groups.length; i++)
		{
			if(count <= newProjs.length)
			{
				if(groups[i][choice] == project)
				{
					addElement(newProjs,count,i);
					count++;
				}
			}
		}
		return newProjs;
	}
	public static int [] addElement(int [] projs, int index, int element)
	{
		projs[index] = element;
		return projs;
	}
}
