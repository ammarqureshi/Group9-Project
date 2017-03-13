
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
	
	        
	    
	     System.out.println(removeNonVet(progectPrefInArray,vettedProjects));
	     
		*/
	
    } 
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
		    		  if(currrentPref.equals("OP"))
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
		    // System.out.println(returningString);
	      return returningString;
	    
	}
	/*
	 //this would be a 2nd approach to it , not working so commented out
	public static String[][] removeVal(String[][] array)
	{
		
		//String[][] tempArray= new String[array.length-1][array.length-1];

        ArrayList<String[]> stockout = new ArrayList<String[]>(Arrays.asList(array));
        System.out.println("length before = " + stockout.size());

        for (int i=0 ; i <= stockout.size(); i++) 
        {
            for (int j = 0; j < stockout.size(); j++) {
                if (array[i][j].equals("-1")) 
                {
                    System.out.println("removing " + stockout.get(j)+stockout.get(i));
                    stockout.remove(i);
                }
            }
        }
        String[][] remainingStockout = (String[][]) stockout.toArray(new String[][] {});
        System.out.println("length after = " + remainingStockout.length);
        return remainingStockout;
		
	}*/
	/**
	 * 
	 * @param vettedArray
	 * @param valToCompare
	 * @return
	 */
	public static boolean compareToVetted(String[] vettedArray,int valToCompare)
	{
		boolean isNotVetted=true;
		for(int i=0;i<vettedArray.length;i++)
		{ int v=1;
			if(valToCompare==Integer.parseInt(vettedArray[v]))
			{
				isNotVetted=false;
				break;
			}
			v++;
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
