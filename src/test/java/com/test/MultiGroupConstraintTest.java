package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MultiGroupConstraintTest {

	@Test
	public void threeGroupsMultigroup() {
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3 3\n";
		String threeGroupsMultigroup = 
				"1 3\n" +
				"2 3\n" +
				"3 3\n";
		String result = MainProgram.getAssignments(threeGroupsMultigroup, projectInfo);
		String expected = 
				"1 3\n" +
				"2 3\n" +
				"3 3\n";
		assertEquals(expected, result);
	}

	@Test
	public void fiveGroupsWithMultigroup() {
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3 3\n";
		String fiveGroupsWithMultigroup = 
				"1 3\n" +
				"2 3\n" +
				"3 3\n" +
				"4 1\n" +
				"5 2\n";
		String result = MainProgram.getAssignments(fiveGroupsWithMultigroup, projectInfo);
		String expected = 
				"1 3\n" +
				"2 3\n" +
				"3 3\n" +
				"4 1\n" +
				"5 2\n";
		assertEquals(expected, result);
	}

	@Test
	public void fiveGroupsTwoMultigroup() {
		String projectInfo =
				"Projects\n" +
				"1 3\n" +
				"2 2\n";
		String fiveGroupsWithMultigroup = 
				"1 1\n" +
				"2 2\n" +
				"3 1\n" +
				"4 1\n" +
				"5 2\n";
		String result = MainProgram.getAssignments(fiveGroupsWithMultigroup, projectInfo);
		String expected = 
				"1 1\n" +
				"2 2\n" +
				"3 1\n" +
				"4 1\n" +
				"5 2\n";
		assertEquals(expected, result);
	}

	@Test
	public void fiveGroupsTwoMultigroupMorePrefs() {
		String projectInfo =
				"Projects\n" +
				"1 3\n" +
				"2 2\n";
		String fiveGroupsWithMultigroup = 
				"1 1 2\n" +
				"2 2 1\n" +
				"3 1 2\n" +
				"4 1 2\n" +
				"5 2 1\n";
		String result = MainProgram.getAssignments(fiveGroupsWithMultigroup, projectInfo);
		String expected = 
				"1 1\n" +
				"2 2\n" +
				"3 1\n" +
				"4 1\n" +
				"5 2\n";
		assertEquals(expected, result);
	}


	@Test
	public void fiveGroupsTwoMultigroupEvenMorePrefs() {
		String projectInfo =
				"Projects\n" +
				"1 3\n" +
				"2 2\n" +
				"3\n" +	//these extra projects should be unassigned by algo
				"4\n";
		String fiveGroupsWithMultigroup = 
				"1 1 2 4 3\n" +
				"2 2 1 3 4\n" +
				"3 1 2 4 3\n" +
				"4 1 2 3 4\n" +
				"5 2 1 3 4\n";
		String result = MainProgram.getAssignments(fiveGroupsWithMultigroup, projectInfo);
		String expected = 
				"1 1\n" +
				"2 2\n" +
				"3 1\n" +
				"4 1\n" +
				"5 2\n";
		assertEquals(expected, result);
	}

	@Test
	public void notAllMultigroupFilled() {
		String projectInfo =
				"Projects\n" +
				"1 3\n" +
				"2 2\n" +
				"3\n" +
				"4\n";
		String fiveGroupsWithMultigroup = 
				"1 1 2 4 3\n" +
				"2 2 1 3 4\n" +
				"3 1 2 4 3\n" +
				"4 4 2 3 1\n" +
				"5 2 1 3 4\n";
		String result = MainProgram.getAssignments(fiveGroupsWithMultigroup, projectInfo);
		String expected = 
				"1 1\n" +
				"2 2\n" +
				"3 1\n" +
				"4 4\n" +
				"5 2\n";
		assertEquals(expected, result);
	}


	@Test
	public void twoMulitProjUnfilled() {
		String projectInfo =
				"Projects\n" +
				"1 3\n" +
				"2 2\n" +
				"3\n" +
				"4\n";
		String fiveGroupsWithMultigroup = 
				"1 1 2 4 3\n" +
				"2 2 1 3 4\n" +
				"3 1 2 4 3\n" +
				"4 4 2 3 1\n" +
				"5 3 1 2 4\n";
		String result = MainProgram.getAssignments(fiveGroupsWithMultigroup, projectInfo);
		String expected = 
				"1 1\n" +
				"2 2\n" +
				"3 1\n" +
				"4 4\n" +
				"5 3\n";
		assertEquals(expected, result);
	}
	
}
