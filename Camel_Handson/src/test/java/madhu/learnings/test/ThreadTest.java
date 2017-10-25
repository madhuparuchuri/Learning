package madhu.learnings.test;

public class ThreadTest extends Thread{
	
	public Thread t;
	
	public ThreadTest()
	{
		t = new Thread(this, "My Thread");
		t.start();
	}
	
	@Override
	public void run() {
		try
		{
			t.join();
			System.out.println(t.getName());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("exception");
		}
	
	}

	public static void main(String[] args) {
//		new ThreadTest();
		
		try
		{
			System.exit(1);
			throw new NullPointerException();
		}
		catch(Exception e)
		{
			System.out.println("exception");
			
			throw new RuntimeException("Runtime...");
		}
		finally
		{
			System.out.println("finally");
		}
	}

}
