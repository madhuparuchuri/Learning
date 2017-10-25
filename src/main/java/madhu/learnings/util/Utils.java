package madhu.learnings.util;

import madhu.learnings.linked.list.LinkedList;
import madhu.learnings.linked.list.LinkedListPrgm;

public class Utils 
{
	public static LinkedList<String> createLinkedList(String listPattern)
	{
		LinkedList<String> list = new LinkedList<String>();
		String[] split = listPattern.split("-->");
		for (int i = 0; i < split.length; i++) 
		{
			list.addLast(split[i]);
		}
		return list;
	}
	
	public static LinkedList<String> createCircularLinkedList(String listPattern)
	{
		LinkedList<String> list = new LinkedList<String>();
		String[] split = listPattern.split("-->");
		for (int i = 0; i < split.length; i++) 
		{
			list.addLast(split[i]);
		}
		list.makeCircular();
		return list;
	}
	
	public static void main(String[] args) 
	{
		LinkedList<String> list = Utils.createLinkedList("12-->34-->21-->56-->756-->542-->98-->3-->20");
		System.out.println(list);
		list.addAfter("756", "1212");
		System.out.println(list);
		list.addFirst("55");
		list.addFirst("66");
		list.addLast("111");
		list.addLast("222");
		System.out.println(list);
		System.out.println(list.removeLast());
		System.out.println(list.removeFirst());
		System.out.println(list.removeFirst());
		System.out.println(list.removeAfter("542"));
		System.out.println(list);
		list.addLast("989");
		
		System.out.println(LinkedListPrgm.getMidNode(list));
		
		
		System.out.println("Expected false::"+LinkedListPrgm.isCircularList(list));
		
		LinkedList<String> circularList = Utils.createCircularLinkedList("12-->34-->21-->56-->756-->542-->98-->3-->20");
		System.out.println("Expected true::"+LinkedListPrgm.isCircularList(circularList));
		
		
		 list = Utils.createLinkedList("12-->34-->21-->56-->756-->542-->98-->3-->20");
		 System.out.println(list);
		 list.reverse();
		 System.out.println(list);
		 
		 LinkedList<String> linkedList = Utils.createLinkedList("12-->13-->13-->12");
		 System.out.println(linkedList.isPalindromeRecurrsive());
		 
		 linkedList = Utils.createLinkedList("12");
		 System.out.println(linkedList.isPalindromeRecurrsive());
		 
		 linkedList = Utils.createLinkedList("12-->13-->14-->15-->14-->13-->12");
		 System.out.println(linkedList.isPalindromeRecurrsive());
		 
		 linkedList = Utils.createLinkedList("12-->13-->14-->15-->20-->13-->12");
		 System.out.println(linkedList.isPalindromeRecurrsive());
		
	}
}
