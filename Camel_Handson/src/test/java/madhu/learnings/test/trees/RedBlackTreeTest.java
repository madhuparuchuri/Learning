package madhu.learnings.test.trees;

import java.util.Stack;

import madhu.learning.trees.RedBlackTree;
import madhu.learning.trees.TreeNode;

public class RedBlackTreeTest 
{
	public static void main(String[] args) 
	{
//		RedBlackTree<Integer> tree = test1();
//		RedBlackTree<Integer> tree = test2();
//		
//		System.out.println(tree.inorder());
//		System.out.println(tree.getHeight());
//		
//		System.out.println(tree.levelOrder());
		
		RedBlackTree<Integer> tree1 = constructTree(1,2,3,4,5,6,7,8, 10, 12,13,14,15);
		RedBlackTree<Integer> tree2 = constructTree(9,11,12,16,17,18,19,20,21,22);
		
		printSortedOrderOfTwoBST(tree1, tree2);
	}

	private static RedBlackTree<Integer> test1() {
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		
		tree.insert(10);
		tree.insert(12);
		tree.insert(11);
		tree.insert(7);
		tree.insert(13);
		tree.insert(9);
		tree.insert(8);
		tree.insert(4);
		tree.insert(5);
		tree.insert(3);
		tree.insert(1);
		tree.insert(6);
		tree.insert(2);
		return tree;
	}
	
	private static RedBlackTree<Integer> test2() {
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		
		tree.insert(1);
		tree.insert(2);
		tree.insert(3);
		tree.insert(4);
		tree.insert(5);
		tree.insert(6);
		tree.insert(7);
		tree.insert(8);
		tree.insert(9);
		tree.insert(10);
		tree.insert(11);
		tree.insert(12);
		tree.insert(13);
		tree.insert(14);
		tree.insert(15);
		tree.insert(16);
		tree.insert(17);
		tree.insert(18);
		
		return tree;
	}
	
	private static RedBlackTree<Integer> constructTree(Integer... integers)
	{
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		for(Integer integer : integers)
		{
			tree.insert(integer);
		}
		return tree;
	}
	
	private static void printSortedOrderOfTwoBST(RedBlackTree<Integer> tree1, RedBlackTree<Integer> tree2)
	{
		Stack<TreeNode<Integer>> treeStack1 = new Stack<TreeNode<Integer>>();
		Stack<TreeNode<Integer>> treeStack2 = new Stack<TreeNode<Integer>>();
		
		TreeNode<Integer> current1 = tree1.getRootNode();
		TreeNode<Integer> current2 = tree2.getRootNode();
		while(current1!=null || !treeStack1.isEmpty() || current2 != null || !treeStack2.isEmpty())
		{
			if(null != current1 || null != current2)
			{
				if(null != current1)
				{
					treeStack1.push(current1);
					current1 = current1.getLeft();
				}
				
				if(null != current2)
				{
					treeStack2.push(current2);
					current2 = current2.getLeft();
				}
			}
			else
			{
				if(treeStack1.isEmpty())
				{
					while(!treeStack2.isEmpty())
					{
						current2 = treeStack2.pop();
						System.out.println(current2.getData());
						inorder(current2.getRight());
					}
					return;
				}
				
				if(treeStack2.isEmpty())
				{
					while(!treeStack1.isEmpty())
					{
						current1 = treeStack1.pop();
						System.out.println(current1.getData());
						inorder(current1.getRight());
					}
					return;
				}
				
				current1 = treeStack1.pop();
				current2 = treeStack2.pop();
				
				if(current1.getData() < current2.getData())
				{
					System.out.println(current1.getData());
					current1 = current1.getRight();
					treeStack2.push(current2);
					current2=null;
				}
				else
				{
					System.out.println(current2.getData());
					current2 = current2.getRight();
					treeStack1.push(current1);
					current1=null;
				}
			}
			
			
		}
	}
	
	private static void inorder(TreeNode<Integer> node)
	{
		if(null != node)
		{
			inorder(node.getLeft());
			System.out.println(node.getData());
			inorder(node.getRight());
		}
	}
}
