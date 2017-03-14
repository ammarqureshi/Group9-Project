package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ownProjectTest {

	//test for when no own projects chosen by groups	
	@Test
	public void noGroupChoseOwnProj() {

		String groupPrefs = new String("3 \n"
				+ "1 1 5 10 7 8\n"
				+ "2 8 7 2\n"
				+ "3 1 2 10\n");

		String projDesc = new String("Projects\n"
				+ "3\n"
				+ "1\n"
				+ "2\n"
				+ "3\n"
				+ "OwnProjects\n");

		String expected = new String("1 1\n"
				+ "2 8\n"
				+ "3 2\n");
		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);

	}

	//test for when all get own projects
	@Test
	public void allGetOwnProj(){

		String groupPrefs = new String("3\n"
				+ "1 OP 10 5 3 4 9 6"
				+ "2 OP 2 8 6 10 1"
				+ "4 OP 2 5 14 2");

		String projDesc = new String("Projects\n"
				+ "3\n"
				+ "1\n"
				+ "2\n"
				+ "4\n"
				+ "OwnProjects\n"
				+ "1\n"
				+ "2\n"
				+ "4\n");

		String expected = new String(
				"1 OP\n"
						+ "2 OP\n"
						+ "4 OP\n");

		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);
	}


	//test for when all choose own project but only one gets own project

	@Test
	public void noneGetFirstChoiceOP(){

		String groupPrefs = new String("4\n"
				+ "1 OP 4 5 2 1 3"
				+ "2 OP 4 3 1 2 5"
				+ "3 OP 3 1 2 5 4 "
				+ "4 OP 3 1 2 5 4");

		String projDesc = new String("Projects\n"
				+ "4\n"				//number of projects
				+ "1\n"
				+ "2\n"
				+ "3\n"
				+ "4\n"
				+ "OwnProjects\n"	//group numbers for which own projects are allowed
				);
		String expected = new String(
				"1 5\n"
						+ "2 4\n"
						+ "3 1\n"
						+ "4 3\n"
						+ "5 2\n");
		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);

	}


	@Test
	public void oneOutOfTwoGetsOP(){

		String groupPrefs = new String("7 \n"
				+ "1 1 OP 2 4 10\n"
				+ "2 1 OP 4 3 10 2\n"
				+ "3 OP 10 4 3 5 2\n");

		String projDesc = new String("Projects\n"
				+ "7\n"
				+ "1\n"
				+ "2\n"
				+ "3\n"
				+ "4\n"
				+ "5\n"
				+ "6\n"
				+ "10\n"
				+ "OwnProjects\n"
				+ "1\n"
				+ "2\n");

		String expected = new String(
				"1 OP\n"
						+ "2 1\n"
						+ "3 10\n");

		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);
	}




	@Test public void vetTwoOP(){
		String groupPrefs = new String(
				"12\n"
						+ "A  1 OP 3 7 4 1 2\n"
						+ "B OP 1 7\n"
						+ "C 2 1 OP\n"
						+ "D 2 OP 5 7 3 6\n"
						+ "E 4 6 3 \n"
						+ "F 1 3\n"
						+ "G 4 12\n"
						+ "H OP 4 5 6 7 4 1 3\n");		
		String projDesc = new String("Projects\n"
				+ "12\n"
				+ "1\n"
				+ "2\n"
				+ "3\n"
				+ "4\n"
				+ "5\n"
				+ "6\n"
				+ "7\n"
				+ "OwnProject"
				+ "A\n"
				+ "B\n"
				+ "E\n");

		String expected = new String(
				"A OP\n"
						+ "B OP\n"
						+ "C 1\n"
						+ "D 2\n"
						+ "E 4\n"
						+ "F 3\n"
						+ "G 12\n"
						+ "H 5\n");
		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);
	}



}
