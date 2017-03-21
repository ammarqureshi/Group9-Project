package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PriorityProjectConstraintTests {

	@Test
	public void simple() {
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3 P\n";	// 3 must be assigned
		String groupPreferences = 
				"1 1 2 3\n"  +
				"2 2 3 1 2\n";
		String result = MainProgram.getAssignments(groupPreferences, projectInfo);
		String expected = 
				"1 1\n" +
				"2 3\n";
		assertEquals(expected, result);
	}

	@Test
	public void multiGroupPriority() {
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3 P 2\n";	// 3 must be assigned twice
		String groupPreferences = 
				"1 1 2 3\n"  +
				"2 2 3 1 2\n";
		String result = MainProgram.getAssignments(groupPreferences, projectInfo);
		String expected = 
				"1 3\n" +
				"2 3\n";
		assertEquals(expected, result);
	}


	@Test
	public void multiGroupPrioritySilly() {
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3 P 5\n";	// 3 must be assigned five times
		String groupPreferences = 
				"1 1 2 3\n"  + // preferences actually irrelevant they're all getting proj 3
				"2 2 3 1 2\n"+
				"3 2 3 1 2\n"+
				"4 2 3 1 2\n"+
				"5 2 3 1 2\n";
		String result = MainProgram.getAssignments(groupPreferences, projectInfo);
		String expected = 
				"1 3\n" +
				"2 3\n"+
				"3 3\n"+
				"4 3\n"+
				"5 3\n";
		assertEquals(expected, result);
	}
	
}
