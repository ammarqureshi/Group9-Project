package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleHungarianTest {

	//Simplest test
	@Test
	public void simplestTest() {
		String projectInfo = 
				"Projects\n" +
				"1\n";
		String oneGroupPreference = "1 1\n";
		String result = MainProgram.getAssignments(oneGroupPreference, projectInfo);
		String expected = "1 1\n";
		assertEquals(expected, result);
	}
	

	//Group names should be arbitrary
	@Test
	public void namingTest() {
		String projectInfo = 
				"Projects\n" +
				"1\n";
		String oneGroupPreference = "100 1\n";
		String result = MainProgram.getAssignments(oneGroupPreference, projectInfo);
		String expected = "100 1\n";
		assertEquals(expected, result);
	}

	@Test
	public void oneGroupNoPreference() {
		String projectInfo = 
				"Projects\n" +
				"1\n";
		String oneGroupPreference = "1\n";
		String result = MainProgram.getAssignments(oneGroupPreference, projectInfo);
		String expected = "1 1\n";
		assertEquals(expected, result);
	}

	@Test
	public void twoGroupsFirstChoices() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n";
		String twoGroupPreferences = "1 1 2\n" +
								     "2 2 1\n";
		String result = MainProgram.getAssignments(twoGroupPreferences, projectInfo);
		String expected = "1 1\n" +
						  "2 2\n";
		assertEquals(expected, result);
	}


	@Test
	public void twoGroupsOneNoPreference() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n";
		String twoGroupPreferences = "1 1 2\n" +
								     "2 \n";
		String result = MainProgram.getAssignments(twoGroupPreferences, projectInfo);
		String expected = "1 1\n" +
						  "2 2\n";
		assertEquals(expected, result);
	}


	@Test
	public void twoGroupsNoPreferences() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n";
		String twoGroupPreferences ="1 \n" +
								     "2 \n";
		String result = MainProgram.getAssignments(twoGroupPreferences, projectInfo);
		//doesnt matter which as long as one of these two!
		String expected1 = "1 1\n" +
				           "2 2\n";
		String expected2 = "1 2\n" +
				   		   "2 1\n";
		boolean success = result.equals(expected1) || result.equals(expected2);
		assert(success);
	}

	@Test
	public void threeGroupsFirstChoices() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3\n";
		String twoGroupPreferences = "1 1 2\n" +
								     "2 2 1\n" +
								     "3 3\n";
		String result = MainProgram.getAssignments(twoGroupPreferences, projectInfo);
		String expected = "1 1\n" +
						  "2 2\n" +
						  "3 3\n";
		assertEquals(expected, result);
	}

	@Test
	public void threeGroupsFirstChoicesExtraPreferences() {
		String projectInfo = 
			"Projects\n" +
			"1\n" +
			"2\n" +
			"3\n" +
			"4\n" +
			"5\n";
		String twoGroupPreferences = "1 1 2 3 4 5\n" +
								     "2 2 3 4 5 1\n" +
								     "3 3 4 5 1 2\n";
		String result = MainProgram.getAssignments(twoGroupPreferences, projectInfo);
		String expected = "1 1\n" +
						  "2 2\n" +
						  "3 3\n";
		assertEquals(expected, result);
	}

	//Another check for group names being arbitrary
	@Test
	public void threeGroupsFirstChoicesNamingCheck() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3\n";
		String threeGroupPreferences = 
				 "X 1\n" +
			     "Y 2\n" +
			     "Z 3\n";
		String result = MainProgram.getAssignments(threeGroupPreferences, projectInfo);
		String expected = "X 1\n" +
						  "Y 2\n" +
						  "Z 3\n";
		assertEquals(expected, result);
	}


	@Test
	public void threeGroupsContention() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3\n";
		String twoGroupPreferences = "1 1 2 3\n" +	// should get their 1st
								     "2 2 1 3\n" +  // should get their 1st
								     "3 1 3 2\n";	// should get their 2nd (alternative is someone else gets 3rd)
		String result = MainProgram.getAssignments(twoGroupPreferences, projectInfo);
		String expected = "1 1\n" +
						  "2 2\n" +
						  "3 3\n";
		assertEquals(expected, result);
	}
	@Test
	public void withoutGivenGroupNames() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"4\n" +
				"5\n" +
				"6\n" +
				"7\n" +
				"8\n" +
				"9\n" +
				"10\n" +
				"11\n" +
				"12\n" +
				"13\n" +
				"14\n" +
				"15\n" +
				"16\n";
		String lastYears =
			"1 10 11 14\n" +
			" 2 14 15 9 7 5 11\n" +
			"3 6 5 4 9 3\n" +
			" 9 15 5 1 8 4\n" +
			"5 1 9 3 5 7\n" +
			" 14 1 3 5 9 7\n" +
			"7 14 15 7 11 3 13 9\n" +
			" 3 9 5 1 2\n" +
			"9 14 6 9 11 3\n" +
			" 9 4 1 2 7\n" +
			"11 9 12 5 16 14 1\n" +
			"1 14 1 2 8 13\n" +
			"13 5 9 6 1 12 8 15 4\n" +
			"1 9 7 6 8 14 15 5 4 16 1 2 3 11 13 10 12\n" +
			"15 14 7 9 2\n";
		String result = MainProgram.getAssignments(lastYears, projectInfo);
		String expected = 
				"1 10\n" + 
				" 2\n" +
				"3 6\n" +
				" 9\n" +
				"5 1\n" +
				" 14\n" +
				"7 15\n" +
				" 3\n" +
				"9 11\n" +
				" 4\n" +
				"11 12\n" +
				"1 13\n" +
				"13 5\n" +
				"1 8\n" +
				"15 7\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void lastYearsPreferences() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"4\n" +
				"5\n" +
				"6\n" +
				"7\n" +
				"8\n" +
				"9\n" +
				"10\n" +
				"11\n" +
				"12\n" +
				"13\n" +
				"14\n" +
				"15\n" +
				"16\n";
		String lastYears =
			"1 10 11 14\n" +
			"2 2 14 15 9 7 5 11\n" +
			"3 6 5 4 9 3\n" +
			"4 9 15 5 1 8 4\n" +
			"5 1 9 3 5 7\n" +
			"6 14 1 3 5 9 7\n" +
			"7 14 15 7 11 3 13 9\n" +
			"8 3 9 5 1 2\n" +
			"9 14 6 9 11 3\n" +
			"10 9 4 1 2 7\n" +
			"11 9 12 5 16 14 1\n" +
			"12 14 1 2 8 13\n" +
			"13 5 9 6 1 12 8 15 4\n" +
			"14 9 7 6 8 14 15 5 4 16 1 2 3 11 13 10 12\n" +
			"15 14 7 9 2\n";
		String result = MainProgram.getAssignments(lastYears, projectInfo);
		String expected = 
				"1 10\n" + 
				"2 2\n" +
				"3 6\n" +
				"4 9\n" +
				"5 1\n" +
				"6 14\n" +
				"7 15\n" +
				"8 3\n" +
				"9 11\n" +
				"10 4\n" +
				"11 12\n" +
				"12 13\n" +
				"13 5\n" +
				"14 8\n" +
				"15 7\n";
		assertEquals(expected, result);
	}
	
	@Test
	public void lastYearsPreferencesWithWeirdGroupNames() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"4\n" +
				"5\n" +
				"6\n" +
				"7\n" +
				"8\n" +
				"9\n" +
				"10\n" +
				"11\n" +
				"12\n" +
				"13\n" +
				"14\n" +
				"15\n" +
				"16\n";
		String lastYears =
			"itty 10 11 14\n" +
			"2 2 14 15 9 7 5 11\n" +
			"<3 6 5 4 9 3\n" +
			"4babyz 9 15 5 1 8 4\n" +
			"options 1 9 3 5 7\n" +
			"bitty 14 1 3 5 9 7\n" +
			"-1 14 15 7 11 3 13 9\n" +
			"thanks 3 9 5 1 2\n" +
			"9 14 6 9 11 3\n" +
			"10 9 4 1 2 7\n" +
			"spider 9 12 5 16 14 1\n" +
			":) 14 1 2 8 13\n" +
			"chancers 5 9 6 1 12 8 15 4\n" +
			"squad 9 7 6 8 14 15 5 4 16 1 2 3 11 13 10 12\n" +
			"group 14 7 9 2\n";
		String result = MainProgram.getAssignments(lastYears, projectInfo);
		String expected = 
				"itty 10\n" + 
				"2 2\n" +
				"<3 6\n" +
				"4babyz 9\n" +
				"options 1\n" +
				"bitty 14\n" +
				"-1 15\n" +
				"thanks 3\n" +
				"9 11\n" +
				"10 4\n" +
				"spider 12\n" +
				":) 13\n" +
				"chancers 5\n" +
				"squad 8\n" +
				"group 7\n";
		assertEquals(expected, result);
	}

}
