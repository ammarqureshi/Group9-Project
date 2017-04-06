package com.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import org.junit.Test;

public class MainProgramTest {

	@Test
	public void test() throws FileNotFoundException { 
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    // IMPORTANT: Save the old System.out!
	    PrintStream old = System.out;
	    System.setOut(ps);
	    // Sysout from main redirected into string
		MainProgram.main(new String[] {"fileIOTestFilePrefs.txt", "fileIOTestFileProjects.txt"});
	    // Put things back
	    System.out.flush();
	    System.setOut(old);
	    String expected = 
	    		"Happiness before prioritizing - \n" +
	    		"Groups receiving top 3 preference - 60.00%\n" +
	    		"Groups receiving preference 4-6 - 20.00%\n" +
	    		"Groups receiving other preference - 20.00%\n" +
	    		"\n" +
	    		"Assignments\n" +
	    		"1 2\n" +
	    		"2 6\n" +
	    		"3 5\n" +
	    		"4 8\n" +
	    		"5 OP\n" +
	    		"6 8\n" +
	    		"7 3\n" +
	    		"8 9\n" +
	    		"9 1\n" +
	    		"10 4\n" +
	    		"\n" +
	    		"Happiness after prioritizing - \n" +
	    		"Groups receiving top 3 preference - 60.00%\n" +
	    		"Groups receiving preference 4-6 - 20.00%\n" +
	    		"Groups receiving other preference - 20.00%\n" +
	    		"\n" +
	    		"Assignments\n" + 
	    		"1 2\n" +
	    		"2 6\n" +
	    		"3 5\n" +
	    		"4 8\n" +
	    		"5 OP\n" +
	    		"6 8\n" +
	    		"7 3\n" +
	    		"8 8\n" +
	    		"9 1\n" +
	    		"10 4";
	    String result = baos.toString();
	    result = result.substring(0, result.length()-2); //prune end necessary
	    assertEquals(expected, result);
	}

	@Test
	public void testBadInput() throws FileNotFoundException { 
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    PrintStream ps = new PrintStream(baos);
	    // IMPORTANT: Save the old System.out!
	    PrintStream old = System.err;
	    System.setErr(ps);
	    // Sysout from main redirected into string
		MainProgram.main(new String[] {"fileIOTestFilePrefs.txt"});
	    // Put things back
	    System.err.flush();
	    System.setErr(old);
	    /*
	    String expected = MainProgram.ARGS_ERR +"\n";
	    String result = baos.toString();
	    assertEquals(expected, result);
	    */
	}
	
	
	@Test
	public void testFileNotFoundException(){
		
		String prfFile = "pref.txt";
		String prjDscFile = "prjdsc.txt";

		
		 try
		  {
				MainProgram.main(new String[] {prfFile,prjDscFile});

		    fail("Should have thrown FileNotFoundException but did not");
		  }
		  catch( FileNotFoundException e)
		  {
				String err = "File " + prfFile + " not found!";

		    assertEquals(err, e.getMessage());
		  }
	}
	
	
	
	

	
	

}
