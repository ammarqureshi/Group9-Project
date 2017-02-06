import static org.junit.Assert.*;

import org.junit.Test;

public class HungarianHappinessTest {

	@Test
	public void Onegroup() {
		String[] groups = new String[]{
				"1 1 2 3 4 5",};
		String results = "1";
		int numOfProjects = 5;
		assertEquals(100, HungarianHappiness.happinessScore(groups, results, numOfProjects), 0);
	}
	
	//Every group gets their first preference
	@Test
	public void score100() {
		String[] groups = new String[]{
				"1 1 2 3 4 5",
				"2 2 1 3 4 5",
				"3 3 2 1 4 5",
				"4 4 2 3 1 5",
				"5 5 2 3 4 1"
		};
		String results = "1 2 3 4 5";
		int numOfProjects = 5;
		assertEquals(100, HungarianHappiness.happinessScore(groups, results, numOfProjects), 0);
	}
	
	@Test
	public void score56() {
		String[] groups = new String[]{
				"1 1 2 3 4 5",
				"2 2 1 3 4 5",
				"3 3 2 1 4 5",
				"4 4 2 3 1 5",
				"5 5 2 3 4 1"
		};
		String results = "3 1 2 5 4";
		int numOfProjects = 5;
		assertEquals(56, HungarianHappiness.happinessScore(groups, results, numOfProjects), 0);
	}
	
	@Test
	public void score72() {
		String[] groups = new String[]{
				"1 1 2 3 4 5",
				"2 2 1 3 4 5",
				"3 3 2 1 4 5",
				"4 4 2 3 1 5",
				"5 5 2 3 4 1"
		};
		String results = "1 2 3 5 4";
		int numOfProjects = 5;
		assertEquals(72, HungarianHappiness.happinessScore(groups, results, numOfProjects), 0);
	}
	
	//Every group gets there last preference
	@Test
	public void score20() {
		String[] groups = new String[]{
				"1 5 2 3 4 1",
				"2 5 1 3 4 2",
				"3 5 2 1 4 3",
				"4 5 2 3 1 4",
				"5 4 2 3 1 5"
		};
		String results = "1 2 3 4 5";
		int numOfProjects = 5;
		assertEquals(20, HungarianHappiness.happinessScore(groups, results, numOfProjects), 0);
	}

}
