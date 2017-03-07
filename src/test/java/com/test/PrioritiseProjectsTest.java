import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

public class PrioritiseProjectsTest {

	@Test
	public void OnegroupTrue() {
		String results = "1 1";
		int[] projects = {1};
		Assert.assertTrue(PrioritiseProjects.AllAllocated(results, projects));
	}
	
	@Test
	public void OnegroupFalse() {
		String results = "1 1 2 2 3 3";
		int[] projects = {4};
		Assert.assertFalse(PrioritiseProjects.AllAllocated(results, projects));
	}
	
	@Test
	public void FivePriorityProjectsAllocated() {
		String results = "1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10 10";
		int[] projects = {1, 2, 3, 4, 5};
		Assert.assertTrue(PrioritiseProjects.AllAllocated(results, projects));
	}
	
	@Test
	public void FivePriorityProjectsNotAllocated() {
		String results = "1 1 2 2 3 3 4 4 5 5 6 6 7 7 8 8 9 9 10 10";
		int[] projects = {1, 2, 3, 4, 11};
		Assert.assertFalse(PrioritiseProjects.AllAllocated(results, projects));
	}
	
	@Test
	public void OnePriorityProjectPriorityBumped() {
		String groups = new String(
				"12\n1 1 2 3 4 5 6 7 8 9 10 11 12"
		);
		String newPrefs = new String(
				"12\n1 1 2 3 4 5 6 7 8 9 11 10 12\n"
		);
		int[] projects = {11};
		Assert.assertEquals(newPrefs, PrioritiseProjects.bumpPriority(groups, projects));
	}
	
	@Test
	public void FivePriorityProjectPriorityBumped() {
		String groups = new String(
				"12\n1 1 2 3 4 5 6 7 8 9 10 11 12"
		);
		String newPrefs = new String(
				"12\n1 1 2 3 4 5 7 8 6 10 11 12 9\n"
		);
		int[] projects = {10, 11, 12, 8, 7};
		Assert.assertEquals(newPrefs, PrioritiseProjects.bumpPriority(groups, projects));
	}
	
	

}
