package madhu.learning.trees;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class RedBlackTree<T> extends BinarySearchTree<T>
{
	private Comparator<T> comparator;
	
	private RedBlackTreeNode<T> rootNode;
	
	private int size;
	
	public RedBlackTree()
	{
		
	}
	public RedBlackTree(Comparator<T> comparator)
	{
		this.comparator = comparator;
	}
	
	public void insert(T data)
	{
		size++;
		RedBlackTreeNode<T> node = new RedBlackTreeNode<T>(data);
		if(rootNode == null)
		{
			rootNode = node;
			rootNode.setColour(Colour.BLACK);
			return;
		}
		
		RedBlackTreeNode<T> traverseNode = rootNode;
		while(traverseNode != null)
		{
			int result = compare(traverseNode.getData(), data);
			if(result == -1)
			{
				if(traverseNode.getRight() == null)
				{
					traverseNode.setRight(node);
					node.setParent(traverseNode);
					break;
				}
				else
				{
					traverseNode = (RedBlackTreeNode<T>) traverseNode.getRight();
				}
			}
			else
			{
				if(traverseNode.getLeft() == null)
				{
					traverseNode.setLeft(node);
					node.setParent(traverseNode);
					break;
				}
				else
				{
					traverseNode = (RedBlackTreeNode<T>) traverseNode.getLeft();
				}
			}
		}
		
		rebalance(node);
	}
	
	public int getHeight()
	{
		return rootNode.getHeight();
	}
	
	private void rebalance(RedBlackTreeNode<T> node) 
	{
		if(node == rootNode)
		{
			node.setColour(Colour.BLACK);
			return;
		}
		RedBlackTreeNode<T> parent = (RedBlackTreeNode<T>) node.getParent();
		if(parent.getColour() == Colour.RED)
		{
			RedBlackTreeNode<T> uncle = getUncle(node);
			if(uncle != null && uncle.getColour() == Colour.RED)
			{
				parent.setColour(Colour.BLACK);
				uncle.setColour(Colour.BLACK);
				RedBlackTreeNode<T> grandParent = (RedBlackTreeNode<T>) parent.getParent();
				grandParent.setColour(Colour.RED);
				rebalance(grandParent);
			}
			else
			{
				rotate(node, parent);
			}
		}
	}
	
	private void rotate(RedBlackTreeNode<T> node, RedBlackTreeNode<T> parent) 
	{
		RedBlackTreeNode<T> grandParent = (RedBlackTreeNode<T>) parent.getParent();
		if(isLeftChild(parent, grandParent))
		{
			if(false == isLeftChild(node, parent))
			{
				leftRotate(node);
				
			}
			rightRotate(parent);
		}
		else
		{
			if(isLeftChild(node, parent))
			{
				rightRotate(node);
			}
			leftRotate(parent);
		}
		swapColors(parent,grandParent);
	}
	
	private void swapColors(RedBlackTreeNode<T> parent, RedBlackTreeNode<T> grandParent) 
	{
		Colour colour = grandParent.getColour();
		grandParent.setColour(parent.getColour());
		parent.setColour(colour);
	}
	private RedBlackTreeNode<T> getUncle(RedBlackTreeNode<T> node)
	{
		TreeNode<T> parent = node.getParent();
		
		TreeNode<T> grandParent = parent.getParent();
		if(null != grandParent)
		{
			if(isLeftChild(parent, grandParent))
			{
				return (RedBlackTreeNode<T>) grandParent.getRight();
			}
			return (RedBlackTreeNode<T>) grandParent.getLeft();
		}
		return null;
	}
	private boolean isLeftChild(TreeNode<T> node, TreeNode<T> parent) {
		return parent.getLeft() == node;
	}
	private void leftRotate(TreeNode<T> node)
	{
		TreeNode<T> parent = node.getParent();
		TreeNode<T> grandParent = parent.getParent();
		
		
		TreeNode<T> left = node.getLeft();
		parent.setRight(left);
		if(null != left)
		{
			left.setParent(parent);
		}
		
		node.setLeft(parent);
		parent.setParent(node);
		
		if(parent == rootNode)
		{
			rootNode = (RedBlackTreeNode<T>) node;
		}
		
		node.setParent(grandParent);
		
		if(null != grandParent)
		{
			boolean isLeftChild = isLeftChild(parent, grandParent);
			if(isLeftChild)
			{
				grandParent.setLeft(node);
			}
			else
			{
				grandParent.setRight(node);
			}
		}
	}
	
	private void rightRotate(TreeNode<T> node)
	{
		TreeNode<T> parent = node.getParent();
		TreeNode<T> grandParent = parent.getParent();
		
		
		TreeNode<T> right = node.getRight();
		parent.setLeft(right);
		if(null != right)
		{
			right.setParent(parent);
		}
		
		node.setRight(parent);
		parent.setParent(node);
		
		node.setParent(grandParent);
		
		if(parent == rootNode)
		{
			rootNode = (RedBlackTreeNode<T>) node;
		}
		
		if(null != grandParent)
		{
			boolean isLeftChild = isLeftChild(parent, grandParent);
			if(isLeftChild)
			{
				grandParent.setLeft(node);
			}
			else
			{
				grandParent.setRight(node);
			}
		}
	}
	
	private int compare(T data, T data2) 
	{
		if(comparator != null)
		{
			return comparator.compare(data, data2);
		}
		return ((Comparable<T>)data).compareTo(data2);
	}
	public T delete(T data)
	{
		TreeNode<T> node = findNode(data);
		if(null == node)
		{
			return null;
		}
		size--;
		TreeNode<T> parent = node.getParent();
		TreeNode<T> left = node.getLeft();
		TreeNode<T> right = node.getRight();
		if(left ==null && right == null)
		{
			if(node == rootNode)
			{
				rootNode = null;
			}
			if(isLeftChild(node, parent))
			{
				parent.setLeft(null);
			}
			else
			{
				parent.setRight(null);
			}
		}
		else if(left !=null && right != null)
		{
			TreeNode<T> inOrderPredecsor = removeInOrderPredecesorAndGet(node);
			left.setParent(inOrderPredecsor);
			inOrderPredecsor.setLeft(left);
			
			right.setParent(inOrderPredecsor);
			inOrderPredecsor.setRight(right);
			if(rootNode == node)
			{
				rootNode = (RedBlackTreeNode<T>) inOrderPredecsor;
			}
		}
		else
		{
			if(left!=null)
			{
				if(rootNode == node)
				{
					rootNode = (RedBlackTreeNode<T>) left;
				}
				else
				{
					parent.setLeft(left);
				}
			}
			else
			{
				if(rootNode == node)
				{
					rootNode = (RedBlackTreeNode<T>) right;
				}
				else
				{
					parent.setRight(right);
				}
			}
		}
		return node.getData();
	}
	
	private TreeNode<T> removeInOrderPredecesorAndGet(TreeNode<T> node) 
	{
		 node = node.getLeft();
		 while(node.getRight() != null)
		 {
			 node = node.getRight();
		 }
		return node;
	}
	private TreeNode<T> findNode(T data) 
	{
		return find(rootNode, data);
	}
	
	private TreeNode<T> find(TreeNode<T> node, T data) 
	{
		if(node.getData() == data)
		{
			return node;
		}
		if(-1 == compare(node.getData(), data))
		{
			TreeNode<T> left = node.getLeft();
			if(left == null)
			{
				return null;
			}
			return find(left, data);
		}
			TreeNode<T> right = node.getRight();
			if(right == null)
			{
				return null;
			}
			return find(right, data);
	}
	public boolean search(T data)
	{
		return null != findNode(data);
	}
	
	public int size()
	{
		return size;
	}
	
	public List<T> inorder()
	{
		List<T> traverseList = new ArrayList<T>();
		inorderTraversalRecursive(rootNode,traverseList);
		return traverseList;
	}
	private void inorderTraversalRecursive(TreeNode<T> node, List<T> traverseList) 
	{
		if(null !=node)
		{
			inorderTraversalRecursive(node.getLeft(), traverseList);
			traverseList.add(node.getData());
			inorderTraversalRecursive(node.getRight(), traverseList);
		}
	}
	public List<T> levelOrder() {
		List<T> traverseList = new ArrayList<T>();
		Queue<TreeNode<T>> queue = new ArrayBlockingQueue<TreeNode<T>>(size);
		queue.add(rootNode);
		while(false == queue.isEmpty())
		{
			TreeNode<T> remove = queue.remove();
			if(null!=remove.getLeft())
			{
				queue.add(remove.getLeft());
			}
			if(null!=remove.getRight())
			{
				queue.add(remove.getRight());
			}
			traverseList.add(remove.getData());
		}
		return traverseList;
	}
	
	public RedBlackTreeNode<T> getRootNode()
	{
		return rootNode;
	}
	
}

class RedBlackTreeNode<T> extends TreeNode<T>
{
	private Colour colour;

	public RedBlackTreeNode(T data)
	{
		super(data);
		this.colour = Colour.RED;
	}
	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		this.colour = colour;
	}
	
	@Override
	public String toString() {
		char c = Colour.RED == colour?'R' :'B';
		return ""+getData() + ':'+c;
	}
	
}
enum Colour{
	RED, BLACK;
}
