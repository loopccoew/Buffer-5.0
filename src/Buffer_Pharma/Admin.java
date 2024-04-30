package Buffer_Pharma;

import java.util.*;
class Admin {
    static Customer cobj=new Customer(); 
    static Supplier sobj=new Supplier();
    static Db mdb=new Db();
    static Scanner sc=new Scanner(System.in);
    static String username="admin";
    static String password="admin123";
    
    void Amain() {
        boolean login=false;
        boolean logout=false;
        
        do {
        	System.out.println("=================== Admin ===================");
            while(!login) {
                System.out.println("Username: ");
                String user=sc.next();
                if(user.equals(username)) {
                	 System.out.println("Enter Password: ");
                     String pass=sc.next();
                     if(pass.equals(password)){
                        System.out.println("SignIn Successful!");
                        login=true;
                     }
                     else {
                    	 System.out.println("Password is Incorrect");
                     }
                }
                else {
                	System.out.println("Username does not exist");
                }
            }
            
            int ch,m;   
            do {
            	System.out.println("-----Menu-----");
            	System.out.println("1.Customer Side\n2.Supplier Side\n3.LogOut");
            	m=sc.nextInt();
            	switch(m) {
            	case 1:
            		do {
            			System.out.println("1.Display All Customers\n2.Add Customer\n3.Delete Customer\n4.Search Customer\n5.Back");
                        ch=sc.nextInt();
                        switch(ch){
                          case 1:
                           cobj.Cacc_displayall();;
                          break;

                          case 2:
                        	  cobj.Cacc_insert();
                          break;

                          case 3:
                           cobj.Cacc_delete();;
                          break;
                          
                          case 4:
                        	  System.out.print("Enter Cid");
                        	  String tcid=sc.next();
                        	  CustomerAcc c=cobj.Cacc_search(tcid);
                        	  cobj.Ccurr_display(c);
                          break;  
                        }	
                    }
                    while(ch!=5);
            	break;
            	
            	case 2:
            		do {
            			System.out.println("1.Display All Suppliers\n2.Add Supplier\n3.Delete Supplier\n4.Back");
                        ch=sc.nextInt();
                        switch(ch){
                          case 1:
                              sobj.Sacc_displayAll();
                          break;

                          case 2:
                        	  sobj.Sacc_insert();
                          break;

                          case 3:
                        	  sobj.Sacc_delete();
                          break;
                        }
                    	
                    }
                    while(ch!=4);
            	break;
            	
            	case 3:
            		System.out.println("\nLogging out...\n");
             	    System.out.print("\nReturning to main page...\n");
            		login=false;
            		logout=true;
            	break;
              }
            }
            while(login);
        }
        while(!logout);
    }
}

