package com.test;

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
	public void PriorityProjectsAllocatedUnordered(){
		String results = "1 5 2 9 3 3 4 2 5 1";
		int[] projects = {9, 2};
		Assert.assertTrue(PrioritiseProjects.AllAllocated(results, projects));
	}
	
	@Test
	public void PriorityProjectsNotAllocatedUnordered(){
		String results = "1 1 2 6 3 8 4 9 5 2 6 5 7 10";
		int[] projects = {9, 3};
		Assert.assertFalse(PrioritiseProjects.AllAllocated(results, projects));

	}
	
	
	@Test
	public void OnePriorityProjectPriorityBumped() {
		String groups = new String(
				"1 1 2 3 4 5 6 7 8 9 10 11 12"
		);
		String newPrefs = new String(
				"1 1 2 3 4 5 6 7 8 9 11 10 12\n"
		);
		int[] projects = {11};
		Assert.assertEquals(newPrefs, PrioritiseProjects.bump(groups, projects));
	}
	
	//to test if project to bump is already the highest priority
	//if it is, then preference should remain the same
	
	@Test
	public void ProjectAlreadyHighestPriority(){
		String groups = new String(
				"2 5 6 9 10"
		);
	
		
		String newPrefs = new String(
				"2 5 6 9 10\n"
		);
		
		int[] projects = {5};
		Assert.assertEquals(newPrefs, PrioritiseProjects.bump(groups, projects));
	}
	
	
	@Test
	public void FivePriorityProjectPriorityBumped() {
		String groups = new String(
				"1 1 2 3 4 5 6 7 8 9 10 11 12"
		);
		String newPrefs = new String(
				"1 1 2 3 4 5 7 8 6 10 11 12 9\n"
		);
		int[] projects = {10, 11, 12, 8, 7};
		Assert.assertEquals(newPrefs, PrioritiseProjects.bump(groups, projects));
	}
	

	
	@Test
	public void TwoGroupPriorityProjectBumped() {
		String groups = new String(
				"1 1 2 3 4 5 6 10 8 9\n" +
				"2 9 10 5 6 12 18 1 6 3"
		);
		String newPrefs = new String(
				"1 1 2 3 4 5 10 6 8 9\n" +
				"2 10 9 5 6 12 18 1 6 3\n"
		);
		int[] projects = {10};
		Assert.assertEquals(newPrefs, PrioritiseProjects.bump(groups, projects));
	}
	
	@Test
	public void ThreeGroupPriorityProjectBumped() {
		String groups = new String(
				"1 1 2 3 4 5 6 10 8 9\n" +
				"2 9 10 5 6 12 18 1 6 3\n" +
				"3 10 1 9 8 7 3 5 12 11"
		);
		String newPrefs = new String(
				"1 1 3 2 4 5 10 6 8 9\n" +
				"2 10 9 5 6 12 18 1 3 6\n" +
				"3 10 1 9 8 3 7 5 12 11\n"

		);
		int[] projects = {10,3};
		Assert.assertEquals(newPrefs, PrioritiseProjects.bump(groups, projects));
	}
	
	
	
	@Test
	public void GroupPriorityProjectNotBumped(){
		String groups = new String(
				"1 1 2 3 4 5 6 10 8 9\n" +
				"2 9 10 5 6 12 18 1 6 3"
		);
		String newPrefs = new String(
				"1 1 2 3 4 5 6 10 8 9\n" +
				"2 9 10 5 6 12 18 1 6 3\n"
		);
		int[] projects = {15};
		Assert.assertEquals(newPrefs, PrioritiseProjects.bump(groups, projects));
	}
	
	

}
