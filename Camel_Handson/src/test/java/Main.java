import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue; 
public class Main  
{
 	public static void main (String[] args) throws java.lang.Exception 
 	{
 		Node head = null;
 		
 		Map<Integer, Node> nodeMaps = new HashMap<Integer, Node>();
     	//use the following code to fetch input from console 
     	String line; 
     	BufferedReader inp = new BufferedReader (new InputStreamReader(System.in)); 
     	line=inp.readLine();
     	
     	String[] split = line.split(" ");
     	if(split.length !=2)
     	{
     		throw new IllegalArgumentException("Only two numbers seperated by single space will be accepted as input");
     	}
     	
     	int n = Integer.parseInt(split[0]);
     	
     	int d = Integer.parseInt(split[1]);
     	
     	for(int i=0; i<n-1; i++)
     	{
     		line=inp.readLine();
         	
         	String[] conn = line.split(" ");
         	if(conn.length !=2)
         	{
         		throw new IllegalArgumentException("Only two numbers seperated by single space will be accepted as input");
         	}
         	int from = Integer.parseInt(conn[0]);
         	int to = Integer.parseInt(conn[1]);
         	
         	Node fromNode = nodeMaps.get(from);
         	if(null == fromNode)
         	{
         		fromNode = new Node(from);
         	}
         	Node toNode = nodeMaps.get(to);
         	if(null == toNode)
         	{
         		toNode = new Node(to);
         	}
         	makeLink(fromNode, toNode);
         	
         	if(head == null)
         	{
         		head = fromNode;
         	}
         	
         	nodeMaps.put(from, fromNode);
         	nodeMaps.put(to, toNode);
     	}

     	//Use the following code to print output
     	System.out.println(countOfNodeWithDistanceFromHead(d, head));
    }
 	
 	private static void makeLink(Node from, Node to)
 	{
 		from.addLink(to);
 		to.addLink(from);
 	}
 	
 	private static int countOfNodeWithDistanceFromHead(int d, Node head)
 	{
 		Set<Node> visitied = new HashSet<Node>();
 		Queue<Node> nodes = new ArrayBlockingQueue<>(256);
 		nodes.add(head);
 		
 		for(int i=0; i<d; i++)
 		{
 			Queue<Node> newQueue = new ArrayBlockingQueue<>(256);
 			while(false == nodes.isEmpty())
 			{
 				Node poll = nodes.poll();
 				List<Node> links = poll.getLinks();
 				for (Node node : links) {
					if(false == visitied.contains(node))
					{
						newQueue.add(node);
						visitied.add(node);
					}
				}
 			}
 			nodes = newQueue;
 		}
 		return nodes.size();
 		
 		
 		
 		
 	}
}
class Node
{
	private final int data;
	private List<Node> links = new ArrayList();
	
	public Node(int data)
	{
		this.data = data;
	}
	
	public void addLink(Node linkNode)
	{
		links.add(linkNode);
	}
	
	public List<Node> getLinks()
	{
		return links;
	}
}