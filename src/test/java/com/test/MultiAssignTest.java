package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class MultiAssignTest {

	@Test
	public void NoContentionForTwoMP() {
	
		String projDesc = new String("Projects\n"
				+ "7\n"
				+ "5 2\n"		//takes in 2 groups
				+ "2\n"
				+ "4\n"
				+ "10\n"
				+ "6\n"
				+ "9\n"
				+ "7\n"
				+ "OwnProjects\n"
				);
		
		String groupPrefs = new String("7\n"
				+ "1 5 4 10 6 9 7\n"
				+ "2 5 6 7\n"
				+ "3 2 6\n");
		String expected = new String("1 5\n"
				+ "2 5\n"
				+ "3 2\n");
		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);

	}
	
	@Test
	public void oneMPallGroupsChoose() {
	
		String projDesc = new String("Projects\n"
				+ "1\n"
				+ "1 3\n"		//takes in 3 groups
				+ "OwnProjects\n"
				);
		
		String groupPrefs = new String("7\n"
				+ "1 1\n"
				+ "2 1\n"
				+ "3 1\n");
		String expected = new String("1 1\n"
				+ "2 1\n"
				+ "3 1\n");
		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);

	}
	
	@Test public void noContentionforThreeMP(){
		String projDesc = new String("Projects\n"
				+ "7\n"
				+ "5 3\n"		//takes in 3 groups
				+ "2\n"
				+ "4\n"
				+ "10\n"
				+ "6\n"
				+ "9\n"
				+ "8\n"
				+ "OwnProjects\n"
				);
		
		String groupPrefs = new String("7\n"
				+ "1 5 4 10 6 9 8\n"
				+ "2 5 6 8\n"
				+ "3 2 6\n"
				+ "4 5 9 4");
		String expected = new String(
				 "1 5\n"
				+ "2 5\n"
				+ "3 2\n"
				+ "4 5\n");
		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);
	}
	
	
//test when 4 groups first choice is a multi group project but only 3 are allowed.	
	@Test
	public void contention4choose3MP(){
	
	String groupPrefs = new String("5\n"
			+ "1 5 1 3 2\n"
			+ "2 5 2 3 1\n"
			+ "3 5 3 1 2\n"
			+ "4 5 1 2 3\n");
	
	String projDesc = new String("Projects\n"
			+ "5\n"
			+ "1\n"
			+ "2\n"
			+ "3\n"
			+ "4\n"
			+ "5 3\n"
			+ "OwnProjects\n"		//multigroup project which can take in 3 groups
			);		
	String expected = new String(
			"1 5\n"
			+ "2 5\n"
			+ "3 5\n"
			+ "4 1\n");
	String result = Hungarian.getAssignments(groupPrefs, projDesc);
	assertEquals(expected, result);
	}
	
	
	
	//project can take 3 groups, only 2 have it in their higher priority
	public void noContention3MP2Chosen(){
		String groupPrefs = new String("5\n"
				+ "1 1 2 4 3\n"
				+ "2 1 6 4 5\n"
				+ "3 1 2 4 3\n"
				+ "4 2 1 6\n"
				+ "5 2 6 4 3\n");
		
		String projDesc = new String("Projects\n"
				+ "5\n"
				+ "1\n"
				+ "2\n"
				+ "3\n"
				+ "4\n"
				+ "5\n"
				+ "6 3\n"
				+ "OwnProjects\n"		//multigroup project which can take in 3 groups
				);
		
		String expected = new String("1 1\n"
				+ "2 6\n"
				+ "3 4\n"
				+ "4 2\n"
				+ "5 6\n");
		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);
	}
	
	
	
	@Test 
	public void allChooseMP(){
		String groupPrefs = new String("5\n"
				+ "1 5 4 1 2\n"
				+ "2 5 2\n"
				+ "3 5 2 1 3\n"
				+ "4 5 3 1 2\n"
				+ "5 2 4\n"
				+ "6 2 3 5 1 4\n"
				+ "7 1 3 2 5");
		
		
		String projDesc = new String("Projects\n"
				+ "5\n"
				+ "1\n"
				+ "2 3\n" 		//project takes 3 groups
				+ "3\n"
				+ "4\n"
				+ "5\n");
		
		
		String expected = new String(
				  "1 4\n"
				+ "2 5\n"
				+ "3 2\n"
				+ "4 3\n"
				+ "5 2\n"
				+ "6 2\n"
				+ "7 1\n"
				);
		
		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);
	}
	

	
	

}
