package Buffer;
import java.util.HashMap;
import java.util.*;
public class signUp {
	public  HashMap<String, String> users=new HashMap<>();
    public boolean signUp(String username, String password)
{
	 if (users.containsKey(username)) {
         System.out.println("Username already exists. Please choose a different username.");
         return false;
     } else {
         users.put(username, password);
         System.out.println("Sign-up successful! You can now log in with your credentials.");
         return true;
     }
 }	
}

