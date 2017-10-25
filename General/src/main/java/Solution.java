import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
    	
    	Map<String, Integer> count = new HashMap<String, Integer>();
    	Scanner sc = new Scanner(System.in);
    	String nextLine = sc.nextLine();
    	int size = Integer.parseInt(nextLine);
    	if(size<0)
    	{
    		throw new RuntimeException("Negetive array excdeption");
    	}
    	
    	for(int i=0; i<size; i++)
    	{
    		String str = sc.nextLine();
    		int curr = getCount(count, str);
    		count.put(str, curr+1);
    	}
    	
    	size = Integer.parseInt(sc.nextLine());
    	if(size<0)
    	{
    		throw new RuntimeException("Negetive array excdeption");
    	}
    	
    	String[] quries = new String[size];
    	for (int i = 0; i < quries.length; i++) {
    		quries[i] = sc.nextLine();
		}
    	
    	for (int i = 0; i < quries.length; i++) {
			System.out.println(getCount(count, quries[i]));
		}
    	
    	
    	
    	
    	
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
    }

	private static int getCount(Map<String, Integer> count, String str) {
		Integer curr = count.get(str);
		if(null == curr)
		{
			return 0;
		}
		return curr;
	}
}