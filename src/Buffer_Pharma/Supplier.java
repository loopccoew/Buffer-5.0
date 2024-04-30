package Buffer_Pharma;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;

class Supplier{
	static final String JDBC_Driver="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/Buffer?allowPublicKeyRetrieval=true&&useSSL=false";
	static final String User="root";
	static final String Pass="mysql@123";
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	String Query="";
	Scanner sc=new Scanner(System.in);
	Db meddb=new Db();
	
	
		
	//----------------------------------- Supplier Account CRUD ---------------------------
	void Sacc_insert() {
		System.out.println("Enter name: ");
		String name=sc.nextLine();
		System.out.println("Enter city: ");
		String city=sc.nextLine();
		System.out.println("Enter pincode: ");
		int pin=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter Shopname: ");
		String  shop=sc.nextLine();
       
		while(pin<100000) {
			System.out.println("Incorrect pincode...");
			System.out.println("Enter correct pincode: ");
			pin=sc.nextInt();
		}
		sc.nextLine();
		
		Random ran=new Random();
        int r=ran.nextInt(200);
    	try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			int flag=0;
			ResultSet rs=null;
			boolean Supplier=true;
			while(Supplier) {
				Query="select * from Supplier_info where S_ID=?";
				pstmt=conn.prepareStatement(Query);
				pstmt.setInt(1, r);
				 rs=pstmt.executeQuery();
				
				while(rs.next()) {
					flag=1;	
				}
				if(flag==0) {
					Supplier=false;
				}else {
					
					r=ran.nextInt(200);
					flag=0;
				}
			}
			int pass=0;
			
			if(flag==0) {
				System.out.println("Account generated successfully....");
				System.out.println("Your login ID is: "+r);
				
				System.out.println("Enter password: ");
				pass=sc.nextInt();
			}
			
			Query="insert into Supplier_info(S_ID,S_name,Shop_name,S_city,S_pincode,S_pass) values(?,?,?,?,?,?)";
			pstmt=conn.prepareStatement(Query);
			pstmt.setInt(1, r);
			pstmt.setString(2, name);
			pstmt.setString(3, shop);
			pstmt.setString(4, city);
			pstmt.setInt(5, pin);
			pstmt.setInt(6, pass);
			
			pstmt.executeUpdate();
			System.out.println("Success!!!!!!");
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
catch(SQLException se){
	se.printStackTrace();
		}
	}
	
	void Sacc_displayAll() {
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			Query= "select * from Supplier_info"; 
			pstmt=conn.prepareStatement(Query);
        	ResultSet rs=pstmt.executeQuery();
        	
        	System.out.println("SID\tOwner\t\tShop\t\t\tCity\tPincode");
        	while(rs.next()){
        		int sid = rs.getInt("S_ID");
                String sname = rs.getString("S_name");
                String shopname = rs.getString("Shop_name");
                String scity = rs.getString("S_city");
                String pin=rs.getString("S_pincode");
                System.out.println( sid + "\t"+ sname + "\t" + shopname + "\t" + scity+"\t"+pin);
        	}
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
      catch(SQLException se){
	     se.printStackTrace();
      }
	}

	void Sacc_update() {
		System.out.println("Enter SID: ");
		int sid=sc.nextInt();
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			Query= "select * from Supplier_info where S_ID=?";
			pstmt=conn.prepareStatement(Query);
			pstmt.setInt(1, sid);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("Enter city: ");
				String city=sc.next();
				System.out.println("Enter pincode: ");
				int pin=sc.nextInt();
				Query="update Supplier_info set S_city=?,S_pincode=? where S_ID=?";
				pstmt=conn.prepareStatement(Query);
				pstmt.setString(1, city);
				pstmt.setInt(2, pin);
				pstmt.setInt(3, sid);
				pstmt.executeUpdate();
			}
			else {
				System.out.print("Supplier ID not found");
			}
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
        catch(SQLException se){
	       se.printStackTrace();
		}
		
	}

	void Sacc_delete() {
		System.out.print("Enter SID of Supplier to be Deleted: ");
		int sdel=sc.nextInt();
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			
			try(
            		PreparedStatement psfk=conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
                    PreparedStatement psDel = conn.prepareStatement("delete from Supplier_info where S_ID = ? ");
                )

            {       
            	psfk.execute();
            	psDel.setInt(1, sdel);
            	//psDel.execute();
                if (psDel.executeUpdate() > 0)
                    System.out.println("Record deleted successfully.");
                else
                    System.out.println("Record not found.");
                
                psfk.close();
    			psDel.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
			conn.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
       catch(SQLException se){
	        se.printStackTrace();
	 	}
		
	}

	//----------------------------------- Supplier-Medicine CRUD --------------------------
	void Scurr_displayCurr(int sid) {
//		System.out.print("Enter SID: ");
//		int tsid=sc.nextInt();
		
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			Query= "select * from Supplier_info where S_ID=?";
			pstmt=conn.prepareStatement(Query);
			pstmt.setInt(1, sid);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				int tsid = rs.getInt("S_ID");
                String sname = rs.getString("S_name");
                String shopname = rs.getString("Shop_name");
                String scity = rs.getString("S_city");
                String pin=rs.getString("S_pincode");
                System.out.println("-----------------\nWelcome "+sname);
                System.out.println("-----Details-----\nSID\t"+tsid + "\nOwner\t "+ sname + "\nShop\t" + shopname + "\nCity\t" + scity+"\nPincode\t"+pin);
                
                System.out.println("\nMedicines:");
                String Query1= "select * from Medicine_info where S_ID=?";
    			PreparedStatement pstmt1=conn.prepareStatement(Query1);
    			pstmt1.setInt(1, tsid);
    			ResultSet rs1=pstmt1.executeQuery();
				System.out.println( "M_ID\tMed_name\tMed_type\tPrice\tQuantity\tNo of Tablets\tVolume\n---------------------------------------------------------------------------------------");	
    			while(rs1.next()) {
    				System.out.println( rs1.getInt("M_ID") + "\t"+ rs1.getString("Med_name") + "\t\t" + rs1.getString("Med_type") + "\t\t" + rs1.getBigDecimal("Price")+"\t"+rs1.getInt("Quantity")+"\t\t"+rs1.getInt("Num_Tablets")+"\t\t"+rs1.getInt("Volume"));	
    			}
    			rs1.close();
                
			}
			else {
				System.out.println("No supplier found with SID: " + sid);
			}	
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	   catch(SQLException se){
	        se.printStackTrace();
	 	}
	}
	
	void Scurr_addmed(int sid) {
		System.out.println("Enter M_ID: ");
		int mid=sc.nextInt(); 
		System.out.println("Enter Medicine Name: ");
		String med_name=sc.next();
		System.out.println("Enter Medicine type: ");
		String med_type=sc.next();
		System.out.println("Enter Medicine Quantity: ");
		int quan=sc.nextInt();
		System.out.println("Enter Medicine Price: ");
		int price=sc.nextInt();
		System.out.println("Enter No of Tablets: ");
		int nooftb=sc.nextInt();
		System.out.println("Enter Medicine Volume(for syrup med only): ");
		int vol=sc.nextInt();
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			String Query1= "insert into Medicine_info values (?,?,?,?,?,?,?,?)";
			PreparedStatement pstmt1=conn.prepareStatement(Query1);
			pstmt1.setInt(1, sid);
			pstmt1.setInt(2, mid);
			pstmt1.setString(3, med_name);
			pstmt1.setString(4, med_type);
			pstmt1.setInt(5, price);
			pstmt1.setInt(6, quan);
			pstmt1.setInt(7, nooftb);
			pstmt1.setInt(8, vol);
			pstmt1.executeUpdate();
			System.out.println("Added Successfully!");
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
	   catch(SQLException se){
	        se.printStackTrace();
	 	}	
	}

	void Scurr_delmed(int sid) {
		System.out.println("Enter Medicine Name: ");
		String med_name=sc.next();
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			
			try(
            		PreparedStatement psfk=conn.prepareStatement("SET FOREIGN_KEY_CHECKS=0;");
                    PreparedStatement psDel = conn.prepareStatement("delete from Medicine_info where S_ID = ? and Med_name =? ");
                )

            {       
            	psfk.execute();
            	psDel.setInt(1, sid);
            	psDel.setString(2, med_name);
            	//psDel.execute();
                if (psDel.executeUpdate() > 0)
                    System.out.println("Medicine deleted successfully.");
                else
                    System.out.println("Medicine not found.");
                
                psfk.close();
    			psDel.close();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
			conn.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
       catch(SQLException se){
	        se.printStackTrace();
	 	}
	}
	
	void Scurr_updatemed_quan(int sid) {
		System.out.println("Enter M_ID: ");
		int mid=sc.nextInt();
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			Query= "select * from Medicine_info where S_ID = ? and M_ID=? ";
			pstmt=conn.prepareStatement(Query);
			pstmt.setInt(1, sid);
			pstmt.setInt(2, mid);
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("Enter Quantity: ");
				int quan=sc.nextInt();
				Query="update Medicine_info set Quantity=? where S_ID=? and M_ID=?";
				pstmt=conn.prepareStatement(Query);
				pstmt.setInt(1, quan);
				pstmt.setInt(2, sid);
				pstmt.setInt(3, mid);
				pstmt.executeUpdate();
				System.out.println("Updated Successfully!");
			}
			else {
				System.out.println("Medicine not found");
			}
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
        catch(SQLException se){
	       se.printStackTrace();
		}
	}

	
	//----------------------------------- Supplier side Main -------------------------------
	int Sacc_login() {
		int sid=0;
		boolean li=false;
		System.out.println("Enter ID: ");
		int id=sc.nextInt();
		sc.nextLine();
		System.out.println("Enter password: ");
		int pass=sc.nextInt();
		
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			Query="select * from Supplier_info where S_ID=?";
			pstmt=conn.prepareStatement(Query);
			pstmt.setInt(1, id);
			
			ResultSet rs=pstmt.executeQuery();
			int flag=0;
			while(rs.next()) {
				flag=1;
			}
			
			if(flag==0) {
				System.out.println("User doesn't exists...");
			}
			else {
				Query="select S_pass from Supplier_info where S_ID=? ";
				pstmt=conn.prepareStatement(Query);
				pstmt.setInt(1, id);
				
				 rs=pstmt.executeQuery();
				 
				 while(rs.next()) {
					 if(rs.getInt("S_pass")==pass) {
						 System.out.println("Log in successful....");
						 sid=id;
						 li=true;		 
					 }
					 else {
						 System.out.println("Wrong password entered");
					 }
				 }
			}
			rs.close();
			pstmt.close();
			conn.close();
		}
		catch(ClassNotFoundException e){
			e.printStackTrace();
		}
catch(SQLException se){
	se.printStackTrace();
		}
		
		if(li)
			return sid;
		else
			return -1;
	}

	void Smain() {
	    Scanner s= new Scanner(System.in);
	    boolean login=false;
	    int ls,ch,sid=-1;
		do{
	           do{
	                System.out.println("=================== Supplier ===================");
	                System.out.println("1. Log In \n2. Sign Up\n3. Exit");
	                ls=s.nextInt();
	                switch(ls){
	                   case 1:
	                      sid=Sacc_login();
	                      login=true;
	                   break;
	    
	                   case 2:
	                	   Sacc_insert();
	                   break;
	                   
	                   case 3:
	                	   System.out.print("\nReturning to main page...\n");
	                   break;
	                }
	            }
	            while((!login && sid==-1));
	           
	            Customer c=new Customer();
		        if(sid!=-1) {
		        	Scurr_displayCurr(sid);
					do {
						System.out.println("\n-----Menu-----");
						System.out.println("1.Add Medicines\n2.Update Medicine Quantity\n3.Delete Medicine\n4.Display All Information\n5.Pending Deliveries\n6.Deliver Orders\n7.LogOut");
			            ch=sc.nextInt();
			            switch(ch){
			              case 1:
			            	  Scurr_addmed(sid);
			              break;

			              case 2:
			            	  Scurr_updatemed_quan(sid);
			              break;

			              case 3:
			            	  Scurr_delmed(sid);
			              break;
			              
			              case 4:
			            	  Scurr_displayCurr(sid);
			              break;
			              
			              case 5:
			            	  c.showq(sid);
			              break;
			              
			              case 6:
			            	  c.S_deliver(sid);
			              break;
			            }
			        }
			        while(ch!=7);
					System.out.println("\nLogging out...\n");
					sid=-1;
				}	
	       }
		   while(ls!=3);
	 }
		
}





