package Buffer_Pharma;

import java.util.Scanner;

public class Main {

	static Customer cobj=new Customer(); 
    static Supplier sobj=new Supplier();
    static Admin aobj=new Admin();
    static Db mdb=new Db();
    static Scanner sc=new Scanner(System.in);
    
    
	public static void main(String[] args) {
		int ls;
		//inserting initial values
		cobj.Cacc_insert("C122","Anjali Shah","Pune","123",411038);
	    cobj.Cacc_insert("C123","Pranav Joshi","Pune","456",4110381);
	    cobj.Cacc_insert("C342","Janhavi Birla","Pune","789",411035);
	    cobj.Cacc_insert("C876","Ajay Patil","Pune","098",411052);
	    cobj.Cacc_insert("C577","Radha Sane","Pune","765",411001);
	    
		do{
            System.out.println("\n*************|||-- WELCOME --|||**************");
            System.out.println("1. Customer LogIn \n2. Supplier LogIn\n3. Admin LogIn\n4. Exit");
            ls=sc.nextInt();
            switch(ls){
               case 1:
                  cobj.Cmain();
               break;

               case 2:
                  sobj.Smain();
               break;
               
               case 3:
            	   aobj.Amain();
               break;   
            }
        }
        while(ls!=4);
		System.out.print("***************|||-- EXIT --|||****************");
	}

}
