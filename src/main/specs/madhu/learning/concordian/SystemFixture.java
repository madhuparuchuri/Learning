package madhu.learning.concordian;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.runner.RunWith;

@RunWith(ConcordionRunner.class)
public class SystemFixture 
{
	System system = new System();
	
	   public String getGreeting(String userName){
	      return system.getGreeting(userName);
	   }
}
