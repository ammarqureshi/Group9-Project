package com.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AllConstraintsTest {

	@Test
	public void vettingNecessary() {
		
		//this test will fail till Ezi's vetting code is ready
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3\n" +		
				"4 3\n" +	// multigroup takes 3
				"5 P\n" +	// 5 must be assigned
				"OwnProjects\n" +
				"1\n" +	// one own project max
				"3\n" +
				"4\n";	// group 3 or 4 can do own projects
		String groupPreferences = 
				"1 2 4 5\n"  +
				"2 1 3 4 2\n" +
				"3 4 5 2 1\n" +
				"4 OP 4 5 1 2\n" +
				"5 3 1 5 4\n" +
				"6 1 OP 3 5 2\n" +
				"7 OP 1 2\n" +
				"8 5 OP\n";
		String result = MainProgram.getAssignments(groupPreferences, projectInfo);
		String expected = 
				"1 4\n" +
				"2 4\n" +
				"3 4\n" +
				"4 OP\n" +
				"5 3\n" +
				"6 1\n" +
				"7 2\n" +
				"8 5\n";
		
		assertEquals(expected, result);
	}
	
	@Test
	public void priorityNecessary() {
		
		//this test will fail till Thomas's vetting code is ready
		String projectInfo =
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3 P\n";
		
		String groupPreferences = 
				"1 1 2 3\n"  +
				"2 2 1 3\n";
		String result = MainProgram.getAssignments(groupPreferences, projectInfo);
		String expected = 
				"1 3\n" +
				"2 2\n";
		String expected2 = 
				"1 1\n" +
				"2 3\n";
		boolean success = expected.equals(result) || expected2.equals(result);
		assert(success);
	}

}
