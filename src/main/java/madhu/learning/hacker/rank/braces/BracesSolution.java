package madhu.learning.hacker.rank.braces;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class BracesSolution {
	
	/*
	 * Complete the function below.
	 */
	
	boolean compare(int a, int b)
	{
		return a==b;
	}
	
	boolean compare(String a, String b)
	{
		if(null == b && null == a)
		{
			return true;
		}
		return null!=b && b.equals(a);
	}
	
	boolean compare(int[] a, int[] b)
	{
		if(a.length != b.length)
		{
			return false;
		}
		for(int i=0; i<a.length;i++)
		{
			if(a[i] != b[i])
			{
				return false;
			}
		}
		return true;
	}

	    static String[] braces(String[] values) 
	    {
	    	Map<Character, Character> matches = new HashMap<Character, Character>();
	    	matches.put('}', '{');
	    	matches.put(')', '(');
	    	matches.put(']', '[');
	    	if(values.length<1 || values.length >15)
	    	{
	    		throw new IllegalArgumentException("Total string constraint violated which should be between 1 and 15");
	    	}
	    	
	    	String[] results = new String[values.length];
	    	for(int i=0; i<values.length; i++)
	    	{
	    		results[i] = isBracesProper(values[i], matches);
	    	}
	    	
	    	return results;

	    }

	private static String isBracesProper(String string, Map<Character, Character> matches) 
	{
		if(string.length() > 100 || string.length() < 1)
		{
			throw new IllegalArgumentException("string length constraint violated which should be between 1 and 100");
		}
		Stack<Character> stack = new Stack<Character>();
		for(int i=0; i<string.length(); i++)
		{
			char chr = string.charAt(i);
			if(chr == '[' || chr == '(' || chr == '{')
			{
				stack.push(chr);
			}
			if(chr == ']' || chr == ')' || chr == '}')
			{
				if(stack.isEmpty())
				{
					return "NO";
				}
				Character pop = stack.pop();
				if(matches.get(chr) != pop)
				{
					return "NO";
				}
			}
		}
		if(stack.isEmpty())
		{
			return "YES";
		}
		else
		{
			return "NO";
		}
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String nextLine = sc.nextLine();
		int totalLines = Integer.parseInt(nextLine);
		
		String[] inputs = new String[totalLines];
		for(int i=0; i<totalLines; i++)
		{
			inputs[i] = sc.nextLine();
		}
		
		String[] braces = braces(inputs);
		
		for(int i=0; i<totalLines; i++)
		{
			System.out.println(braces[i]);
		}

	}

}
