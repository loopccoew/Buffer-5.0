package code.netjava;
import java.util.*;

class prescription{
	
	String patientName;
	String doctorName;
	String addr;
	ArrayList<String> med;
	int patientID;
	long phone_no;
	prescription left;
	prescription right;

	prescription(String pname,String dname,String addr,int p_id,long no,int k,ArrayList<String> med){
		patientName=pname;
		doctorName = dname;
		this.addr=addr;
		patientID = p_id;
		phone_no = no;
		this.med = new ArrayList<>(med); 
		left = null;
		right = null;
	}
}

class Graph {
    Map<String, Map<String, Integer>> routes;

    Graph() {
        routes = new HashMap<>();
        // Initialize some default routes
        // These routes can be hardcoded or fetched from a database
        routes.put("Warehouse", new HashMap<>());
        routes.get("Warehouse").put("Kakade Society,Kothrud,Pune,411056", 10); // Example distance or time
        routes.get("Warehouse").put("Water's Edge,Pimple Nilakh,Pune 411065", 30);
        routes.get("Warehouse").put("DSK Akashganga,Aundh,Pune,411067",25);
        routes.get("Warehouse").put("Baya Karve Hostel,Karve Nagar,Pune 411078",20);
        routes.get("Warehouse").put("Colina Vista,Pashan,Pune,411087",25);
    }

    // Method to estimate distance based on the last 6 digits of the ZIP code
    private int estimateDistanceByZIP(String address) {
        // Extract the last part of the address as the ZIP code
        String[] parts = address.split(",");
        String zipPart = parts[parts.length - 1].trim();
        String last6Digits = zipPart.substring(Math.max(0, zipPart.length() - 6)); // Extract last 6 digits

        // Convert the last 6 digits to an integer for comparison
        int zipCode = Integer.parseInt(last6Digits);

        // Assign distances based on the range of the last 6 digits
        if (zipCode >= 411056 && zipCode <= 411065) {
            return 10; // Short distance
        } else if (zipCode >= 411066 && zipCode <= 411067) {
            return 30; // Medium distance
        } else if (zipCode >= 411068 && zipCode <= 411078) {
            return 25; // Medium distance
        } else if (zipCode >= 411079 && zipCode <= 411087) {
            return 20; // Long distance
        } else {
            return 40; // Default distance for unknown ZIP codes
        }
    }

    // Method to add a new address to the graph
    public void addAddress(String address) {
        routes.put(address, new HashMap<>());
        // Update distances/time for existing addresses based on new address
        // This can be done based on existing routes or calculated dynamically
        // For simplicity, let's assume the distance/time is calculated dynamically
        for (String existingAddress : routes.keySet()) {
            if (!existingAddress.equals(address)) {
                // Calculate and update distance/time between existingAddress and new address
                int distanceOrTime = estimateDistanceByZIP(address); // Use the estimateDistanceByZIP method
                if (distanceOrTime != -1) { // Check if distance is valid
                    routes.get(existingAddress).put(address, distanceOrTime);
                    routes.get(address).put(existingAddress, distanceOrTime); // Assuming symmetric routes
                } else {
                    System.out.println("Failed to estimate distance for address: " + address);
                }
            }
        }
    }
    public int shortestDistanceOrTimeToWarehouse(String address) {
        System.out.println("Trying to find distance for address: " + address); // Print the address being searched
        
        // Print the contents of the routes map
        System.out.println("Routes Map Contents:");
        for (Map.Entry<String, Map<String, Integer>> entry : routes.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
        System.out.println();        
        if (routes.containsKey(address)) {
            // Use Dijkstra's algorithm to find shortest distance from Warehouse to the given address
            Map<String, Integer> distances = new HashMap<>();
            Set<String> visited = new HashSet<>();
            PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

            // Initialize distances to infinity and add all addresses to the queue
            for (String key : routes.keySet()) {
                distances.put(key, Integer.MAX_VALUE);
                queue.offer(key);
            }
            distances.put("Warehouse", 0);

            // Dijkstra's algorithm
            while (!queue.isEmpty()) {
                String current = queue.poll();
                visited.add(current);
                for (Map.Entry<String, Integer> neighbor : routes.get(current).entrySet()) {
                    String next = neighbor.getKey();
                    int distance = neighbor.getValue();
                    if (!visited.contains(next)) {
                        int newDistance = distances.get(current) + distance;
                        if (newDistance < distances.get(next)) {
                            distances.put(next, newDistance);
                            queue.offer(next);
                        }
                    }
                }
            }

            Integer distanceToWarehouse = distances.get(address);
            if (distanceToWarehouse != null) {
                return distanceToWarehouse.intValue(); // Return the distance if it's not null
            } else {
                // If distance is null, address is not reachable
                System.out.println("Address is not reachable from the warehouse.");
                return -1; // or any other appropriate value to indicate unreachable
            }
        } else {
            // If address not found in the routes map, use estimateDistanceByZIP as fallback
            System.out.println("Address not found in the graph. Using estimateDistanceByZIP as fallback.");
            return estimateDistanceByZIP(address);
        }
    }
}

class methods {
	
	prescription root;
	Graph graph;

	methods(){
		root=null;
		graph=new Graph();
	}
	Scanner sc=new Scanner(System.in);
	int p_id=100;
	
	void insert_first() {
		p_id++;
		int n=0;

		System.out.println("Enter patient's name:");
		String pname=sc.next();
		while (!isValidName(pname)) {
	        System.out.println("Invalid name. It should contain only alphabetic characters.");
	        System.out.println("Please enter a valid name:");
	        pname = sc.next(); // Re-read the patient's name
	    }
		System.out.println("Enter doctor's name:");
		String dname=sc.next();
		while (!isValidName(dname)) {
	        System.out.println("Invalid name. It should contain only alphabetic characters.");
	        System.out.println("Please enter a valid name:");
	        dname = sc.next(); // Re-read the patient's name
	    }
		System.out.println("Enter patient's address:");
		 sc.nextLine();
		String addr=sc.nextLine();
		while (!isValidAddress(addr)) { // Check if it's too short or null
	        System.out.println("Invalid address. It must have at least 15 characters.");
	        System.out.println("Please enter a valid address:");
	        addr = sc.nextLine(); // Re-read if invalid
	    }
		System.out.println("Enter patient's phone number:");
		long no=sc.nextLong();
		while (!isValidPhoneNumber(no)) { // Check if it's invalid
		    System.out.println("Invalid phone number. It must be 10 digits long and start with 7, 8, or 9.");
		    System.out.println("Please enter a valid phone number:");
		   no = sc.nextLong(); // Re-read the phone number
		}
		System.out.println("Enter the number of medicines in your prescription:");
		n=sc.nextInt();
		sc.nextLine();
		ArrayList<String> med = new ArrayList<>();
		for (int i = 0; i < n; i++) {
		    System.out.println("Enter name of medicine number " + (i + 1) + ":");
		    String medName = sc.next();
		    med.add(medName); // Ensure it's correctly added to the list
		}

		prescription p=new prescription(pname,dname,addr,p_id,no,n,med);
		//insert into b-tree using p_id
		insert_tree(p);
		System.out.println("Your patientID id is: "+p_id); //showing this as customer will have a record of what his p_id is
	}

	public static boolean isValidPhoneNumber(long phoneNumber) {
	    // Convert to String
	    String phoneStr = Long.toString(phoneNumber);
	    // Ensure it's 10 digits long
	    if (phoneStr.length() != 10) {
	        return false;
	    }
	    // Ensure the first digit is 7, 8, or 9
	    char firstDigit = phoneStr.charAt(0);
	    if (firstDigit != '7' && firstDigit != '8' && firstDigit != '9') {
	        return false;
	    }
	    // Ensure all characters are digits
	    for (char c : phoneStr.toCharArray()) {
	        if (!Character.isDigit(c)) {
	            return false; // If any character is not a digit
	        }
	    }
	    return true; // Valid phone number
	}

	public static boolean isValidName(String name) {
	    return name.matches("^[a-zA-Z]+$"); // Only allows alphabetic characters
	}

	public static boolean isValidAddress(String address) {
	    // Ensure address is not null and has at least the specified minimum length
	    return address != null && address.length() >= 15;
	}

	prescription findByPatientID(prescription current, int patientID) {
        while (current != null) {
            if (patientID < current.patientID) {
                current = current.left;
            } 
            else if (patientID > current.patientID) {
                current = current.right;
            } 
            else {
                return current; // Found the matching node
            }
        }
        return null; // No matching node found
    }

	void update() {
		System.out.println("Enter patientID");
    	int patientID=sc.nextInt();

        // Find the node to update based on patientID
        prescription nodeToUpdate = findByPatientID(root, patientID);

        if (nodeToUpdate == null) {
            System.out.println("No record found with the given patientID.");
            return;
        }
        
        System.out.println("What would you like to update?");
        System.out.println("1. Phone number");
        System.out.println("2. Address");
        System.out.println("3. Add new medicines in your prescription");
        int choice = sc.nextInt();

        if (choice == 1) { // Update phone number
            System.out.println("Enter new phone number:");
            long newPhoneNumber = sc.nextLong();
            // Validate the phone number
            while (!isValidPhoneNumber(newPhoneNumber)) {
                System.out.println("Invalid phone number. It must be 10 digits and start with 7, 8, or 9.");
                System.out.println("Please enter a valid phone number:");
                newPhoneNumber = sc.nextLong(); // Re-enter if invalid
            }
            nodeToUpdate.phone_no = newPhoneNumber; // Update phone number if valid
            System.out.println("Phone number updated successfully.");
        }
        else if (choice == 2) { // Update address
            System.out.println("Enter new address: ");
            sc.nextLine(); // Consume newline character
            String newAddress = sc.nextLine();

            while (!isValidAddress(newAddress)) { // Check if it's too short or null
                System.out.println("Invalid address. It must have at least 10 characters.");
                System.out.println("Please enter a valid address:");
                newAddress= sc.nextLine(); // Re-read if invalid
            }
            nodeToUpdate.addr = newAddress; // Update address directly
            System.out.println("Address updated successfully.");
        } 
        else if (choice==3) {
        	System.out.println("How many medicines do you want to add?");
        	int n = sc.nextInt();
        	for(int i=0;i<n;i++) {
        		System.out.println("Enter name of medicine number "+(i+1)+":");
        		sc.nextLine();
        		String a = sc.next();
        		nodeToUpdate.med.add(a); //add directly in arraylist
        	}
        	System.out.println("Pescriptions added successfully.");
        }
        else {
            System.out.println("Invalid choice. Please try again.");
        }
	}
	
	void delete() {
		//delete 1 med from prescription
		System.out.println("Enter patientID");
    	int patientID=sc.nextInt();

        // Find the node to update based on patientID
        prescription nodeToUpdate = findByPatientID(root, patientID);

        if (nodeToUpdate == null) {
            System.out.println("No record found with the given patientID.");
            return;
        }
        System.out.println("Enter medicine name: ");
        sc.nextLine(); // Consume newline character
        String name = sc.nextLine();

        for(int i=0; i< nodeToUpdate.med.size(); i++) {
            if(name.equalsIgnoreCase(nodeToUpdate.med.get(i))) {
            	nodeToUpdate.med.remove(i);
            	System.out.println("Medicine deleted successfully.");
            	return;
            }
        }
        System.out.println("Medicine not found.");
	}
	
	private void insert_tree(prescription p) {
	    if (root == null) {
	        root = p; // If the tree is empty, set the new node as the root
	        return;
	    }

	    prescription current = root;

	    while (true) {
	        if (p.patientID < current.patientID) { // Go left if new node is smaller
	            if (current.left == null) {
	                current.left = p;
	                break;
	            } 
	            else {
	                current = current.left;
	            }
	        } 
	        else if (p.patientID > current.patientID) { // Go right if new node is larger
	            if (current.right == null) {
	                current.right = p;
	                break;
	            } 
	            else {
	                current = current.right;
	            }
	        } 
	        else {
	            System.out.println("Patient ID already exists");
	            break;
	        }
	    }
	}
	
	prescription call() {
		return root;
	}

	void display() {
	    System.out.println("Enter the patient ID of the prescription you want to view:");
	    int patientID = sc.nextInt(); // Read the patient ID from the user

	    // Find the node with the given patientID
	    prescription found = findByPatientID(root, patientID);

	    if (found == null) {
	        System.out.println("No prescription found with patient ID " + patientID);
	    } else {
	        // If found, display the details of the prescription
	        System.out.println("Patient ID: " + found.patientID);
	        System.out.println("Patient Name: " + found.patientName);
	        System.out.println("Doctor's Name: " + found.doctorName);
	        System.out.println("Phone Number: " + found.phone_no);
	        System.out.println("Address: " + found.addr);
	        System.out.println("Medications:");
	        if (found.med.isEmpty()) {
	            System.out.println("No medications recorded."); // Handle empty list
	        } else {
	            for (String medication : found.med) {
	                System.out.println("- " + medication); // Ensure correct loop
	            }
	        }
	    }
	}
	
	void displayDistanceToWarehouse() {
		if (graph == null) {
			System.out.println("Error: Graph object is not properly initialized.");
		return;
		}
		
		System.out.println("Enter the patient ID to calculate the distance to the warehouse:");
		int patientID = sc.nextInt();
		prescription found = findByPatientID(root, patientID);
		if (found == null) {
			System.out.println("No prescription found with patient ID " + patientID);
		} 
		else {
			String address = found.addr; // Get the address from the prescription
			int distanceToWarehouse = graph.shortestDistanceOrTimeToWarehouse(address);
			if (distanceToWarehouse != -1) {
				System.out.println("Time left for delivery:" + patientID + ": " + distanceToWarehouse + " mins");
				System.out.println();
			} // else: Do not print any message if distance cannot be calculated
		}
	}
}

public class Main {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		methods p=new methods();

		while(true) {
			System.out.println("Welcome to MED-WISE Express");
			System.out.println("Enter your choice: \n1. New customer info \n2. Update customer info \n3. Display \n4. Delete medicine"
					+ "\n5. Display time taken for delivery \n6. Exit");
			int choice = sc.nextInt();

			switch(choice) {

			case 1:
				p.insert_first();
				System.out.println();
				break;
				
			case 2:
				p.update();
				break;

			case 3:
				p.display();
				System.out.println();
				break;

			case 4:
				p.delete();
				break;
				
			case 5:
				System.out.println("Hey there!Your order is on it's way!!");
				p.displayDistanceToWarehouse();
				break;

			case 6:
				System.exit(0);
			}
		}	
	}
}
