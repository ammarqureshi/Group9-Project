package com.test;

import org.junit.Assert;
import org.junit.Test;

public class HungarianHappinessTest {

	@Test
	public void Onegroup() {
		String[] groups = new String[]{
				"1 1 2 3 4 5 6 7 8 9 10 11 12",};
		String results = "1";
		double[] exResults = new double[]{100, 0, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	//Every group within 'happy' range
	@Test
	public void allHappy() {
		String[] groups = new String[]{
				"1 1 2 3 4 5 6 7 8 9 10 11 12",
				"2 2 1 3 4 5 6 7 8 9 10 11 12",
				"3 3 2 1 4 5 6 7 8 9 10 11 12",
				"4 4 2 3 1 5 6 7 8 9 10 11 12",
				"5 5 2 3 4 1 6 7 8 9 10 11 12"
		};
		String results = "1 2 3 4 5";
		double[] exResults = new double[]{100, 0, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	//Every group falls in middle range
	@Test
	public void allMiddling() {
		String[] groups = new String[]{
				"1 1 2 3 4 5 6 7 8 9 10 11 12",
				"2 2 1 3 4 5 6 7 8 9 10 11 12",
				"3 3 2 1 4 5 6 7 8 9 10 11 12",
				"4 4 2 3 1 5 6 7 8 9 10 11 12",
				"5 5 2 3 4 1 6 7 8 9 10 11 12"
		};
		String results = "5 6 7 8 1";
		double[] exResults = new double[]{0, 100, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	//Every group falls in the unhappy range
	@Test
	public void allUnhappy() {
		String[] groups = new String[]{
				"1 1 2 3 4 5 6 7 8 9 10 11 12",
				"2 2 1 3 4 5 6 7 8 9 10 11 12",
				"3 3 2 1 4 5 6 7 8 9 10 11 12",
				"4 4 2 3 1 5 6 7 8 9 10 11 12",
				"5 9 2 3 4 1 6 7 8 5 10 11 12"
		};
		String results = "12 11 10 9 5";
		double[] exResults = new double[]{0, 0, 100};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	//Every group gets a project not in preferences (should be all sad)
	@Test
	public void notPreferenceProject() {
		String[] groups = new String[]{
				"1 5 2 3 4 1",
				"2 5 1 3 4 2",
				"3 5 2 1 4 3",
				"4 5 2 3 1 4",
				"5 4 2 3 1 5"
		};
		String results = "9 8 7 6 10";
		double[] exResults = new double[]{0, 0, 100};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void Happy50Middling50() {
		String[] groups = new String[]{
				"1 5 2 3 4 1",
				"2 2 1 3 4 5",
				"3 5 2 1 4 3",
				"4 5 2 3 1 4",
		};
		String results = "5 2 3 4";
		double[] exResults = new double[]{50, 50, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void Happy50Middling25Unhappy25() {
		String[] groups = new String[]{
				"1 1 2 3 4 5",
				"2 1 2 3 4 5",
				"3 1 2 3 4 5",
				"4 1 2 3 4 5",
		};
		String results = "1 2 5 7";
		double[] exResults = new double[]{50, 25, 25};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void Happy75Middling25() {
		String[] groups = new String[]{
				"1 1 2 3 4 5",
				"2 1 2 3 4 5",
				"3 1 2 3 4 5",
				"4 1 2 3 4 5",
		};
		String results = "1 2 3 5";
		double[] exResults = new double[]{75, 25, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void Happy75Middling0Unhappy25() {
		String[] groups = new String[]{
				"1 1 2 3 4 5",
				"2 1 2 3 4 5",
				"3 1 2 3 4 5",
				"4 1 2 3 4 5",
		};
		String results = "1 2 3 6";
		double[] exResults = new double[]{75, 0, 25};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void Happy25Middling50Unhappy25() {
		String[] groups = new String[]{
				"1 1 2 3 4 5",
				"2 1 2 3 4 5",
				"3 1 2 3 5 4",
				"4 1 2 3 4 5",
		};
		String results = "1 6 4 5";
		double[] exResults = new double[]{25, 50, 25};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}

}

