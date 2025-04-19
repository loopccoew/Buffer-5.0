package tourismbooking;

import java.util.*;

// Assuming Booking is defined in the same package
public class Bill {
    private double total; // To store the total bill amount

    // Constructor to create a bill from a booking
    
    public Bill(Booking booking) {

        // Simple calculation based on the number of persons 
        this.total = booking.getNoOfPersons() * booking.selectedPackage.price;
        // Calculate the total bill
    }
    public void bday_anvys()
    {   Scanner sc= new Scanner(System.in);
        System.out.println("Does anybody in your group have birthday or anniversary during tour? (only one date can be used) , enter y for yes");
        char ch=sc.nextLine().charAt(0);
        
        
        if (ch=='y')
        {  System.out.println("CONGRATULATIONS!!! you get an additional 5% discount on your bill !");
            System.out.println("Enter birth date of said person, format = DD/MM/YYYY(It will be verified on spot during check in, a fine will be implied if false information provided)");
           String bdate= sc.nextLine();
           total = total - (0.05*total);
           System.out.println("You saved : Rs."+0.05*total);
        }
        else
        	System.out.println("Sorry. No discount.");
   
          
        System.out.println("Your bill (excluding taxes) comes out to be:" + total);
        
    }
    public void srctz()
    {  Scanner sc= new Scanner(System.in);
        System.out.println("Is any senior citizen , age greater than or equal to 75 in your group?, enter y for yes : ");
        char ch=sc.nextLine().charAt(0);
        
        
        if (ch=='y')
        {  System.out.println("CONGRATULATIONS!!! you get an additional 5% discount on your bill !");
            System.out.println("Enter age of said person(It will be verified on spot during check in, a fine will be implied if false information provided)");
           String bdate= sc.nextLine();
           total= total - (0.05*total);
           System.out.println("You saved Rs: "+0.05*total);
        }
        else
        	System.out.println("Sorry. No discount.");
        
        System.out.println("Your bill (excluding taxes) comes out to be:" +total);
        
    }
    public void couple(Booking booking)
    {   Scanner sc= new Scanner(System.in);
        if (booking.getNoOfPersons()==2)
        {  System.out.println("CONGRATULATIONS!!! you get an additional 10% discount on your bill !");
            total= total - (0.10*total);
            System.out.println("You saved : Rs."+0.10*total);
        }
        else
        	System.out.println("Sorry. No discount.");
        System.out.println("Your bill (excluding taxes) comes out to be:" +total);
       
    }
    public void group(Booking booking)
    {   
        if (booking.getNoOfPersons()>5)
        {  System.out.println("CONGRATULATIONS!!! you get an additional 7% discount per person on your bill !");
        
        total= total - (0.07*total);
        System.out.println("You saved : Rs."+0.07*total);
          
        }
        else
        	System.out.println("Sorry. No discount.");
        
   
        System.out.println("Your bill (excluding taxes) comes out to be:" +total);
        
        
        
    }
    public double getTotal() {
    	System.out.println("GST (18%) : "+0.18*total);
        return total + 0.18*total; // Return the total bill
    }

    @Override
    public String toString() {
        return "Total Bill: " + total;
    }
}
