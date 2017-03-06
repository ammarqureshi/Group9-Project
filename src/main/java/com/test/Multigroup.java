package com.test;

import java.util.Random;

public class Multigroup {
	
	private int [] projects;
	private int [] groupPrefs;
//	double [] highScores;
	
	/**
	 * Parameters: multiprojs[] = multigroup projects that need groups assigned to them
	 * 			   groups[][] = groups to be assigned to projects, and preferences
	 * @return array of sorted groups with projects
	 */
	public int [] multiSort(int [][] groups, int [] multiprojs)
	{
		projects = multiprojs;
		groupPrefs = new int[groups.length];
		int i, j, count = 0;
		boolean free = false, allTaken = false;
		for(i = 0; i < groups.length; i++)
		{
			for(j = 0; j < multiprojs.length; j++)
			{
				if(groups[i][0] == multiprojs[j])			//if(group[i] has prefernce in multiproj)
				{
					groupPrefs[i] = multiprojs[j];			//group[i] preference = project at index j
				}
			}
		}
		
		for(i = 0; i < projects.length; i++)
		{
			for(j = 0; j < groupPrefs.length; j++)
			{
				if(groupPrefs[j] == i)
				{
					projects[i] = j + 1;		//assigns group to project at index i
				}
			}
		}
		
		i=0;
		while(allTaken == false)			//while(not all projects taken
		{
			while(free == false)			//while(no free spaces)
			{
				if(i == projects.length)
				{							//no free projects found, allTaken = true 
					allTaken = true;
				}
				else
				{
					if(projects[i] == 0)
					{
						free = true;		//if project at index i not taken,
					}
					i++;
				}
			}
			projects = nextPrefs(projects, groups);
			
			free = false;
		}
		

		return projects;
	}
	/**
	 * 
	 * @param projs - multiprojs, some allocated, some no allocated
	 * @param groups	- all groups 
	 * @return projs - all filled
	 */
	public static int [] nextPrefs(int [] projs, int [][] groups)
	{
		boolean freeSpace = false;			
		boolean free = true;
		int [] unchosenProjs, unchosenGroups;
		int i,j, freeProjs = 0, pindex = 0, gindex = 0;
		int indCount= 0;
		for(i = 0; i < projs.length; i++)
		{
			if(projs[i] == 0)
			{
				freeProjs++;			//count how many unchosen projects there are
			}

		}
		
		unchosenProjs = new int[freeProjs];		//array of unchosen projects
		unchosenGroups = new int[(groups.length - projs.length) + freeProjs];	//array of groups not assigned to project
		
		for(i = 0; i < groups.length;i++)
		{
			for(j = 0; j < projs.length;j++)
			{
				if(projs[j] == i)				//if group already assigned, dont count
				{
					free = false;
				}
			}
			if(free == true)
			{
				unchosenGroups[gindex] = i + 1;		//if(group unassigned) add to unchosen groups array
				gindex++;
			}
			free = true;					//reset free value
		}
		
		for(i = 0; i < projs.length; i++)
		{
			if(projs[i] == 0)
			{
				unchosenProjs[pindex] = i + 1;			//puts unassigned projects into array
				pindex++;
			}
		}
		
		while(freeSpace == true)
		{
			free = false;
			pindex =0; gindex = 0;
			while(pindex < unchosenProjs.length)		//while(all projects not assigned)
			{
				if(gindex == unchosenGroups.length)		//if(project not found at this preference level)
				{										//   increment preference level
					gindex = 0;							//	 reset group index
					indCount++;
				}
				if(indCount + 1 == groups[unchosenGroups[gindex]].length)
				{
					gindex++;
				}
				else
				{
					if(unchosenProjs[pindex] == groups[unchosenGroups[gindex]][indCount + 1])
					{															//if(match found)
						projs[unchosenProjs[pindex]] = unchosenGroups[gindex];	//project taken by group
						pindex++;									//increment to next unchosen project
					}
					else
					{
						gindex++;									//no match found, increment group
					}
				}
			}
			for(i = 0; i < projs.length; i++)
			{
				if(projs[i]== 0)					//searches for free project, i.e not chosen
				{
					free = true;
				}
			}
			if(free != true)
			{
				freeSpace = false;				//if(no free spaces) all projects are taken , return projects
			}
			indCount++;
		}
		
		return projs;
	}

}
