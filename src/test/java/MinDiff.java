import java.io.*; 
import java.util.*; 
public class MinDiff  
{
 	public static void main (String[] args) throws java.lang.Exception 
 	{

     	//use the following code to fetch input from console 
     	String line; 
     	BufferedReader inp = new BufferedReader (new InputStreamReader(System.in)); 
     	line=inp.readLine();
      	int arrSize = Integer.parseInt(line);
      
      int[] intArr = new int[arrSize];
      line=inp.readLine();
      String[] split = line.split(" ");
      if(split.length != arrSize)
      {
        throw new RuntimeException("Given array size and no of elemetns given for array not matching");
      }
      for(int i=0; i<arrSize; i++)
      {
        intArr[i] = Integer.parseInt(split[i]);
      }

     	//Use the following code to print output
     	System.out.println(smallestDiff(intArr));
    }
  
  	private static int smallestDiff(int[] arr)
    {
      Arrays.sort(arr);
      
      int minDiff = 0;
      for(int i=1; i<arr.length;i++)
      {
        int dif = arr[i]-arr[i-1];
        if(i==1 || minDiff > dif)
        {
          minDiff = dif;
        }
      }
      return minDiff;
      
    }
   
}