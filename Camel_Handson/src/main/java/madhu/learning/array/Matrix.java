package madhu.learning.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Matrix 
{
	private int size;
	
	private int[][] mat;
	
	private Map<Pair,List<String>> paths= new HashMap<Pair,List<String>>();

	public Matrix(int size)
	{
		this.size = size;
		mat = new int[size][size];
	}
	
	public void readMatrix()
	{
		Scanner sc = new Scanner(System.in);
		
		String[] input = new String[size];
		for(int i=0;i<size; i++)
		{
			input[i] = sc.nextLine();
		}
		populateToArray(input);
	}

	private void populateToArray(String[] input) 
	{
		for(int i=0;i<size; i++)
		{
			String str = input[i];
			String[] numbers = str.split(" ");
			for(int j=0; j<size; j++)
			{
				mat[i][j] = Integer.parseInt(numbers[j]);
			}
		}
	}
	
	public List<String> getAllPath(int s, int d)
	{
		int i=0, j=0;
		for(;i<size;i++)
		{
			for(j=0;j<size;j++)
			{
				if(mat[i][j] == s)
				{
					break;
				}
			}
			if(j<size)
			{
				break;
			}
		}
		
		return getPaths(i,j,d);
	}

	private List<String> getPaths(int i, int j, int d) 
	{
		Pair pair = new Pair(i,j);
		List<String> list = paths.get(pair);
		if(null != list)
		{
			return list;
		}
		List<String> pathList = new ArrayList<String>();
		paths.put(pair, pathList);
		if(mat[i][j] == d)
		{
			pathList.add(""+d);
			return pathList;
		}
		int iLow = i==0?0:i-1;
		int jLow = j==0?0:j-1;
		int iHigh = i==size-1?size-1:i+1;
		int jHigh = j==size-1?size-1:j+1;
		for(int l=iLow;l<=iHigh;l++)
		{
			for(int k=jLow;k<=jHigh;k++)
			{
				int val = mat[l][k];
				if(val > mat[i][j])
				{
					List<String> currPaths = getPaths(l, k, d);
					populatePaths(pathList, mat[i][j], currPaths);
				}
			}
		}
		
		return pathList;
	}

	private void populatePaths(List<String> pathList, int val, List<String> currPaths) {
		for(String currPath : currPaths)
		{
			pathList.add(val+"-->"+currPath);
		}
	}
	
	class Pair
	{
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + i;
			result = prime * result + j;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (i != other.i)
				return false;
			if (j != other.j)
				return false;
			return true;
		}

		private int i;
		private int j;

		public Pair(int i, int j)
		{
			this.i = i;
			this.j = j;
		}

		public int getI() {
			return i;
		}

		public int getJ() {
			return j;
		}
	}
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		int size = Integer.parseInt(sc.nextLine());
		Matrix mat = new Matrix(size);
		mat.readMatrix();
		System.out.println(mat.getAllPath(2, 14));
	}
}
