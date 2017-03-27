package com.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.Test;

public class ProjectDataTest {


	//Simplest test
	@Test
	public void simplestTest() {
		String projectInfo = 
				"Projects\n" +
				"1\n";
		ProjectData pd = new ProjectData(projectInfo);
		assertEquals(1, pd.totalProjectColumns());
		assertEquals("    1",
						pd.colHeadersToString());
	}
	
	@Test
	public void mediumSize() {
		String testOne =
				"Projects\n" +
				"1\n" +
				"2\n" +
				"4 P\n" +
				"5 3\n" +
				"6 2 P\n" +
				"OwnProjects\n" +
				"2\n" +
				"1\n" +
				"4";
		ProjectData pd = new ProjectData(testOne);
		// 3 standard projects + multigroup of 3 + multigroup of 2 + 2 own projects
		assertEquals("    1    2    4    5    5    5    6    6  999  999",
				pd.colHeadersToString());
		assertEquals(10, pd.totalProjectColumns());
	    
	}
	
	@Test
	public void testNoProject()
	{
		String groupInfo = 
				"1\n" +
				"2\n" +
				"4 P\n" +
				"5 3\n" +
				"6 2 P\n" +
				"OwnProjects\n" +
				"2\n" +
				"1\n" +
				"4";
	  try
	  {
	    new ProjectData(groupInfo);
	    fail("Should have thrown some exception but did not!");
	  }
	  catch( final IllegalArgumentException e )
	  {
		final String err = "Projects info file incorrectly formatted? Doesn't start with \"Projects\\n\"";
	    assertEquals(err, e.getMessage());
	  }
	}
	
	@Test
	public void testProjectDetailsUnrecognized(){
		String groupInfo =
				"Projects\n" +
				"1\n" +
				"2\n" +
				"4 P\n" +
				"5 3\n" +
				"9 abc";
			
		
		  try
		  {
		    new ProjectData(groupInfo);
		    fail("Should have thrown some exception but did not!");
		  }
		  catch( final IllegalArgumentException e )
		  {
				String err = "Token in project info for project " + 9+  " unrecognised : abc" ;
		    assertEquals(err, e.getMessage());
		  }
		
		
		
	}
	


}
