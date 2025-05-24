package pgm2_java;

import java.util.ResourceBundle;

public class Pgm2_App {
	public int userlogin(String inuser, String inpwd)
	{
		ResourceBundle rb=ResourceBundle.getBundle("config1");
		String un=rb.getString("username");
		String pwd=rb.getString("pass");
		if(inuser.equals(un)&&inpwd.equals(pwd))
			return 1;
		else
			return 0;
	}
}
