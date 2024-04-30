package Buffer_Pharma;

import java.util.*;
import java.text.SimpleDateFormat;

class CustomerAcc{
    String Cid;
    String C_Name;
    String C_phno;
     String C_pass;
    String C_City;
    int C_pincode;
    CustomerAcc next;
    CustomerAcc(){
        next=null;
    }
}

class Order{
    int M_id;
    int M_quantity;
    String date;
    Order next;
    Order(){
        this.M_quantity=0;
        this.next=null;
    }

}

class Customer{
    static Hashtable<String,CustomerAcc> C_ht1=new Hashtable<>();
    static Hashtable<CustomerAcc,Order> C_ht2=new Hashtable<>();
    //Order O_head=null;
    Scanner sc=new Scanner(System.in);
    Db db=new Db();
    Supplier s=new Supplier();
    
    //-------------------------------- Customer CRUD ----------------------------------
    void Cacc_insert(){
        int x=0;
        do{
            CustomerAcc Ctemp=new CustomerAcc();
            Random rand = new Random();
            int id = rand.nextInt(10000);
            Ctemp.Cid= "C"+id;
            while(C_ht1.containsKey(Ctemp.Cid)){
                int id1 = rand.nextInt(10000);
                Ctemp.Cid="C"+id1;
                System.out.println("-----");
            }
            System.out.println("Set Password: ");
            Ctemp.C_pass=sc.nextLine();
            System.out.println("Enter the name of Customer: ");
            Ctemp.C_Name=sc.nextLine();
            System.out.println("Enter the City of Customer: ");
            Ctemp.C_City=sc.nextLine();
            System.out.println("Enter Pincode: ");
            Ctemp.C_pincode=sc.nextInt();
            while(String.valueOf(Ctemp.C_pincode).length()!=6){
                System.out.println("Pincode is incorrect\t\tEnter correct pincode ");
                Ctemp.C_pincode=sc.nextInt();
            }
            Order Otemp = new Order();
            C_ht1.put(Ctemp.Cid,Ctemp);
            C_ht2.put(Ctemp,Otemp);
            //System.out.println(Ctemp.Cid);
            System.out.println("Account Created Successfully!! \nLoginID: "+Ctemp.Cid+"\t Password: "+Ctemp.C_pass);
            System.out.print("Add another Account? Enter y/n : 1/0");
            x=sc.nextInt();
            sc.nextLine();
        }
        while(x!=0);
    }

    void Cacc_insert(String cid,String name,String City,String pass,int pin){
        CustomerAcc Ctemp=new CustomerAcc();
        Ctemp.Cid=cid;
        Ctemp.C_pass=pass;
        Ctemp.C_Name=name;
        Ctemp.C_City=City;
        Ctemp.C_pincode=pin;
        Order Otemp = new Order();
        C_ht1.put(Ctemp.Cid,Ctemp);
        C_ht2.put(Ctemp,Otemp);
    }

    void Cacc_displayall(){
        CustomerAcc ctemp;
        Order otemp;
        for (String i : C_ht1.keySet()) {
            ctemp=C_ht1.get(i);
            if(ctemp!=null){
                System.out.println(i+ "\t"+ ctemp.C_Name+"\t"+ctemp.C_City);
                otemp=C_ht2.get(ctemp);
                int k=0;
                while(otemp!=null){
                  System.out.println(k+ "\t"+ otemp.M_id+"\t"+otemp.M_quantity);
                  otemp=otemp.next;
                   k++;
                }
                System.out.println("-----------------------------------");
            }
          }
    }
    
    void Cacc_update(String cid){
        CustomerAcc ct=Cacc_search(cid);
        if(ct!=null){
            System.out.println("Enter the City of Customer: ");
            ct.C_City=sc.next();
            System.out.println("Enter Pincode: ");
            ct.C_pincode=sc.nextInt();
            while(String.valueOf(ct.C_pincode).length()!=6){
                System.out.println("Pincode is incorrect\t\tEnter correct pincode ");
                ct.C_pincode=sc.nextInt();
            }  
            
            System.out.println("Updated Successfully!");
            System.out.println("-----Details-----\nCID\t"+ct.Cid+"\nName\t"+ ct.C_Name+"\nCity\t"+ct.C_City+"\nPincode\t"+ct.C_pincode);
        }
    }

    void Cacc_delete(){
        System.out.println("Enter Customer ID: ");
        String cidtemp=sc.next();
        CustomerAcc ctemp=Cacc_search(cidtemp);
        if(ctemp!=null){
            Order temp=C_ht2.get(ctemp);
            C_ht2.remove(temp);
            C_ht1.remove(cidtemp);
        }
    }
   
    CustomerAcc Cacc_search(String cid){
        CustomerAcc ctemp=C_ht1.get(cid);
        if(ctemp!=null){
            return ctemp;
        }
        else{
            System.out.println("Enter valid Customer ID");
            return null;
        }
    }
    
    //-------------------------------- Order Medicines --------------------------------
    int C_Order(CustomerAcc c){
        c=Cacc_search(c.Cid);
        int y=0;
        if(c!=null){
            Order O_head=C_ht2.get(c);
            int x;
            
            do{
               y= db.search();
              if(y!=0) {
               Order Onn=new Order();
               System.out.println("Enter ID of medicine: ");
               Onn.M_id=sc.nextInt();
               System.out.println("Enter Quantity of medicine: ");
               Onn.M_quantity=sc.nextInt();
               Onn.next=O_head;
               O_head=Onn;
               C_ht2.put(c,O_head);
               SimpleDateFormat ft  = new SimpleDateFormat("dd-MM-yyyy"); 
               String str = ft.format(new Date()); 
               Onn.date=str;
               System.out.println("Do you want to add one more Medicine? 1.YES         2. NO");
               x=sc.nextInt();
        }
              else{
            	  break;}
              }
        while(x!=2);
        }
        return y;
    }

    void Ccurr_display(CustomerAcc temp){
       // System.out.println(temp.Cid+"\t"+ temp.C_Name+"\t"+temp.C_City);
    	if(temp!=null) {
    	      Order otemp=C_ht2.get(temp);
    	        int k=1;
    	        System.out.println("Order Summary");
    	        
    	        System.out.println("\nSr No\tMID\tQuantity\tDate");
    	        while(otemp.next!=null){
    	            System.out.println(k+ "\t"+ otemp.M_id+"\t"+otemp.M_quantity+"\t"+otemp.date);
    	            otemp=otemp.next;
    	            k++;
    	        }
    	}
    }
    
//	void NearestSearch(CustomerAcc temp,String date) {
//	Order otemp=C_ht2.get(temp);
//	while(otemp.next!=null && otemp.date.equals(date)) {
//		int ID=otemp.M_id;
//		int quan=otemp.M_quantity;
//		int pincode=temp.C_pincode;
//		String shopname=db.check(ID,quan,pincode);
//		if(shopname.equals("null")) {
//			System.out.println("Insufficient quantity..");
//		}
//		System.out.println(shopname);
//		otemp=otemp.next;
//	  }
//    }
	
	void showq(int sid) {
		db.checkOrders(sid);
	}
	
    void bill(CustomerAcc c,String date) {
    	Order otemp=C_ht2.get(c);
    	double total = 0;
    	int k=1;
    	System.out.println("Sr No\tMID\tQuantity\tDate\tPrice\tSupplier"); 
    	while(otemp.next!=null && otemp.date.equals(date)) {
    		String Sname=db.check(otemp.M_id,otemp.M_quantity,c.C_pincode);
    		if(Sname.equals("null")) {
    			System.out.println("\n\n\bInsufficient quantity..");
    			break;
    		}
    		double price=db.FindPrice(otemp.M_id);
            System.out.println(k+"\t"+ otemp.M_id+"\t"+otemp.M_quantity+"\t"+otemp.date+"\t"+(otemp.M_quantity*price)+"\t"+Sname); 
            total=total+otemp.M_quantity*price;
            k++;
            db.Updatemed(Sname,otemp.M_id,otemp.M_quantity);
            otemp=otemp.next;
        }
    	System.out.print("-----------------------------------------------\nTotal\t"+total);
    	System.out.println();
    }
    
    void S_deliver(int sid) {
    	db.Dequeue(sid);
    }

    //---------------------------- Customer Main method -------------------------------
    CustomerAcc C_login(){
        System.out.println("Enter Customer ID: ");
        String cidtemp=sc.next();
        CustomerAcc ctemp=Cacc_search(cidtemp);
        if(ctemp!=null){
            System.out.println("Enter Password: ");
            String pass=sc.next();
            if(pass.equals(ctemp.C_pass)){
               System.out.println("SignIn Successful!");
               return ctemp;
            }
            else{
               System.out.println("Password is Incorrect");
               return null;
            }
        }
        System.out.println("LogIn ID\tNOT FOUND");
        return null;
    }
    
    void C_SignUp(){
        CustomerAcc Ctemp=new CustomerAcc();
        Random rand = new Random();
        int id = rand.nextInt(10000);
        Ctemp.Cid= "C"+id;
        while(C_ht1.containsKey(Ctemp.Cid)){
            int id1 = rand.nextInt(10000);
            Ctemp.Cid="C"+id1;
            System.out.println("-----");
        }
        System.out.println("Set Password: ");
        Ctemp.C_pass=sc.next();
        System.out.println("Enter the name of Customer: ");
        Ctemp.C_Name=sc.next();
        System.out.println("Enter the City of Customer: ");
        Ctemp.C_City=sc.next();
        System.out.println("Enter Pincode: ");
        Ctemp.C_pincode=sc.nextInt();
        while(String.valueOf(Ctemp.C_pincode).length()!=6){
            System.out.println("Pincode is incorrect\t\tEnter correct pincode ");
            Ctemp.C_pincode=sc.nextInt();
        }
        Order Otemp = new Order();
        C_ht1.put(Ctemp.Cid,Ctemp);
        C_ht2.put(Ctemp,Otemp);
        System.out.println(Ctemp.Cid);
        System.out.println("Account Created Successfully!! \nLoginID: "+Ctemp.Cid+"\t Password: "+Ctemp.C_pass);
    }

    void Cmain() {
    	 Scanner s= new Scanner(System.in);
	        Customer obj=new Customer(); 
	        CustomerAcc currAcc=null;
	        boolean login=false;
	        int ls,ch;

	       
	        
	        
	        do{
	            do{
	                System.out.println("=================== Customer ===================");
	                System.out.println("1. Log In \n2. Sign Up\n3. Exit");
	                ls=s.nextInt();
	                switch(ls){
	                   case 1:
	                      currAcc=obj.C_login();
	                      login=true;
	                   break;
	    
	                   case 2:
	                     obj.C_SignUp();
	                   break;
	                   
	                   case 3:
	                	   System.out.print("\nReturning to main page...\n");
	                   break;
	                }
	            }
	            while((!login && currAcc==null));

	            if(currAcc!=null){
	                System.out.println("-----------------\nWelcome "+currAcc.C_Name);
	                System.out.println("-----Details-----\nCID\t"+currAcc.Cid+"\nName\t"+ currAcc.C_Name+"\nCity\t"+currAcc.C_City+"\nPincode\t"+currAcc.C_pincode);
	                do{
	                    System.out.println();
	                    System.out.println("-----Menu-----");
	                    System.out.println("1.Order Med\n2.Recent Orders\n3.Update details\n4.LogOut");
	                    ch=s.nextInt();
	                    switch(ch){
	                      case 1:
	                       int y=obj.C_Order(currAcc);
	                       //obj.Ccurr_display(currAcc);
	                       if(y!=0) {
	                       System.out.println("***Order Summary***");
	                       SimpleDateFormat ft  = new SimpleDateFormat("dd-MM-yyyy"); 
	                       String datestr = ft.format(new Date());
	                       
	                       
	                    	   obj.bill(currAcc,datestr);
	                       }else {
	                    	   System.out.println("Medicine not available..");
	                       }
	                       
	                      break;

	                      case 2:
	                    	  obj.Ccurr_display(currAcc);
	                      break;
	    
	                      case 3:
	                       obj.Cacc_update(currAcc.Cid);
	                      break;
	                    }
	                }
	                while(ch!=4);
	                System.out.println("\nLogging out...\n");
	                currAcc=null;
	            }
	        }
	        while(ls!=3);
    }
}

