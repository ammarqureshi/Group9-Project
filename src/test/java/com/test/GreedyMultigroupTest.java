package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class GreedyMultigroupTest {

	@Test
	public void allFirstPrefs(){
		int[][] groups = new int[4][5];
		groups[0] = new int[]{1,2,3,4,5};
		groups[1] = new int[]{2,3,4,5,6};
		groups[2] = new int[]{3,4,5,6,7};
		groups[3] = new int[]{4,5,6,7,8};
		int [] multiprojs = {1,2,3,10};
		int [] amountPerProj = {1,1,1,1};
		
		int [] expected = null;
		MultigroupChange multi = new MultigroupChange();
		groups = multi.multiSort(groups, multiprojs, amountPerProj);
//		assertArrayEquals("Expected return is: ",expected, groups[0]);
//		assertArrayEquals("Expected return is: ",null, groups[1]);
//		assertArrayEquals("Expected return is: ",null, groups[2]);
//		assertArrayEquals("Expected return is: ",null, groups[3]);
		assertArrayEquals("Expected return is null",null, groups[0]);
//		for(int i = 0; i < groups.length; i++)
//		{
//			assertArrayEquals("Expected return is: ",null, groups[i]);
//		}
	}
	
	@Test 
	public void noneAssigned(){
		int[][]groups = new int[1][1];
		groups[0] = new int[]{1};
		int [] multiprojs = {8};
		int [] amountPerProj = {1};
		MultigroupChange multi = new MultigroupChange();
		int [] expected  = {1};
		groups = multi.multiSort(groups, multiprojs, amountPerProj);
		assertArrayEquals("Expected return is: ",expected, groups[0]);

	}
	
	@Test
	public void invalidParams(){
		int[][]groups = new int[3][1];
		for(int i = 0; i < groups.length; i++)
		{
			groups[i][0] = i+1;
		}
		int [] multiprojs = {8,2,3,4};
		int [] amountPerProj = {1,3,4};
		
		MultigroupChange multi = new MultigroupChange();
		groups = multi.multiSort(groups, multiprojs, amountPerProj);
		assertNull("Expected return is null", groups);
	}
}

