package Buffer;

public class logins extends signUp{
	 /* public void authenticate(String usernames, String passwords) {
	        // Check if the username exists in the HashMap
	        if (users.containsKey(usernames)) {
	        	//String p=users.get(usernames);
	        	//if(p.equals(passwords))
	        	//{
	        		//System.out.println("login succuessful!");
	        	//}
	        }*/
	        public boolean authenticate(String username, String password) {
	            if (users.containsKey(username)) {
	                return users.get(username).equals(password);
	            }
	            return false;
	        }

	       
	    }

