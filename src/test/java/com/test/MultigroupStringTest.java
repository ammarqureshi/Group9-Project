package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class MultigroupStringTest {

	@Test
	public void testOneGroup() {
		System.out.println("Testing for 1 group");
		String groups,multiprojs,amounts;
		groups = "1 2 3 4";
		multiprojs = "1 3 5";
		amounts = "1 1 1";
		MultigroupStrings string = new MultigroupStrings();
		string.multiSort(groups, multiprojs, amounts);
	}
	
	@Test
	public void testTwoGroup() {
		System.out.println("Testing for 2 groups");
		String groups,multiprojs,amounts;
		groups = "1 2 3 4;"
				+ "2 3 5 7;";
		multiprojs = "1 3 5";
		amounts = "1 1 1";
		MultigroupStrings string = new MultigroupStrings();
		string.multiSort(groups, multiprojs, amounts);
	}
	
	@Test
	public void testTen() {
		System.out.println("Testing for 10 groups");
		String groups, multiprojs, amounts;
		groups = "1 5 6 7 9 10 11 2 4;"
				+ "1 13 4 7 9 10;"
				+ "10 11 12 13 14 28;"
				+ "2 4 5;"
				+ "3 6 9 12 15 18 21 29;"
				+ "7;"
				+ "31 32 3 15 4;"
				+ "1 2 3 4 5 6 7 8 9 10 11 12;"
				+ "9 10 21 22 30 2;"
				+ "22 30 10 23 5 8 3;";
		multiprojs = "1 10 31 9 4 13 29";
		amounts = "2 2 1 1 3 1 1";
		MultigroupStrings string = new MultigroupStrings();
		string.multiSort(groups, multiprojs, amounts);
	}

}
