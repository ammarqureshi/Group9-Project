package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreedyMultigroupTest {

	@Test
	public void testFourgroups(){
		int[][] groups = new int[4][5];
		groups[0] = new int[]{1,2,3,4,5};
		groups[1] = new int[]{2,3,4,5,6};
		groups[2] = new int[]{2,4,5,6,7};
		groups[3] = new int[]{4,5,6,7,8};
		int [] multiprojs = {1,2,7};
		int [] amountPerProj = {1,2,1};
		
		MultigroupChange multi = new MultigroupChange();
		groups = multi.multiSort(groups, multiprojs, amountPerProj);
		int [] expected = null;
		assertArrayEquals("Expected return is: ",expected, groups[0]);
		System.out.println("Group 2 = " + groups[1]);
		assertArrayEquals("Expected return is: ",expected, groups[1]);
		assertArrayEquals("Expected return is: ",expected, groups[2]);
		assertArrayEquals("Expected return is: ",expected, groups[3]);
	}
	
	@Test 
	public void noneAssigned(){
		int[][]groups = new int[1][1];
		groups[0][0] = 1;
		int [] multiprojs = {8};
		int [] amountPerProj = {1};
		MultigroupChange multi = new MultigroupChange();
		int [] expected  = {1};
		groups = multi.multiSort(groups, multiprojs, amountPerProj);
		assertArrayEquals("Expected return is: ",expected, groups[0]);

	}
}

