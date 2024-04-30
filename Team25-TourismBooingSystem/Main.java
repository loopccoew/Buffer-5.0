package tourismbooking;

import java.util.Scanner;
import java.util.ArrayList;
public class Main {
	public static void clearScreen() 
	{
		for (int i = 0; i < 10; i++) { // Adjust the number for your preference
            System.out.println(); // Print blank lines
        }
	}
    public static void main(String[] args) {
    	long mobile;
    	int noOfPersons;
    	char gender;
    	Scanner scanner = new Scanner(System.in);
        System.out.println("****************************************");
        System.out.println("\tENTER USER INFORMATION : ");
        System.out.println("****************************************");
        
        do {
        System.out.println("Enter the number of persons : ");
        noOfPersons = scanner.nextInt(); 
        scanner.nextLine(); 
        if(noOfPersons<=0)
        	System.out.println("Invalid number of persons. Please try again:");
        }while(noOfPersons<=0);
        
        do {
        System.out.println("Enter your mobile number : ");
        mobile = scanner.nextLong();
        scanner.nextLine();
        if(String.valueOf(mobile).length() != 10)
        	System.out.println("Invalid Mobile number. Please enter again : ");
        }while(String.valueOf(mobile).length() != 10);

        System.out.println("Enter your email address:");
        String email = scanner.nextLine(); 

        
        Booking booking = new Booking(noOfPersons, mobile, email);

        
        for (int i = 0; i < noOfPersons; i++) {
        	System.out.println("-------------------------------------------");
            System.out.println("Enter details for person " + (i + 1) + ":");

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
             

            System.out.println("Aadhaar Number: ");
            String aadhaar = scanner.nextLine();
            
            System.out.print("Gender (m/f) :");
            do {
            gender = scanner.nextLine().charAt(0);
            if(gender!='m' && gender!='f')
            	System.out.println("Invalid selection. Please try again : ");
            }while(gender!='m' && gender!='f');
            
            
            
            Person person = new Person(firstName, lastName, age, aadhaar, gender);
            booking.addPerson(person); 
        }
        System.out.println("Press ENTER to continue....");
        scanner.nextLine();
        clearScreen();
     // Step 1: Add hill stations
        Places.addHillStations(); // Initialize the list of hill stations

        // Step 2: Display hill stations with their attractions
        ArrayList<Places.Place> hillStations = Places.getHillStations();
        
        System.out.println("****************************************");
        System.out.println("Available Hill Stations with Attractions:");
        System.out.println("****************************************");
        
        for (int i = 0; i < hillStations.size(); i++) {
            Places.Place place = hillStations.get(i);

            System.out.println((i + 1) + ". " + place.getDestination()); // Display destination name

            System.out.println("Attractions:");
            for (String attraction : place.getAttractions()) {
                if (attraction != null) { // Check for null values
                    System.out.println("- " + attraction); // Display each attraction
                }
            }

            System.out.println(); // Add a blank line for readability
        }

        // Step 3: User selects a hill station
        System.out.println("Select a hill station by entering its number:");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (choice < 1 || choice > hillStations.size()) { // Validate the choice
            System.out.println("Invalid selection. Please choose a valid number.");
        } else {
            // Step 4: Display selected hill station with its attractions
            Places.Place selectedPlace = hillStations.get(choice - 1); // Adjust for 1-based index
            System.out.println("----------------------------------------");
            System.out.println("\nYou selected : " + selectedPlace.getDestination()); 

            System.out.println("Attractions at " + selectedPlace.getDestination() + ":");
            for (String attraction : selectedPlace.getAttractions()) {
                if (attraction != null) {
                    System.out.println("- " + attraction); // Display attraction
                }
                
            }
            System.out.println("----------------------------------------");
        }
        System.out.println("Press ENTER to continue....");
        scanner.nextLine();
        clearScreen();

        Packages.addPackages();
        Packages.displayPackages();
        
        
        System.out.println("\nSelect the package number you want to book:");
        int packageChoice = scanner.nextInt();
        scanner.nextLine(); 
        
        if (packageChoice < 1 || packageChoice > 6) {
            System.out.println("Invalid package selection.");
        } else {
            Packages.Package selectedPackage = Packages.allPackages[packageChoice - 1];
            booking.addPackage(selectedPackage);
        }
       
        // Create a bill based on the booking
        Bill bill = new Bill(booking);
        
        System.out.println("Press ENTER to continue....");
        scanner.nextLine();
        clearScreen();
        
        System.out.println("****************************************");
        System.out.println("\tDISCOUNTS");
        System.out.println("****************************************"); 
        System.out.println("\nDiscounts available :\n 1. couples discount -> 10% \n 2.group discount -> 7% for groups with 5+ members \n 3.brithday/anniversary discount -> 5% \n 4.senior citizen discount -> 5% ");
        System.out.println("\nEnter option number if applicable(0 if not applicable) : ");
        int ch=scanner.nextInt();
        switch(ch)
        {  case(1):
            bill.couple(booking);
            break;
             
           case (2):
            bill.group(booking);
            break;
           case (3):
            bill.bday_anvys();
            break;
           case (4):
            bill.srctz();
            break;
           default:
            System.out.println("Oops, No discount. ");
         }
       
        System.out.println("----------------------------------------");
        System.out.println("\nTotal Bill Amount: Rs."+bill.getTotal());
        System.out.println("----------------------------------------");
        
        System.out.println("Press ENTER to continue....");
        scanner.nextLine();
        clearScreen();
        
        System.out.println("\n******************************************");
        System.out.println("Choose method of payment: (0 = card / 1 = UPI)");
        System.out.println("*********************************************");
        int paymentMethod = scanner.nextInt();

        if (paymentMethod == 1) { 
            System.out.println("Enter UPI ID:");
            String upi = scanner.next();

            System.out.println("Enter the 4-digit passcode:");
            int passcode = scanner.nextInt();

            System.out.println("Are you sure you want to proceed? (0 = yes / 1 = no)");
            int proceed = scanner.nextInt();

            if (proceed == 0) {
            	System.out.println("-------------------------------------");
                System.out.println("\tPAYMENT SUCCESSFUL");
                System.out.println("-------------------------------------");
                System.out.println("A confirmation with booking details will be emailed to you.");
                System.out.println("**Thank You**");
            } else {
                System.out.println("Payment Cancelled");
            }
        } else if (paymentMethod == 0) { 
            System.out.println("Enter card number:");
            long cardNumber = scanner.nextLong();

            System.out.println("Enter 4-digit OTP:");
            int otp = scanner.nextInt();

            System.out.println("Are you sure you want to proceed? (0 = yes / 1 = no)");
            int proceed = scanner.nextInt();

            if (proceed == 0) {
            	System.out.println("-------------------------------------");
                System.out.println("\tPAYMENT SUCCESSFUL");
                System.out.println("-------------------------------------");
                System.out.println("A confirmation with booking details will be emailed to you.");
                System.out.println("**Thank You**");
            } else {
                System.out.println("Payment Cancelled");
            }
        } else {
            System.out.println("Invalid payment method.");
        }

        scanner.close(); 
    }
}
    
