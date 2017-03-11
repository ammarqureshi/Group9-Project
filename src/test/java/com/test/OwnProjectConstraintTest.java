package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OwnProjectConstraintTest {

	// TODO - check for vetting, waiting for Ezi's code
	
	@Test
	public void simplest() {
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"OwnProjects\n" +
				"1\n" +
				"1\n";
		String oneGroup = 
				"1 OP\n";
		String result = MainProgram.getAssignments(oneGroup, projectInfo);
		String expected = 
				"1 OP\n";
		assertEquals(expected, result);
	}

	@Test
	public void twoGroupsOneOP() {
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"OwnProjects\n" +
				"1\n" +
				"1\n";
		String groupPreferences = 
				"1 OP\n" + 
				"2 1\n";
		String result = MainProgram.getAssignments(groupPreferences, projectInfo);
		String expected = 
				"1 OP\n" + 
				"2 1\n";
		assertEquals(expected, result);
	}


	@Test
	public void twoGroupsTwoOP() {
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"OwnProjects\n" +
				"2\n" +
				"1\n" +
				"2\n";
		String groupPreferences = 
				"1 OP 1\n" + 
				"2 OP 1\n";
		String result = MainProgram.getAssignments(groupPreferences, projectInfo);
		String expected = 
				"1 OP\n" + 
				"2 OP\n";
		assertEquals(expected, result);
	}


	@Test
	public void threeGroupsTwoOP() {
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"OwnProjects\n" +
				"2\n" +
				"1\n" +
				"2\n";
		String groupPreferences = 
				"1 OP 1\n" + 
				"2 OP 1\n" + 
				"3 1\n";
		String result = MainProgram.getAssignments(groupPreferences, projectInfo);
		String expected = 
				"1 OP\n" + 
				"2 OP\n" + 
				"3 1\n";
		assertEquals(expected, result);
	}


	@Test
	public void unfilledOP() {
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"OwnProjects\n" +
				"2\n" +
				"1\n" +
				"2\n";
		String groupPreferences = 
				"1 OP 1\n" + 
				"2 1\n";
		String result = MainProgram.getAssignments(groupPreferences, projectInfo);
		String expected = 
				"1 OP\n" + 
				"2 1\n";
		assertEquals(expected, result);
	}

}
