package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class MultigroupStringTest {

	@Test
	public void testOneGroup() {
		String groups,multiprojs,amounts;
		groups = "1 2 3 4";
		multiprojs = "1 3 5";
		amounts = "1 1 1";
		MultigroupStrings string = new MultigroupStrings();
		string.multiSort(groups, multiprojs, amounts);
	}
	
	@Test
	public void testTwoGroup() {
		String groups,multiprojs,amounts;
		groups = "1 2 3 4"
				+ "2 3 5 7";
		multiprojs = "1 3 5";
		amounts = "1 1 1";
		MultigroupStrings string = new MultigroupStrings();
		string.multiSort(groups, multiprojs, amounts);
	}

}
