import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
public class InputHandel
{
	public static int rows, cols;
    public static int[][] cells;
    
	public static int[][] main(String[] args) throws Exception {
		String numbs="";
		 int[][] intArray;
		 int[][] finalArray = null;
	    // Read the entire file in
	    List<String> myFileLines = Files.readAllLines(Paths.get("D:/Workplace/test.txt"));

	    // Remove any blank lines
	    for (int i = myFileLines.size() - 1; i >= 0; i--) 
	    {
	        if (myFileLines.get(i).isEmpty()) 
	        {
	        	
	            myFileLines.remove(i);
	        }
	    }

	    //Uses first value to get the number of rows 
	    for (int i =0; i < 1; i++) 
	    {
	    	numbs = myFileLines.get(i);
	    	cols = (Integer.parseInt(numbs));
	    	
	    	
	    }
	    
	  
	    
	  
	    // 2d array with the amount of lines that were read from the file
	     intArray = new int[myFileLines.size()][];
	    int tempCols=0;
	    
	    // Iterate through each row to determine the number of columns
	    for (int i = 0; i < myFileLines.size(); i++)
	    {
	    	
	        // Split the line by spaces
	        String[] splitLine = myFileLines.get(i).split("\\s");

	        // Declare the number of columns in the row from the split
	        intArray[i] = new int[splitLine.length]; 
	       // cols=splitLine.length;
	      
	        
	        for (int j = 0; j < splitLine.length; j++) 
	        {
	          if(i==0 && j==0)
	          {
	        	  break;
	        	  
	          }
	          else
	          {
	           	// Convert each String element to an integer
		            intArray[i][j] = Integer.parseInt(splitLine[j]);
		            
	          }
	        }
	    }
	    
	    
	    int z = 0; // the element to remove
	    
	    
	    intArray = removeRow(intArray,z); 
	    
	    //finds the amount of groups (rows) 
	    int ro=0;
	    for(int i=0;i<intArray.length;i++)
		   {
			   for(int j =0;j<intArray[i].length;j++)
			   {
				   
			   }
			   ro++;
		   }
	    rows=ro;
	   
	  
	   
	    
	   /**
	    * Printing begins now
	    */
	    
	    System.out.println(rows+" groups");
	    System.out.println(cols+" projects");
	    
	    int r =rows;
	    intArray= fillInArray(intArray,r);
	    intArray= removeCol(intArray,z);
	    System.out.println(" ");
	    print(intArray);
		return intArray;
	    
	   
	}
	
	//removes the first row containing the total group number
	
	public static int[][] removeRow(int[][] array,int rowToRemove)

	{
		
		int[][] tempA = new int[array.length - 1][];
		 // copy everything from 0 to  rowToRemove
		 System.arraycopy(array, 0, tempA, 0,  rowToRemove);
		 // copy from  rowToRemove+1 to end
		 System.arraycopy(array,  rowToRemove + 1, tempA,  rowToRemove, array.length -  rowToRemove - 1);
		 // reassign
	    return tempA;		
	}
	
	//removes group numbers 
	public static int[][] removeCol(int[][] array,int col)
	{
		int[][] temp=null;
		if(array != null && array.length > 0 && array[0].length > col)
	    {
	        temp = new int[array.length][array[0].length-1];
	        for(int i =0;i<array.length;i++)
	        { 
	            int newColIdx = 0;
	            for(int j =0;j<array[i].length;j++)
	            {
	                if(j!=col)
	                {
	                    temp[i][newColIdx] = array[i][j];
	                    newColIdx++;
	                }               
	            }
	        }
	    }
		return temp;       
	}
	public static int[][] fillInArray(int[][] array,int col)
	{
		int largestRow=0;
		int[][]finalA;
		//int c=0;
		
		//array to find largest row size
		for(int i=0;i<array.length;i++)
		{
			int c=0;
			for(int j=0;j<array[i].length;j++)
			{
				c++;
			}
			if(c>largestRow)
			{
				largestRow=c;
			}
		}
		int val=0;
		finalA=new int[col][largestRow];
		int intilizer = 20;
		/**
		 * ^ change this for weighted-ness
		 */
		for(int i=0; i<col;i++)
		{
			for(int j = 0; j<largestRow;j++)
			{
				finalA[i][j]=intilizer;
			}
		}
		for(int i=0;i<array.length;i++)
		{
			int c=0;
			for(int j=0;j<array[i].length;j++)
			{
				val=array[i][j];
				c++;
			}
			c-=1;
			
			for(;c>=0;c--)
			{
				finalA[i][c]=array[i][c];
			}
		}
		
		
		return finalA;
		
	}
	public static void print(int[][] array)
	{
		for (int[] row : array) 
		{
	        for (int col : row) 
	        {
	            System.out.printf("%5d ", col);
	        }
	        System.out.println();
		}
	}
	
	
}