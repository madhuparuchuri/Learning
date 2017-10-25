package madhu.learnings.linked.list;

public class LinkedList<T> 
{
	private Node<T> head;
	
	private Node<T> last;
	
	public void addLast(T data)
	{
		Node<T> newNode = new Node<T>(data, null);
		if(head == null)
		{
			head = last = newNode;
		}
		else
		{
			last.setNext(newNode);
			last= newNode;
		}
	}
	
	public void addFirst(T data)
	{
		Node<T> newNode = new Node<T>(data, null);
		if(head == null)
		{
			head = last = newNode;
		}
		else
		{
			newNode.setNext(head);
			head = newNode;
		}
	}
	
	public T removeFirst()
	{
		if(head == null)
		{
			return null;
		}
		Node<T> temp = head;
		head = head.getNext();
		return temp.getData();
	}
	
	public T removeLast()
	{
		if(head == null)
		{
			return null;
		}
		if(head.getNext() == null)
		{
			T data = head.getData();
			head=null;
			return data;
		}
		Node<T> traverse = head;
		while(traverse.getNext().getNext() != null)
		{
			traverse = traverse.getNext();
		}
		Node<T> temp = traverse.getNext();
		last =traverse;
		traverse.setNext(null);
		return temp.getData();
	}
	
	public boolean addAfter(T after, T data)
	{
		Node<T> traverse = head;
		while(null!=traverse && null != after)
		{
			if(after.equals(traverse.getData()))
			{
				Node<T> newNode = new Node<T>(data, null);
				newNode.setNext(traverse.getNext());
				traverse.setNext(newNode);
				return true;
			}
			traverse = traverse.getNext();
		}
		return false;
	}
	
	public Node<T> getHead()
	{
		return head;
	}
	
	public Node<T> getLast()
	{
		return last;
	}
	
	public T removeAfter(T after)
	{
		Node<T> traverse = head;
		while(null!=traverse && null != after)
		{
			if(after.equals(traverse.getData()))
			{
				Node<T> temp = traverse.getNext();
				traverse.setNext(traverse.getNext().getNext());
				return temp.getData();
			}
			traverse = traverse.getNext();
		}
		return null;
	}
	
	public void makeCircular()
	{
		last.setNext(head);
	}
	
	public void reverse()
	{
		Node<T> prev = null;
		Node<T> curr = head;
		Node<T> next = curr.getNext();
		while(next!= null)
		{
			curr.setNext(prev);
			prev = curr;
			curr = next;
			next = curr.getNext();
		}
		curr.setNext(prev);
		head = curr;
		
	}
	
	@Override
	public String toString() 
	{
		StringBuilder sb = new StringBuilder();
		Node<T> traverse = head;
		while(null != traverse.getNext())
		{
			sb.append(traverse.getData());
			sb.append("-->");
			traverse = traverse.getNext();
		}
		sb.append(traverse.getData());
		return sb.toString();
	}

	public boolean isPalindromeRecurrsive() 
	{
		Node<T> node = head;
		node = isPalindrome(head.getNext());
		return node != null && node.getData().equals(head.getData());
	}

	private Node<T> isPalindrome(Node<T> node) 
	{
		if(node == null)
		{
			return head;
		}
		else
		{
			Node<T> palindrome = isPalindrome(node.getNext());
			if(null != palindrome && palindrome.getData().equals(node.getData()))
			{
				return palindrome.getNext();
			}
			return null;
		}
	}

}
class Node<T>
{
	private T data;
	public T getData() {
		return data;
	}

	private Node<T> next;
	
	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public Node(T data, Node<T> next)
	{
		this.data = data;
		this.next = next;
	}
	
	
}
