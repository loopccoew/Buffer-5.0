package Buffer_Pharma;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.lang.*;

class Db{
	static final String JDBC_Driver="com.mysql.cj.jdbc.Driver";
	static final String DB_URL="jdbc:mysql://localhost:3306/Buffer?allowPublicKeyRetrieval=true&&useSSL=false";
	static final String User="root";
	static final String Pass="mysql@123";
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	String Query="";
	static Scanner sc=new Scanner(System.in);
	static Hashtable <Integer,Queue> ht=new  Hashtable<>();
	static Db meddb=new Db();
	

	int search() {
		System.out.println("Enter the name of medicine: ");
		String med=sc.nextLine();
		int flag=0;
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			Query="Select distinct M_ID,Med_name,Price,Med_type,Case when Med_Type=? then Num_Tablets else Volume end as Quantity from Medicine_info"+" where  LOCATE(?,Med_name)!=0";
			pstmt=conn.prepareStatement(Query);
			pstmt.setString(1, "Tablet");
			pstmt.setString(2, med);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				flag=1;
				int id=rs.getInt("M_ID");
				String name=rs.getString("Med_name");
				int cost=rs.getInt("Price");
				String type=rs.getString("Med_type");
				int quantity=rs.getInt("Quantity");
				String ans="";
				if(type.equals("Tablet")) {
					ans="Strip of "+quantity+" tablets";
				}
				else {
					ans="Bottle of "+quantity+" ml";
				}
				System.out.println(id+")"+"Medicine name: "+ name+" "+"Price: "+cost+" "+ans);
				
				
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
		return flag;
	}
	
	void med_displayAll() {
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			Query= "select * from Medicine_info"; 
			pstmt=conn.prepareStatement(Query);
        	ResultSet rs=pstmt.executeQuery();
        	
        	System.out.println("MID\tProvided by(SID)\t\tName\t\t\tCity\tPincode");
        	while(rs.next()){
        		int sid = rs.getInt("S_ID");
        		int mid = rs.getInt("M_ID");
                String mname = rs.getString("Med_name");
                String medtype = rs.getString("Med_type");
                int price = rs.getInt("Price");
                int Quantity=rs.getInt("Quantity");
                int nooftab=rs.getInt("Num_Tablets");
                int Volume=rs.getInt("Volume");
                System.out.println( sid + "\t"+ mid + "\t" + mname + "\t" + medtype+"\t"+price+"\t"+Quantity+"\t"+nooftab+"\t"+Volume);
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
	
	String check(int id,int q,int pin) {
		int ID=0;
		String name="";
		int flag=0;
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			Query="Select Supplier_info.S_ID,Supplier_info.Shop_name from Supplier_info inner join Medicine_info on Supplier_info.S_ID=Medicine_info.S_ID where Medicine_info.M_ID=? and Medicine_info.Quantity>=? Order by ABS(Supplier_info.S_pincode-?),Medicine_info.Quantity Desc limit 1 ;";
			pstmt=conn.prepareStatement(Query);
			pstmt.setInt(1, id);
			pstmt.setInt(2, q);
			pstmt.setInt(3, pin);
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				flag=1;
				ID=rs.getInt("S_ID");
				name=rs.getString("Shop_name");
			}
			if(flag==1) {
			orders(ID,id,q,pin,name);
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
		if(flag==0) {
			return "null";
		}
		return name;
	}

	double FindPrice(int id) {
		double price=0;
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			Query="Select Price from Medicine_info where M_ID=? Order by Price limit 1;";
			pstmt=conn.prepareStatement(Query);
			pstmt.setInt(1, id);
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				
				price=rs.getInt("Price");
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
		return price;
	}
//static
	void orders(int SID,int mid,int q,int pin,String name) {
		S_Order obj=new S_Order(mid,q,pin,name);
		Queue<S_Order> queue=new LinkedList<S_Order>();		
		if(!ht.containsKey(SID)) {			
			queue.add(obj);
			ht.put(SID,queue);			
		}
		else {
			Queue<S_Order> a=ht.get(SID);
			a.add(obj);
			ht.put(SID, a);
		}		
//		Queue<S_Order> b=new LinkedList<S_Order>();
//		for (int i : ht.keySet()) {
//			 b=ht.get(i);
//			
//			 for(S_Order item : b){
//				    System.out.println(i+" "+item.MID+" "+item.name);
//				}
//			
//			}		
	}
	//static	
	void checkOrders(int ID) {
		System.out.println("MID\t Quantity");
		Queue<S_Order> a=new LinkedList<S_Order>();
		try {
		for (int i : ht.keySet()) {
			 a=ht.get(i);
			
			 for(S_Order item : a){
				    System.out.println(item.MID+" "+item.quantity);
				}
			
			}
		}
		catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println("No medicines to be delivered....");
		}
	}

    void Updatemed(String shopname,int MID,int q) {		
		try{
			Class.forName(JDBC_Driver);
			conn=DriverManager.getConnection(DB_URL,User,Pass);
			Query="update Medicine_info inner join Supplier_info on Supplier_info.S_ID=Medicine_info.S_ID set Medicine_info.Quantity=Medicine_info.Quantity-? where Supplier_info.Shop_name=? and Medicine_info.M_ID=?";
			pstmt=conn.prepareStatement(Query);
			pstmt.setInt(1, q);
			pstmt.setString(2, shopname);
			pstmt.setInt(3, MID);
		    pstmt.executeUpdate();
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

   static void Dequeue(int sID) {
		System.out.println("Have you delivered the order??(Yes/No)");
		String ans=sc.nextLine();
		if(ans.equals("Yes")) {
			Queue<S_Order> a=new LinkedList<S_Order>();	
			 a=ht.get(sID);		
			 a.remove();
			 ht.put(sID,a);
		}	
	}

//	public static void main(String[] args) {
//		orders(103,202,1, 411038, "Ondem");
//		checkOrders(103);
//	}
}

class S_Order{
	int MID;
	int quantity;
	int pincode;
	String name;
	S_Order(int x,int y,int z,String a){
		MID=x;
		quantity=y;
		pincode=z;
		name= a;
	}
}