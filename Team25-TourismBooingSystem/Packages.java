
package tourismbooking;

import java.util.*;
public class Packages {
	Scanner scanner=new Scanner(System.in);
	
    static Package[] allPackages; 
    public static class Package {
        String type;
        String days;
        int price;
        String facilities;

        public Package(String type, String days, int price, String facilities) {
            this.type = type;
            this.days = days;
            this.price = price;
            this.facilities = facilities;
        }

        @Override
        public String toString() {
            return   "Type:" + type+ "\n-Days:" + days+ "\n-Price per person:" +  price + "\n-Facilities:"+ facilities;
        }
    }

    public static void addPackages() {
        allPackages = new Package[6]; 

        
        allPackages[0] = new Package("Basic", "3 days 4 nights", 3000, "Basic accommodation, breakfast, dinner, transportation");
        allPackages[1] = new Package("Standard", "3 days 4 nights", 6000, "Mid-range accommodation, breakfast, dinner, extra activities, transportation");
        allPackages[2] = new Package("Premium", "3 days 4 nights", 12000, "Luxury accommodation, gourmet meals, spa, tour guide, transportation");
        allPackages[3] = new Package("Basic", "6 days 7 nights", 5000, "Basic accommodation, breakfast, dinner, transportation");
        allPackages[4] = new Package("Standard", "6 days 7 nights", 10000, "Mid-range accommodation, breakfast, dinner, extra activities,transportation");
        allPackages[5] = new Package("Premium", "6 days 7 nights", 20000, "Luxury accommodation, gourmet meals, spa, tour guide, transportation");
    }

    public static void displayPackages() {
    	Scanner scanner=new Scanner(System.in);
        if (allPackages == null) {
            System.out.println("No packages available.");
            return;
        }
        System.out.println("*************************************");
        System.out.println("\tAVAILABLE PACKAGES");
        System.out.println("*************************************");
        System.out.println("Select duration would you like to book : ");
        System.out.println("1= 3 days 4 nights\n2= 6 days 7 nights");
        int choice=scanner.nextInt();
        switch(choice) {
        
        
        case 1:{
        	
        	for (int j=0;j<3;j++) {
        		System.out.println("\n"+(j+1)+"."+allPackages[j]);
               
        	}
        	break;
        	
        }
        case 2: {
        	
        	for (int j=3;j<6;j++) {
        		System.out.println("\n"+(j+1)+"."+allPackages[j]);
                
        	}
        	break;
        }
        	
        }
        
    }
    
}
