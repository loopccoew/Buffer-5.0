package dsa;
import java.util.*;

public class DSA_project_27 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("****** CRIME RECORD MANAGEMENT SYSTEM******");
		System.out.println("\nSet password : ");
		String P = sc.nextLine();
		System.out.println("\nEnter the Number of Crimes ");
		int n = sc.nextInt();
		int ch;
		RecordCreation[] obj = new RecordCreation[n];
		for (int i = 0; i < n; i++) {
		int j = i + 1;
		System.out.println("\n--- Creating Crime " + j+" ---");
		obj[i] = new RecordCreation();
		obj[i].create();
		}
		do {
		System.out.println("\n\n\n==== MAIN MENU ===\n");
		System.out.println("1. View all Records");
		System.out.println("2. Search a Record");
		System.out.println("3. Edit a Record");
		System.out.println("4. Display Names of all Accused");
		System.out.println("5. Display Names of all Applicants along with CrimeID");
		System.out.println("6. Exit");
		ch = sc.nextInt();
		switch (ch) {
		case 1 : System.out.println("Are you a :");
		System.out.println("1. Citizen");
		System.out.println("2. Authorized Officer ?");
		int a = sc.nextInt();
		if (a == 1) {
		System.out.println("CRIME RECORDS :-");
		for (int i = 0; i < n; i++) {
		int j = i + 1;
		obj[i].display(j);
		}
		}

		else if (a == 2) {

		System.out.println("Enter Password :");
		sc.nextLine();
		String p = sc.nextLine();
		if (p.equals(P)) {
		System.out.println("\nUSER VERIFIED >>");
		System.out.println("CRIME RECORDS :-");
		for (int i = 0; i < n; i++) {
		int j = i + 1;
		obj[i].display(j, obj[i].getAccused());
		}
		}
		else {

		System.out.println("INCORRECT Password!!!");
		}
		}
		break;

		case 2 : System.out.println("Enter Password :");
		sc.nextLine();
		String p = sc.nextLine();
		if(p.equals(P)) {
		System.out.println("\nUSER VERIFIED >>");
		System.out.println("Enter the Crime ID : ");
		int searchID = sc.nextInt();
		boolean found = false;
		for (int i = 0; i < n; i++) {
		int j = i + 1;
		if (searchID == obj[i].CrimeID) {
		found = true;
		obj[i].search(searchID, j);
		break;
		}
		}

		if (!found) {

		System.out.println("Record not Found :(");
		}
		}
		else {

		System.out.println("INCORRECT Password !!!");
		}
		break;

		case 3 : System.out.println("Enter Password :");
		sc.nextLine();
		String pp = sc.nextLine();
		if(pp.equals(P)) {
		System.out.println("Enter Crime ID to edit : ");
		int id = sc.nextInt();
		boolean found = false;
		for (int i = 0; i < n; i++) {
		int j = i + 1;
		if(id == obj[i].CrimeID) {
		found = true;
		obj[i].edit(j);
		break;
		}
		}

		if(!found) {

		System.out.println("Record not Found :(");
		}
		}
		else {

		System.out.println("INCORRECT Password !!!");
		}
		break;
		case 4 : System.out.println("List of Accused : ");
		ArrayList<String>al = new ArrayList<String>();
		for(int i = 0; i < n; i++) {
		al.add(obj[i].accused);
		}

		System.out.println(al);
		break;
		case 5:
		 HashMap<Integer,String>map = new HashMap<>();
		 for(int i = 0; i < n; i++)
		 {
		 map.put(obj[i].CrimeID,obj[i].applicant);
		 }
		 System.out.println("The list of applicants along with the ID of crime filed: ");
		 System.out.println(map);
		break;
		case 6 : System.out.println("You have exited the program");
		System.out.println("<><><> THANK YOU <><><>");
		sc.close();
		break;
		default : System.out.println("Invalid choice! Please try again :(");
		}
		} while (ch != 6);
		sc.close();

	}

}
class RecordCreation {
	   // HashMap<Integer,String>map = new HashMap<>();
	String applicant, accused, location, description, CrimeDet, type;
	int date, month, year, hrs, mins , CrimeID;
	Scanner sc = new Scanner(System.in);
	void create() {
	System.out.println("Name of applicant :");
	applicant = sc.nextLine();
	System.out.println("Name of accused :");
	accused = sc.nextLine();
	System.out.println("Crime Type :");
	type = sc.nextLine();
	System.out.println("Crime Location :");
	location=sc.nextLine();
	System.out.println("Crime Description :");
	description = sc.nextLine();
	System.out.println("Bail Details :");
	CrimeDet = sc.nextLine();
	do {
	try {
	System.out.println("Date :");
	date = sc.nextInt();
	if(date < 1 || date > 31) {
	throw new IllegalArgumentException("Invalid date!");
	}

	break;
	}
	catch(NumberFormatException e) {
	System.out.println("Invalid input. Please enter a numeric value.");
	}
	catch (IllegalArgumentException e) {
	System.out.println(e.getMessage());
	}
	}while(true);
	do {
	try {
	System.out.println("Month :");
	month = sc.nextInt();
	if(month < 1 || month > 12) {
	throw new IllegalArgumentException("Invalid Month!");
	}
	break;
	}
	catch(NumberFormatException e) {
	System.out.println("Invalid input. Please enter a numeric value.");
	}
	catch (IllegalArgumentException e) {
	System.out.println(e.getMessage());
	}
	}while(true);
	do {
	try {
	System.out.println("Year :");
	year = sc.nextInt();
	if(year < 0 || year > 9999) {
	throw new IllegalArgumentException("Invalid Year!");
	}
	break;
	}
	catch(NumberFormatException e) {
	System.out.println("Invalid input. Please enter a numeric value.");
	}
	catch (IllegalArgumentException e) {
	System.out.println(e.getMessage());
	}
	}while(true);
	do {
	try {
	System.out.println("Time (24Hr Format) ");
	System.out.println("Hours : ");
	hrs = sc.nextInt();
	System.out.println("Minutes : ");
	mins = sc.nextInt();
	if(hrs < 0 || hrs > 23 || mins < 0 || mins > 59) {
	throw new IllegalArgumentException("Invalid time !");
	}
	break;

	}
	catch(NumberFormatException e) {
	System.out.println("Invalid input. Please enter a valid format.");
	}
	catch (IllegalArgumentException e) {
	System.out.println(e.getMessage());
	}
	}while(true);
	System.out.println("CRIME ID:");
	CrimeID = sc.nextInt();
	}
	void display(int j) {
	System.out.println("\n--- Crime " + j+" ---");
	System.out.println("Name of accused : " + accused);
	System.out.println("Crime Type : "+type);
	System.out.println("Location : " + location);
	System.out.println("Date : " + date + " / " + month + " / " + year);
	}
	void display(int j, String accused) {
	System.out.println("\n--- Crime " + j+" ---");
	System.out.println("CRIME ID : " + CrimeID);
	System.out.println("Name of accused: " + accused);
	System.out.println("Crime Type : "+type);
	System.out.println("Location : " + location);
	System.out.println("Date : " + date + "/" + month + "/" + year);
	System.out.println("Time : "+hrs+":"+mins);
	System.out.println("Crime Description : " + description);
	System.out.println("Bail Deatils : " + CrimeDet);
	}
	String getAccused() {
	return accused;
	}
	void search(int searchID, int j) {
	System.out.println("Record Found :)");
	System.out.println("Details of the CRIME ID "+searchID+" are : \n");
	this.display(j, accused);
	}
	/*void search(int searchID, int j, HashMap<Integer, HashMap<String, String>> RecordCreation) {
	    HashMap<String, String> crimeDetails =  RecordCreation.get(searchID);
	    if (crimeDetails != null) {
	        System.out.println("Record Found :)");
	        System.out.println("Details of the CRIME ID " + searchID + " are : \n");
	        display(j, crimeDetails);
	    } else {
	        System.out.println("No record found for CRIME ID " + searchID);
	    }
	}*/
	void edit(int j) {
	System.out.println("Select option to edit:");
	System.out.println("1. Name of Accused");
	System.out.println("2. Bail Details");
	int ch3 = sc.nextInt();
	sc.nextLine();
	if (ch3 == 1) {
	System.out.println("Enter updated name of accused : ");
	accused = sc.nextLine();
	}
	else if (ch3 == 2) {
	System.out.println("Enter updated Bail details : ");
	CrimeDet = sc.nextLine();

	}
	System.out.println("\nHere are the updated details : ");
	this.display(j, accused);
	}
	}

