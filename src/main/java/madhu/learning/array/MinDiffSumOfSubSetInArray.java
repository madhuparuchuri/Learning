package madhu.learning.array;

import java.util.Arrays;

public class MinDiffSumOfSubSetInArray 
{
	public int minDiffBySort(int[] arr)
	{
		int sum1=0, sum2 =0;
		
		Arrays.sort(arr);
		
		for(int i=arr.length-1; i>=0; i--)
		{
			if(sum1 < sum2)
			{
				sum1+=arr[i];
			}
			else
			{
				sum2 +=arr[i];
			}
		}
		
		return sum1 > sum2 ? sum1-sum2 : sum2 -sum1;
	}
	
	public int minDiffByRecurrsion(int[] arr)
	{
		int sum=0;
		for (int i = 0; i < arr.length; i++) {
			sum+= arr[i];
		}
		return findMinDiff(arr, arr.length, 0,sum);
	}

	private int findMinDiff(int[] arr, int i, int sumIncluded, int totSum) 
	{
		if(i==0)
		{
			return Math.abs((totSum - sumIncluded)-sumIncluded);
		}
		int min = Math.min(findMinDiff(arr, i-1, sumIncluded+arr[i-1],totSum), findMinDiff(arr, i-1, sumIncluded,totSum));
		return min;
	}
	
	public int minDiffByDP(int[] arr)
	{
		int sum=0;
		for (int i = 0; i < arr.length; i++) {
			sum+= arr[i];
		}
		
		boolean[][] dp=new boolean[arr.length+1][sum+1];
		for(int i=0; i<=arr.length; i++)
		{
			//Zero sum is possible
			dp[i][0] = true;
		}
		
		for(int i=1; i<=arr.length; i++)
		{
			for(int j=1; j<=sum; j++)
			{
				dp[i][j]=dp[i-1][j];
				
				if(arr[i-1] <=j)
				{
					dp[i][j]|=dp[i-1][j-arr[i-1]];
				}
			}
			
		}
		int diff = sum;
		for(int j=sum/2;j>=0;j--)
		{
			if(dp[arr.length][j]==true)
			{
				diff = sum-2*j;
				break;
			}
		}
		
		return diff;
	}
}
