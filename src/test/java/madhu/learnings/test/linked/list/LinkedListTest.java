package madhu.learnings.test.linked.list;

import org.junit.Test;

import junit.framework.Assert;
import madhu.learnings.linked.list.LinkedList;
import madhu.learnings.util.Utils;

public class LinkedListTest 
{
	@Test
	public void testCreateLinkedList() throws Exception 
	{
		LinkedList<String> list = Utils.createLinkedList("12-->34-->21-->56-->756-->542-->98-->3-->20");
		Assert.assertEquals("12-->34-->21-->56-->756-->542-->98-->3-->20", list.toString());
	}

}
