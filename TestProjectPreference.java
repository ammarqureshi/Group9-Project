import static org.junit.Assert.*;

import org.junit.Test;

public class TestProjectPreference {

	
	/**
	 * Tests return for empty preference list
	 */
	@Test
	public void testNoPreferences()
	{
		Group group = new Group();
		assertNull("No project preferences entered", group.getPreference());
	}
	
	/**
	 * Test for every group with no shared preferences
	 */
	@Test
	public void testOnePreference()
	{
		Group [] groups = new Group[4];
		for(int i = 0; i < 4; i++)
		{
			groups[i] = new Group(i);
		}
		assertEquals("Group 1 project:",1, groups[0].getPreference());
		assertEquals("Group 2 project:",2, groups[1].getPreference());
		assertEquals("Group 3 project:",3, groups[2].getPreference());
		assertEquals("Group 4 project:",4, groups[3].getPreference());
	}
	
	/**
	 * Test for when groups share preferences
	 */
	@Test
	public void testSharedPreference()
	{
		Group group1 = new Group(1, 14, 25);
		Group group2 = new Group(3, 12, 14);
		Group group3 = new Group(1, 3, 27);
		assertEquals("Group 1 project:",1, group1.getPreference());
		assertEquals("Group 2 project:",3, group2.getPreference());
		assertEquals("Group 3 project:",27, group3.getPreference());
	}
	
	/**
	 * Test for when no preferences available
	 */
	@Test 
	public void testAllTaken()
	{
		Group group1 = new Group(10, 18, 30);
		Group group2 = new Group(10, 18);
		assertEquals("Group 1 project:",10, group1.getPreference());
		assertNull("Group 2 project:", group2.getPreference());
	}

	/**
	 * Test for when a 1st choice preference is available 
	 * as another groups non-first choice
	 */
	@Test 
	public void testOtherGroupsFirst()
	{
		Group group1 = new Group(1,2,4,8);
		Group group2 = new Group(10,12);
		Group group3 = new Group(10, 22, 18);
		Group group4 = new Group(22, 29);
		assertEquals("Group 1 project:",1, group1.getPreference());
		assertEquals("Group 1 project:",10, group1.getPreference());
		assertEquals("Group 1 project:",18, group1.getPreference());
		assertEquals("Group 1 project:",22, group1.getPreference());
	}
}
