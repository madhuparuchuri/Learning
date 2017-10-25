package madhu.learnings.linked.list;

public class LinkedListPrgm 
{
	public static String getMidNode(LinkedList<String> list)
	{
		Node<String> fastNode = list.getHead();
		Node<String> slowNode = list.getHead();
		
		while(fastNode.getNext() != null && fastNode.getNext().getNext() != null)
		{
			fastNode = fastNode.getNext().getNext();
			slowNode = slowNode.getNext();
		}
		return slowNode.getData();
	}
	
	public static boolean isCircularList(LinkedList<String> list)
	{
		Node<String> fastNode = list.getHead();
		Node<String> slowNode = list.getHead();
		
		while(fastNode.getNext() != null && fastNode.getNext().getNext() != null)
		{
			fastNode = fastNode.getNext().getNext();
			slowNode = slowNode.getNext();
			if(fastNode == slowNode)
			{
				return true;
			}
		}
		return false;
		
	}
	
	public static boolean isPalindrome(LinkedList<String> list)
	{
		return list.isPalindromeRecurrsive();
	}
	public static void reverseLinkedList(LinkedList<String> list)
	{
		list.reverse();
	}
	
	

}
