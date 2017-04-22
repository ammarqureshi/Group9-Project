import java.util.Random;

public class GreedySort {
	
//	String [] projects;
//	double [] highScores;
	
	/**
	 * Parameters: projs[] = projects that need groups assigned to them
	 * 			   groups[] = groups to be assigned to projects
	 * @return array of sorted groups with projects
	 */
	public static String [] (String [] groups, String [] projects)
	{
		HungarianHappiness happy = new HungarianHappiness();
		String [] sorted = new String [groups.length];			//final array of sorted groups, sorted[0] = first groups preference
		String [] oneElem = new String[1];						//1 elem array for testing each score
		int numOfProjects = projects.length;
		int i,j,nextFree;										//nextFree = next free array space
		double score = 0, highScore = 0;
		//String results = "";
//		for(int i = 0; i < projects.length; i++)
//		{
//			results = results + projects[i];
//		}
		//highScores = new double[groups.length];
		//projects = new String[prjs.length];
		for(i = 0; i < groups.length; i++)
		{
			oneElem = groups[i];
			for(j = 0; j < projects.length; j++)
			{
				score = happy.happinessScore(oneElem, projects[j], numOfProjects);		//if new score > old highScore, store new project in sorted
				if(score >=  highScore)
				{
					highScore = score; 
					sorted[i] = projects[j];
				}
			}
		}
		for(i = 0; i < sorted.length; i++)
		{
			for(j = i+1; j < sorted.length; i++)
			{
				if(sorted[i] == sorted[j])
				{
					nextFree = findNextFree(sorted, projects);
					if(nextFree == -1)
					{
						System.out.print("Error - no free projects available for group " + i + "\nAssigning random project.");
						nextFree = Math.random(projects.length - 1);
					}
					else sorted[i] = projects[nextFree];
				}
			}
		}
		return sorted;
	}
	
	/**
	 * 
	 * @param groups - all groups and preferences
	 * @param projects - all project numbers
	 * @return next project not taken
	 */
	public static int findNextFree(String [] groups, String [] projects)
	{
		int freeIndex = 0;
		boolean freeFound = false;
		for(int i = 0; i < projects.length; i++)
		{
			for(int j = 0; j < groups.length; j++)
			{
				if(groups[j] != projects[i])
				{
					//freeFound = true;
					if(projects[freeIndex] != groups[j])			//if group preference != project at projects[i] and
					{												//isn't already a taken project, set to new free index.
						freeFound = true;
						freeIndex = i;
					}
				}
				//else freeFound = false;
			}
		}
		if(freeFound == true)							//if free index found , return index
		{
			return freeIndex;
		}
		else return -1;									// else return error warning.
	}
	
}
