package com.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileIOTests {

	@Test
	public void test() {
		// same input and output as ConorLargeTests first test
		String pathToPrefs = "fileIOTestFilePrefs.txt";
		String pathToProjects = "fileIOTestFileProjects.txt";
		String result = MainProgram.assignFromFileInput(pathToPrefs, pathToProjects);
		String expected = 
				"1 2\n" +
				"2 6\n" +
				"3 5\n" +
				"4 8\n" +
				"5 OP\n" +
				"6 8\n" +
				"7 3\n" +
				"8 8\n" +
				"9 1\n" +
				"10 4\n";
		assertEquals(expected, result);
	}

}
