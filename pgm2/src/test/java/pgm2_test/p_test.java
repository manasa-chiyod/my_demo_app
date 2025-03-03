package pgm2_test;

import org.testng.Assert;
import org.testng.annotations.Test;


import pgm2_java.Pgm2_App;

public class p_test {
	@Test
	
	public void testlogin1()
	{
		Pgm2_App a=new Pgm2_App();
		Assert.assertEquals(0,a.userlogin("abc","abc123"));
	}
 @Test
 	public void testlogin2()
	{
	 Pgm2_App a=new Pgm2_App();
		Assert.assertEquals(1,a.userlogin("abc","abc@123"));
	}
}
