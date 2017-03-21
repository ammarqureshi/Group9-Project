package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConorLargeTests {

	@Test
	public void test() {
		String projDesc = "Projects\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"4\n" +
				"5\n" +
				"6\n" +
				"7\n" +
				"8 P 3\n" + //All three PPs should be assigned
				"9\n" +
				"10\n" +
				"OwnProjects\n" +
				"2\n" +
				"5\n" +
				"10\n";
		
		String groupPrefs = "1 2 4 5\n" +
				"2 1 2 4 6 7 OP\n" +
				"3 OP 1 3 5 7 8\n" +
				"4 1 8 2 10 5 OP\n" +
				"5 1 OP\n" +
				"6 \n" +
				"7 3 4 5 8 10\n" +
				"8 OP\n" +
				"9 1 2 3 5\n" +
				"10 4 5 6 1 OP 5 2\n";
		String result = MainProgram.getAssignments(groupPrefs, projDesc);
		System.out.println(result);
		fail();
	}

}
