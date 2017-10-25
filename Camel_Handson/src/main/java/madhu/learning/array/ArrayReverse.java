package madhu.learning.array;
import java.io.*;
import java.util.*;

public class ArrayReverse {

    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String nextLine = sc.nextLine();
    	int size = Integer.parseInt(nextLine);
    	if(size<0)
    	{
    		throw new RuntimeException("Negetive array excdeption");
    	}
    	
    	String arrayString = sc.nextLine();
    	String[] split = arrayString.split(" ");
    	
    	if(split.length != size)
    	{
    		throw new RuntimeException("Array out bound exception");
    	}
    	
    	for (int i = split.length-1; i>=0; i--) {
    		System.out.print(Integer.parseInt(split[i])+" ");
			
		}
    	
    	
    	
    	
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }
}