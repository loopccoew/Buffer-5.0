package Buffer;
import java.util.*;
import Buffer.event;

public class pQueue {
    static ArrayList <user> cleanupdrive11List=new ArrayList<>();
    static ArrayList <user> treeplantationdrive20List=new ArrayList<>();
	static PriorityQueue<event> pqJan=new PriorityQueue<>(); 
	
	public static void addJan(){
	Scanner sc=new Scanner(System.in);
	String b;
	String a;
    String z;
    String y;
    int x;
	int c;
	       
 
 
 
 do {
 System.out.println("Do you wish to insert more events say yes or no");
  a=sc.next();
 if(a.equals("yes"))
 {
	 System.out.println("Enter the event");
	 z=sc.next();
	 System.out.println("Enter the date");
	  x=sc.nextInt();
	  System.out.println("Enter the location");
	  y=sc.next();
	  
	 pqJan.add(new event(x,z,y));
	
 }
 }while (a.equals("yes"));
 dispadmin() ;
	}
 public static void displayPQ() {
	 pqJan.add(new event(11,"Cleanup-Drive","Bhusari Colony"));
	 pqJan.add(new event(20,"Tree Plantation Drive","ARAI "));
	 Scanner sc=new Scanner(System.in);
Iterator itr=pqJan.iterator();
System.out.println("Following are the events ");
 while(itr.hasNext())
 {
	 System.out.println(pqJan.poll().toString());
 }
System.out.println("Do you wish to register for any of these");
String choice=sc.next();
 if(choice.equalsIgnoreCase("yes"))
 {
	 System.out.println("Please enter the event number");
	 int event=sc.nextInt();
	 if(event==1) {
	 System.out.println("Enter email: ");
     String email = sc.next();
     System.out.println("Enter name: ");
     String name = sc.next();
     System.out.println("Enter surname: ");
     String surname = sc.next();
     System.out.println("Enter phone number: ");
     double phoneNumber = sc.nextDouble();
	 user user1=new user(email, name, phoneNumber,surname);
	 cleanupdrive11List.add(user1);
	 System.out.println("Person registered successfully!");
	 }
	 else if(event==2) {
		 System.out.println("Enter email: ");
	     String email = sc.next();
	     System.out.println("Enter name: ");
	     String name = sc.next();
	     System.out.println("Enter surname: ");
	     String surname = sc.next();
	     System.out.println("Enter phone number: ");
	     double phoneNumber = sc.nextDouble();
		 user user1=new user(email, name, phoneNumber,surname);
		 treeplantationdrive20List.add(user1);
		 System.out.println("Person registered successfully!");
	 }
 }

}

public static  void  displayCleanup() {
	cleanupdrive11List.add(new user("anush.bomble@gmail.com","Anu",34567765,"Bobde"));
	cleanupdrive11List.add(new user("anu.bomble@gmail.com","Anushree",345678765,"Bomble"));
	Iterator list= cleanupdrive11List.iterator();
	System.out.println("Following are the names of registered people  for Cleanup Drive ");
	 while(list.hasNext())
	 {
		user n=(user) list.next();
		System.out.println(n.toString());
	 }
}

public String toString(String email, String name, double phoneNumber,String surname)
{
	
	    return "Email: " + email + ", Name: " + name + ", Phone Number: " + phoneNumber + ", Surname: " + surname;
	

}
public static  void displayTreeplatation() {
	treeplantationdrive20List.add(new user("anush.bomble@gmail.com","Anu",34567765,"Bobde"));
	treeplantationdrive20List.add(new user("anu.bomble@gmail.com","Anushree",345678765,"Bomble"));
	Iterator list= treeplantationdrive20List.iterator();
	System.out.println("Following are the names of registered people  for Tree Plantation Drive ");
	 while(list.hasNext())
	 {
		
		user n=(user) list.next();
		System.out.println(n.toString());
	 }
}

public static void dispadmin() {
	 pqJan.add(new event(11,"Cleanup-Drive","Bhusari Colony"));
	 pqJan.add(new event(20,"Tree Plantation Drive","ARAI "));
	 Scanner sc=new Scanner(System.in);
Iterator itr=pqJan.iterator();
System.out.println("Following are the events ");
while(itr.hasNext())
{
	 System.out.println(pqJan.poll().toString());
}
}
}

	    	   

	    

	

	
