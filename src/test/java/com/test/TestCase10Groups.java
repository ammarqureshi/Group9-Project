package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCase10Groups {

	@Test
	public void test() {
		String projDesc = new String("Projects\n"
				+ "1\n"
				+ "5 2\n"		//takes in 2 groups
				+ "2\n"
				+ "4\n"
				+ "10\n"
				+ "6\n"
				+ "9\n"
				+ "8\n"
				+ "OwnProjects\n"
				);
		
		String groupPrefs = new String("7\n"
				+ "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30\n"
				+ " \n"
				+ "3 2 6 12 13 4 26 21 30 11 17 PP\n"
				+ "PP PP\n"
				+ "1 4 7 10 13 22\n"
				+ "10 20 30\n"
				+ "1 2 3 4 5\n"
				+ "25\n"
				+ "11 23 7 6 2 10 9 30\n"
				+ "17 18 18 45\n");
		String expected = new String("1 5\n"
				+ "2 5\n"
				+ "3 2\n");
		String result = Hungarian.getAssignments(groupPrefs, projDesc);
		assertEquals(expected, result);
	}

}
