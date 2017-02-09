package src.test.java.com.test;
import static org.junit.Assert.*;

import org.junit.Test;

public class TestProjectPreference {

	
	/**
	 * Tests return for empty preference list
	 */
	@Test
//	public void testNoPreferences()
//	{
//		int [] group1 = new int[1];
//		group1[0] = null;
//		assertNull("No project preferences entered", null);
//	}
	
	/**
	 * Test for every group with no shared preferences
	 */
	@Test
	public void testOnePreference()
	{
		long [] group1 = new long[4];
		for(int i = 0; i < 4; i++)
		{
			group1[i] = i;
		}
		assertEquals("Group 1 project:",1, (long)group1[0]);
	}
	
	/**
	 * Test for when groups share preferences
	 */
	@Test
	public void testSharedPreference()
	{
		long [] group1, group2;
		group1 = new long[3];
		group2 = new long[3];
		for(int i = 0; i < group1.length; i++)
		{
			group1[i] = i+1;
			group2[i] = i+1;
		}
		
		assertEquals("Group 1 Project: ", 1, group1[0]);
		assertEquals("Group 2 Project: ", 2, group1[1]);
	}
	
	/**
	 * Test for when no preferences available
	 */
	@Test 
	public void testAllTaken()
	{
		long [] group1 = {10, 18, 30};
		long [] group2 = {10,18};
		assertEquals("Group 1 project:",10, group1[0]);
		assertNull("Group 2 project:",null);
	}

	/**
	 * Test for when a 1st choice preference is available 
	 * as another groups non-first choice
	 */
	@Test 
	public void testOtherGroupsFirst()
	{
		long [] group1 = {1,2,4,8};
		long [] group2 = {10,12};
		long [] group3 = {10, 22, 18};
		long [] group4 = {22, 29};
		assertEquals("Group 1 project:",1, group1[0]);
		assertEquals("Group 2 project:",10, group2[0]);
		assertEquals("Group 3 project:",18, group3[2]);
		assertEquals("Group 4 project:",22, group4[0]);
	}
}
