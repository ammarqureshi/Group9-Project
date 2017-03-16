package com.test;

import org.junit.Assert;
import org.junit.Test;

public class HungarianHappinessTest {

	@Test
	public void Onegroup() {
		String groups = new String("12\n1 1 2 3 4 5 6 7 8 9 10 11 12");
		String results = "1 1\n";
		double[] exResults = new double[]{100, 0, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	//Every group within 'happy' range
	@Test
	public void allHappy() {
		String groups = new String(
				"12\n1 1 2 3 4 5 6 7 8 9 10 11 12\n"
				+ "2 2 1 3 4 5 6 7 8 9 10 11 12\n" 
				+ "3 3 2 1 4 5 6 7 8 9 10 11 12\n"
				+ "4 4 2 3 1 5 6 7 8 9 10 11 12\n"
				+ "5 5 2 3 4 1 6 7 8 9 10 11 12"
		);
		String results = "1 1\n 2 2\n 3 3\n 4 4\n 5 5\n";
		double[] exResults = new double[]{100, 0, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	//Every group falls in middle range
	@Test
	public void allMiddling() {
		String groups = new String(
				"12\n1 1 2 3 4 5 6 7 8 9 10 11 12\n"
				+ "2 2 1 3 4 5 6 7 8 9 10 11 12\n"
				+ "3 3 2 1 4 5 6 7 8 9 10 11 12\n"
				+ "4 4 2 3 1 5 6 7 8 9 10 11 12\n"
				+ "5 5 2 3 4 10 6 7 8 9 1 11 12"
		);
		String results = "1 5\n 2 6\n 3 4\n 4 1\n 5 10\n";
		double[] exResults = new double[]{0, 100, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	//Every group falls in the unhappy range
	@Test
	public void allUnhappy() {
		String groups = new String(
				"12\n1 1 2 3 4 5 6 7 8 9 10 11 12\n"
				+ "2 2 1 3 4 5 6 7 8 9 10 11 12\n"
				+ "3 3 2 1 4 5 6 7 8 9 10 11 12\n"
				+ "4 4 2 3 1 5 6 7 8 9 10 11 12\n"
				+ "5 9 2 3 4 1 6 7 8 5 10 11 12"
		);
		String results = "1 12\n 2 11\n 3 10\n 4 9\n 5 5\n";
		double[] exResults = new double[]{0, 0, 100};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	//Every group gets a project not in preferences (should be all sad)
	@Test
	public void notPreferenceProject() {
		String groups = new String(
				"5\n1 5 2 3 4 1\n"
				+ "2 5 1 3 4 2\n"
				+ "3 5 2 1 4 3\n"
				+ "4 5 2 3 1 4\n"
				+ "5 4 2 3 1 5"
		);
		String results = "1 9\n 2 8\n 3 7\n 4 6\n 5 10\n";
		double[] exResults = new double[]{0, 0, 100};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void Happy50Middling50() {
		String groups = new String(
				"5\n1 5 2 3 4 1\n"
				+ "2 2 1 3 4 5\n"
				+ "3 5 2 1 4 3\n"
				+ "4 5 2 3 1 4"
		);
		String results = "1 5\n 2 2\n 3 3\n 4 4\n";
		double[] exResults = new double[]{50, 50, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void Happy50Middling25Unhappy25() {
		String groups = new String(
				"5\n1 1 2 3 4 5\n"
				+ "2 1 2 3 4 5\n"
				+ "3 1 2 3 4 5\n"
				+ "4 1 2 3 4 5"
		);
		String results = "1 1\n 2 2\n 3 5\n 4 7\n";
		double[] exResults = new double[]{50, 25, 25};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void Happy75Middling25() {
		String groups = new String(
				"5\n1 1 2 3 4 5\n"
				+ "2 1 2 3 4 5\n"
				+ "3 1 2 3 4 5\n"
				+ "4 1 2 3 4 5"
		);
		String results = "1 1\n 2 2\n 3 3\n 4 5\n";
		double[] exResults = new double[]{75, 25, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void Happy75Middling0Unhappy25() {
		String groups = new String(
				"5\n1 1 2 3 4 5\n"
				+ "2 1 2 3 4 5\n"
				+ "3 1 2 3 4 5\n"
				+ "4 1 2 3 4 5"
		);
		String results = "1 1\n 2 2\n 3 3\n 4 6\n";
		double[] exResults = new double[]{75, 0, 25};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void Happy25Middling50Unhappy25() {
		String groups = new String(
				"5\n1 1 2 3 4 5\n"
				+ "2 1 2 3 4 5\n"
				+ "3 1 2 3 5 4\n"
				+ "4 1 2 3 4 5"
		);
		String results = "1 1\n 2 6\n 3 4\n 4 5\n";
		double[] exResults = new double[]{25, 50, 25};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void OneOwnProject() {
		String groups = new String(
				"5\n1 1 2 3 4 5\n"
		);
		String results = "1 OP\n";
		double[] exResults = new double[]{100, 0, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}
	
	@Test
	public void TwoOwnProjects() {
		String groups = new String(
				"5\n1 1 2 3 4 5\n"
				+ "2 1 2 3 4 5\n"
				+ "3 1 2 3 5 4\n"
				+ "4 1 2 3 4 5"
		);
		String results = "1 OP\n 2 OP\n 3 4\n 4 5\n";
		double[] exResults = new double[]{50, 50, 0};
		Assert.assertArrayEquals( exResults, HungarianHappiness.happinessScore(groups, results), 0);
	}

}
