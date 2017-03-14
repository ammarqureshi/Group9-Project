
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OwnProjects {
	 
	public static void main(String[] args)
	{
		/*
		
		String progectPrefInArray=
				 "15\n" +
			     "1 1 OP 4 5\n" +
			     "2 2 3 5 OP\n" +
			     "3 3 4 5 1 2\n"+
			     "4 9 15 OP 8 4\n"+
				 "5 1 OP 5 7\n"+
				 "6 14 1 OP 5 7\n";
		
	

		String vettedProjects =  "2 \n"+
							    "1\n" +
							    "4\n";
	
	        */
	    
	   // System.out.println( removeNonVet(progectPrefInArray,vettedProjects));
	     
		
	
    } 
	/**
	 * removes all OP from all non vetted groups
	 * @param progectPrefInArray
	 * @param vettedProjects
	 * @return String of final groups 
	 */
	public static String removeNonVet(String progectPrefInArray,String vettedProjects)
	{
		String[] vettedP=null;
		vettedP=stringToArray(vettedProjects,vettedP);
		 String[][]projectPref=readArray(progectPrefInArray);
	      
		int currentProjectPref =0;
	      String currrentPref="";
	      for(int i=0;i<projectPref.length;i++)
	      {
	    	  for(int j=0;j<projectPref[i].length;j++)
	    	  {
	    		  if(j>0)
	    		  {
	    			  currentProjectPref=Integer.parseInt(projectPref[i][0]);
	    			  
	    		  
	    		  
		    		  currrentPref=projectPref[i][j];
		    		  if(currrentPref.equals("OP") ||currrentPref.equals("op") )
		    		  {
		    			 // if(vettedP[])
		    			  if((compareToVetted(vettedP,currentProjectPref))==true)
		    			  {
		    				  
		    				 projectPref[i][j]="no";//if their not vetted to do their own
		    				 						// project they get a no
		    			  }
		    			  else
		    			  {
		    				  
		    			  }
		    		  }
	    		  }
	    		  
	    	  }
	      }
	      String returningString ="";
	      
		     returningString=Arrays.deepToString(projectPref);
		     
		    returningString= removeVal(projectPref);
		   
	      return returningString;
	    
	}
	
	 /**
	  * 
	  * @param array
	  * @return
	  */
	public static String removeVal(String[][] array)
	{
		String stringReturn = "";
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<array.length;i++)
		{
			for(int j=0;j<array[i].length;j++)
			{
				if(!(array[i][j].equals("no")))
				{
					list.add(array[i][j]);
				}
				else
				{
					
				}
			}
		}
		stringReturn=Arrays.toString(list.toArray());
		return stringReturn;  
	}
	/**
	 * Checks if the OP passed , the group that it is in is equal to the vetted OP groups
	 * @param vettedArray
	 * @param valToCompare
	 * @return boolean (true/false)
	 */
	public static boolean compareToVetted(String[] vettedArray,int valToCompare)
	{
		boolean isNotVetted=true;
		int v=1;
		for(int i=1;i<vettedArray.length;i++)
		{
		
			if(valToCompare==Integer.parseInt(vettedArray[i]))
			{
				isNotVetted=false;
				v++;
				
			}
			
		}
		
		return isNotVetted;
		
	}
	private static String[] stringToArray(String temp,String[] arrayToChange)
	{
		arrayToChange= temp.split("\n"); 
		return arrayToChange;
		
	}
	/**
	 * takes in a string and converts it to a String[][]
	 * @param temp 
	 * @return String[][]
	 * ArrayList<String[]> a=new ArrayList(Arrays.asList(arr));
		a.remove(iEmployee);
	 */
	private static String[][] readArray(String temp)
	{
		
		String[] rows ;
		
		
		rows= temp.split("\n"); 
		
	  // Get the number of columns in a row
	     int numberOfColumns = rows[0].split(" ").length;

	     // Setup your matrix
	     String[][] matrix = new String[rows.length][numberOfColumns];

	     // Populate your matrix
	     for (int i = 0; i < rows.length; i++) {
	         matrix[i] = rows[i].split(" ");
	     }
	     // Display your matrix
	     System.out.println(" ");
	    
		return matrix;
	}
	
}
