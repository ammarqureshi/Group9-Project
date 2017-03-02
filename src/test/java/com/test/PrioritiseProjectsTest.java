import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class PrioritiseProjectsTest {

	@Test
	public void OnegroupTrue() {
		String results = "1";
		int[] projects = {1};
		Assert.assertTrue(PrioritiseProjects.AllAllocated(results, projects));
	}
	
	@Test
	public void OnegroupFalse() {
		String results = "1 2 3";
		int[] projects = {4};
		Assert.assertFalse(PrioritiseProjects.AllAllocated(results, projects));
	}
	
	@Test
	public void FivePriorityProjectsAllocated() {
		String results = "1 2 3 4 5 6 7 8 9 10";
		int[] projects = {1, 2, 3, 4, 5};
		Assert.assertTrue(PrioritiseProjects.AllAllocated(results, projects));
	}
	
	@Test
	public void FivePriorityProjectsNotAllocated() {
		String results = "1 2 3 4 5 6 7 8 9 10";
		int[] projects = {1, 2, 3, 4, 11};
		Assert.assertFalse(PrioritiseProjects.AllAllocated(results, projects));
	}
	
	@Test
	public void OnePriorityProjectPriorityBumped() {
		String[] groups = new String[]{
				"1 1 2 3 4 5 6 7 8 9 10 11 12",
		};
		String[] newPrefs = new String[]{
				"1 1 2 3 4 5 6 7 8 9 11 10 12",
		};
		int[] projects = {11};
		Assert.assertArrayEquals(newPrefs, PrioritiseProjects.bumpPriority(groups, projects));
	}
	
	@Test
	public void FivePriorityProjectPriorityBumped() {
		String[] groups = new String[]{
				"1 1 2 3 4 5 6 7 8 9 10 11 12",
		};
		String[] newPrefs = new String[]{
				"1 1 2 3 4 5 7 8 6 10 11 12 9",
		};
		int[] projects = {10, 11, 12, 8, 7};
		Assert.assertArrayEquals(newPrefs, PrioritiseProjects.bumpPriority(groups, projects));
	}
	
	

}
