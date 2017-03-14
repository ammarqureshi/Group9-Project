package com.test;
import static org.junit.Assert.*;

import org.junit.Test;

public class PreferenceParserTest {

	//Simplest test
	@Test
	public void simplestTest() {
		String projectInfo = 
				"Projects\n" +
				"1\n";
		String oneGroupPreference = "1 1\n";
		ProjectData pd = new ProjectData(projectInfo);
		PreferenceParser pp = new PreferenceParser(pd);
		int[][] result = pp.getCostMatrixForPreferences(oneGroupPreference);
		int[][] expected = {{1}};
		assertEquals(expected, result);
	}
	
	@Test
	public void threeProjOnePref() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n" +
				"3\n";
		String oneGroupPreference = "1 1 2 3\n";
		ProjectData pd = new ProjectData(projectInfo);
		PreferenceParser pp = new PreferenceParser(pd);
		int[][] result = pp.getCostMatrixForPreferences(oneGroupPreference);
		int unwanted = PreferenceParser.VALUE_UNWANTED_PROJ;
		int[][] expected = {{1, 2, 3}, {unwanted, unwanted, unwanted}, {unwanted, unwanted, unwanted}};
		assertEquals(expected, result);
		

		String oneGroupPreference2 = "1 3 2 1\n";
		int[][] result2 = pp.getCostMatrixForPreferences(oneGroupPreference2);
		int[][] expected2 = {{1, 2, 3}, {unwanted, unwanted, unwanted}, {unwanted, unwanted, unwanted}};
		assertEquals(expected, result);
	}
	
	@Test
	public void easyMultigroup() {
		String projectInfo = 
				"Projects\n" +
				"1 3\n";
		String threeGroups =
				"1 1\n" +
				"2 1\n" +
				"3 1\n";
		ProjectData pd = new ProjectData(projectInfo);
		PreferenceParser pp = new PreferenceParser(pd);
		int[][] result = pp.getCostMatrixForPreferences(threeGroups);
		int unwanted = PreferenceParser.VALUE_UNWANTED_PROJ;
		int[][] expected = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
		assertEquals(expected, result);
	}
	
	@Test
	public void easyOriginalProj() {
		String projectInfo = 
				"Projects\n" +
				"1\n" +
				"2\n" +
				"OwnProjects\n" +
				"1\n" +
				"3\n";
		String threeGroups =
				"1 1 2\n" +
				"2 2 1\n" +
				"3 OP\n";
		ProjectData pd = new ProjectData(projectInfo);
		PreferenceParser pp = new PreferenceParser(pd);
		int[][] result = pp.getCostMatrixForPreferences(threeGroups);
		int unwanted = PreferenceParser.VALUE_UNWANTED_PROJ;
		int[][] expected = {{1, 2, unwanted}, {2, 1, unwanted}, {2, 2, 1}};
		assertEquals(expected, result);
	}
}
