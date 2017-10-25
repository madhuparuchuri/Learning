package madhu.learnings.test.array;

import madhu.learning.array.MinDiffSumOfSubSetInArray;

public class MinDiffSetTest 
{
	public static void main(String[] args) 
	{
		int[] arr = {3, 1, 4, 2, 2, 1};
		MinDiffSumOfSubSetInArray minDiff = new MinDiffSumOfSubSetInArray();
//		System.out.println(minDiff.minDiffBySort(arr));
		System.out.println(minDiff.minDiffByRecurrsion(arr));
		System.out.println(minDiff.minDiffByDP(arr));
	}
}
