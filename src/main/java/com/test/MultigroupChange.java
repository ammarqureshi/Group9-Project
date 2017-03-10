package com.test;

public class MultigroupChange {

	public static int [] projects;
	public static int [][] newGroups;
	
	public static int [][] multiSort(int [][] groups, int [] multiprojs, int [] amountGroups)
	{
		newGroups = groups;
		for(int i = 0; i < multiprojs.length; i++)
		{
			projects = assignForProject(groups, multiprojs[i], amountGroups[i]);
			if(changeMade(groups, newGroups))
			{
				for(int j = 0; j < projects.length; j++)
				{
					System.out.println(projects[j] + "   " + (i+1));
				}
			}
			else
				System.out.println("No change made");
		}
		return newGroups;
	}
	
	public static int [] assignForProject(int [][] groups, int project, int amount)
	{
		int choice = 1,count = 0, max = findMax(groups);
		int [] newProjs  = new int[amount];
		boolean full = full(newProjs);
		while(full == false)
		{	newProjs = assignGroups(groups, newProjs, project,choice,count);
			full = full(newProjs);
			if(full == false)
			{
				count++;
				choice++;
			}
			if (choice > max)
			{
				full = true;
				System.out.println("Error. Could not assign groups");
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
	
	public static int [] assignGroups(int [][] groups,int [] newProjs, int project,int choice,int count)
	{
		System.out.println("Count = " + count);
		for(int i = 0; i < groups.length; i++)
		{
			if(count <= newProjs.length)
			{
				if((groups[i] != null) && (choice < groups[i].length))
				{
					if(groups[i][choice] == project)
					{
						addElement(newProjs,count,i);
						count++;
					}
				}
			}
		}
		return newProjs;
	}
	
	public static int [] addElement(int [] projs, int index, int element)
	{
		if(projs[index] == 0)
		{
			projs[index] = element;
			newGroups[element] = null;
		}
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
			if(groups[i].length >= max)
			{
				max = groups[i].length;
			}
		}
		return max;
	}
}
