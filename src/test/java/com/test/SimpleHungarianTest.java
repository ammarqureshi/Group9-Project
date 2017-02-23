package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SimpleHungarianTest {

	//Simplest test
	@Test
	public void simplestTest() {
		String oneGroupPreference = "1 \n" +
								  	"1 1\n";
		String result = Hungarian.getAssignments(oneGroupPreference, null);
		String expected = "1 1\n";
		assertEquals(expected, result);
	}
	

	//Group names should be arbitrary
	@Test
	public void namingTest() {
		String oneGroupPreference = "1 \n" +
								  	"100 1\n";
		String result = Hungarian.getAssignments(oneGroupPreference, null);
		String expected = "100 1\n";
		assertEquals(expected, result);
	}

	@Test
	public void oneGroupNoPreference() {
		String oneGroupPreference = "1 \n" +
								  	"1\n";
		String result = Hungarian.getAssignments(oneGroupPreference, null);
		String expected = "1 1\n";
		assertEquals(expected, result);
	}

	@Test
	public void twoGroupsFirstChoices() {
		String twoGroupPreferences = "2 \n" +
								     "1 1 2\n" +
								     "2 2 1\n";
		String result = Hungarian.getAssignments(twoGroupPreferences, null);
		String expected = "1 1\n" +
						  "2 2\n";
		assertEquals(expected, result);
	}


	@Test
	public void twoGroupsOneNoPreference() {
		String twoGroupPreferences = "2 \n" +
								     "1 1 2\n" +
								     "2 \n";
		String result = Hungarian.getAssignments(twoGroupPreferences, null);
		String expected = "1 1\n" +
						  "2 2\n";
		assertEquals(expected, result);
	}


	@Test
	public void twoGroupsNoPreferences() {
		String twoGroupPreferences = "2 \n" +
								     "1 \n" +
								     "2 \n";
		String result = Hungarian.getAssignments(twoGroupPreferences, null);
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
		String twoGroupPreferences = "3 \n" +
								     "1 1 2\n" +
								     "2 2 1\n" +
								     "3 3\n";
		String result = Hungarian.getAssignments(twoGroupPreferences, null);
		String expected = "1 1\n" +
						  "2 2\n" +
						  "3 3\n";
		assertEquals(expected, result);
	}

	@Test
	public void threeGroupsFirstChoicesExtraPreferences() {
		String twoGroupPreferences = "5 \n" +
								     "1 1 2 3 4 5\n" +
								     "2 2 3 4 5 1\n" +
								     "3 3 4 5 1 2\n";
		String result = Hungarian.getAssignments(twoGroupPreferences, null);
		String expected = "1 1\n" +
						  "2 2\n" +
						  "3 3\n";
		assertEquals(expected, result);
	}

	//Another check for group names being arbitrary
	@Test
	public void threeGroupsFirstChoicesNamingCheck() {
		String threeGroupPreferences = "3 \n" +
								     "X 1\n" +
								     "Y 2\n" +
								     "Z 3\n";
		String result = Hungarian.getAssignments(threeGroupPreferences, null);
		String expected = "X 1\n" +
						  "Y 2\n" +
						  "Z 3\n";
		assertEquals(expected, result);
	}


	@Test
	public void threeGroupsContention() {
		String twoGroupPreferences = "3 \n" +
								     "1 1 2 3\n" +	// should get their 1st
								     "2 2 1 3\n" +  // should get their 1st
								     "3 1 3 2\n";	// should get their 2nd (alternative is someone else gets 3rd)
		String result = Hungarian.getAssignments(twoGroupPreferences, null);
		String expected = "1 1\n" +
						  "2 2\n" +
						  "3 3\n";
		assertEquals(expected, result);
	}

}
