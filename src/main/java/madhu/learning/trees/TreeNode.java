package madhu.learning.trees;

public class TreeNode<T> 
{
	private TreeNode<T> left;
	private TreeNode<T> right;
	private TreeNode<T> parent;
	public TreeNode<T> getParent() {
		return parent;
	}

	public void setParent(TreeNode<T> parent) {
		this.parent = parent;
	}
	private T data;
	public TreeNode(T data)
	{
		this.data = data;
	}
	
	public TreeNode<T> getLeft() {
		return left;
	}
	public void setLeft(TreeNode<T> left) {
		this.left = left;
	}
	public TreeNode<T> getRight() {
		return right;
	}
	public void setRight(TreeNode<T> right) {
		this.right = right;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public int getHeight()
	{
		int leftHeight = 0;
		if(null != left)
		{
			leftHeight = left.getHeight();
		}
		int rightHeight = 0;
		if(null != right)
		{
			rightHeight = right.getHeight();
		}
		return 1 + max(leftHeight, rightHeight);
	}

	private int max(int leftHeight, int rightHeight) 
	{
		return leftHeight > rightHeight ? leftHeight : rightHeight;
	}
}
