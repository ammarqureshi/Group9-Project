package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConorLargeTests {

	@Test
	public void test10() {
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
		//fail();
	}
	
	@Test 
	public void test20(){
		String projDesc = "Projects\n" +
				"1\n" +
				"2\n" +
				"3\n" +
				"4\n" +
				"5\n" +
				"6\n" +
				"7\n" +
				"8 P 4\n" + //Only 2 assigned
				"9\n" +
				"10\n" +
				"11 2\n" +
				"12\n" +
				"13 P\n" +
				"14\n" +
				"15\n" +
				"16 P 2\n" + //Ps assigned when only 2 needed
				"17\n" +
				"18\n" +
				"19 P\n" +
				"20\n" +
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
				"10 4 5 6 1 OP 5 2\n" +
				"11 11\n" +
				"12 1 5 19 20\n" +
				"13 OP 1 7 8\n" +
				"14 12 13 14 16 5 OP\n" +
				"15 17\n" +
				"16 11 19 17\n" +
				"17 3 4 5 8 19\n" +
				"18 OP\n" +
				"19 16 8 4 5\n" +
				"20 15\n";
		String result = MainProgram.getAssignments(groupPrefs, projDesc);
		System.out.println(result);
	}

}
