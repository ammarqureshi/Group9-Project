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
	public void threeGroupsContention() {
		String twoGroupPreferences = "2 \n" +
								     "1 1 3 2\n" +	// should get their 2nd (alternative is someone else gets 3rd)
								     "2 2 1 3\n" +  // should get their 1st
								     "3 1 2 3\n";	// should get their 1st
		String result = Hungarian.getAssignments(twoGroupPreferences, null);
		String expected = "1 3\n" +
						  "2 2\n" +
						  "3 1\n";
		assertEquals(expected, result);
	}

}
