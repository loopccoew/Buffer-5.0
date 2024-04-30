import java.util.*;

class Train {
    String trainNumber;
    String trainName;
    String source;
    String destination;
    int seatsAvailable;
    Map<String, Integer> distanceMap; // Stores distance to each station

    public Train(String trainNumber, String trainName, String source, String destination, int seatsAvailable) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.seatsAvailable = seatsAvailable;
        this.distanceMap = new HashMap<>();
    }

    public void addDistance(String station, int distance) {
        distanceMap.put(station, distance);
    }

    public int getDistance(String station) {
        return distanceMap.getOrDefault(station, -1);
    }
}

class User {
    String username;
    String password;
    List<String> bookings;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.bookings = new ArrayList<>();
    }
}

public class gorail {
    static Map<String, User> users = new HashMap<>();
    static List<Train> trains = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static User currentUser;
    static List<String> bookedTickets = new ArrayList<>();
    static List<String> feedbackAndComplaints = new ArrayList<>();


    public static void main(String[] args) {
        // Dummy data for demonstration
        trains.add(new Train("1234", "Express", "A", "B", 50));
        trains.add(new Train("5678", "Superfast", "B", "C", 100));

        // Add distances for demonstration
        trains.get(0).addDistance("A", 0);
        trains.get(0).addDistance("B", 100);
        trains.get(1).addDistance("B", 0);
        trains.get(1).addDistance("C", 150);

        // Main menu
        boolean exit = false;
        while (!exit) {
            System.out.println("\n1. Admin Login\n2. Admin Create Account\n3. User Login\n4. User Create Account\n5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    adminCreateAccount();
                    break;
                case 3:
                    userLogin();
                    break;
                case 4:
                    userCreateAccount();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

   
    // Admin login
public static void adminLogin() {
    System.out.println("Admin Login");
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    // Check if username exists and credentials match
    if (users.containsKey(username) && users.get(username).password.equals(password)) {
        System.out.println("Admin login successful.");
        // Call admin menu
        adminMenu();
    } else {
        System.out.println("Invalid username or password. Admin login failed.");
    }
}


  
// Admin menu
public static void adminMenu() {
    boolean exit = false;
    while (!exit) {
        System.out.println("\n1. Add Train\n2. View All Trains\n3. Update Availability of Seats\n4. View Feedback and Complaints\n5. Logout");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                addTrain();
                break;
            case 2:
                viewAllTrains();
                break;
            case 3:
                updateSeatAvailability();
                break;
            case 4:
                viewFeedbackAndComplaints();
                break;
            case 5:
                currentUser = null;
                exit = true;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}



    // Admin create account
public static void adminCreateAccount() {
    System.out.println("Admin Create Account");
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    // Check if username already exists
    if (users.containsKey(username)) {
        System.out.println("Username already exists. Please choose another one.");
        return;
    }
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    // Add admin to users
    users.put(username, new User(username, password));
    System.out.println("Admin account created successfully.");
}


    // Add train
    public static void addTrain() {
        System.out.println("Add Train");
        System.out.print("Enter train number: ");
        String trainNumber = scanner.nextLine();
        System.out.print("Enter train name: ");
        String trainName = scanner.nextLine();
        System.out.print("Enter source: ");
        String source = scanner.nextLine();
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter seats available: ");
        int seatsAvailable = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Train newTrain = new Train(trainNumber, trainName, source, destination, seatsAvailable);
        // Add distances
        System.out.println("Add distances to stations:");
        for (Train train : trains) {
            System.out.print("Distance from " + train.source + " to " + train.destination + ": ");
            int distance = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            newTrain.addDistance(train.destination, distance);
        }

        trains.add(newTrain);
        System.out.println("Train added successfully.");
    }

    // View all trains
    public static void viewAllTrains() {
        System.out.println("All Trains:");
        for (Train train : trains) {
            System.out.println("Train Number: " + train.trainNumber + ", Train Name: " + train.trainName +
                    ", Source: " + train.source + ", Destination: " + train.destination +
                    ", Seats Available: " + train.seatsAvailable);
        }
    }

    // User login
    public static void userLogin() {
        System.out.println("User Login");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Check if user exists and credentials match
        if (users.containsKey(username) && users.get(username).password.equals(password)) {
            System.out.println("Login successful.");
            currentUser = users.get(username);
            // Call user menu
            userMenu();
        } else {
            System.out.println("Invalid username or password. Login failed.");
        }
    }

    // User menu
    public static void userMenu() {
    boolean exit = false;
    while (!exit) {
        System.out.println("\n1. Search Train\n2. Show My Bookings\n3. Train Kitchen\n4. My Journey\n5. Customer Helpline and Feedback\n6. Logout");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        switch (choice) {
            case 1:
                searchTrain();
                break;
            case 2:
                showBookings();
                break;
            case 3:
                trainKitchen();
                break;
            case 4:
                myJourney();
                break;
            case 5:
                customerHelplineAndFeedback();
                break;
            case 6:
                currentUser = null;
                exit = true;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

    // User create account
    public static void userCreateAccount() {
        System.out.println("User Create Account");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        // Check if username already exists
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please choose another one.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        // Add user to users
        users.put(username, new User(username, password));
        System.out.println("User account created successfully.");
    }

    // Search train with booking option
    public static void searchTrain() {
    System.out.print("Enter source: ");
    String source = scanner.nextLine();
    System.out.print("Enter destination: ");
    String destination = scanner.nextLine();

    // Initialize distance map and predecessor map
    Map<String, Integer> distanceMap = new HashMap<>();
    Map<String, String> predecessorMap = new HashMap<>();
    for (Train train : trains) {
        distanceMap.put(train.source, Integer.MAX_VALUE);
        distanceMap.put(train.destination, Integer.MAX_VALUE);
        predecessorMap.put(train.source, null);
        predecessorMap.put(train.destination, null);
    }
    distanceMap.put(source, 0);

    // Run Dijkstra's algorithm
    Set<String> visited = new HashSet<>();
    while (!visited.contains(destination)) {
        String currentStation = null;
        int minDistance = Integer.MAX_VALUE;
        for (Map.Entry<String, Integer> entry : distanceMap.entrySet()) {
            String station = entry.getKey();
            int distance = entry.getValue();
            if (!visited.contains(station) && distance < minDistance) {
                minDistance = distance;
                currentStation = station;
            }
        }
        if (currentStation == null) {
            System.out.println("No path found from " + source + " to " + destination);
            return;
        }
        visited.add(currentStation);

        for (Train train : trains) {
            if (train.source.equals(currentStation)) {
                int alt = distanceMap.get(currentStation) + train.getDistance(destination);
                if (alt < distanceMap.get(destination)) {
                    distanceMap.put(destination, alt);
                    predecessorMap.put(destination, currentStation);
                }
            }
        }
    }

    // Display distance
    System.out.println("Distance from " + source + " to " + destination + ": " + distanceMap.get(destination));

    // Display available seats for each train
    System.out.println("Available Trains:");
    for (Train train : trains) {
        System.out.println("Train Number: " + train.trainNumber + ", Train Name: " + train.trainName +
                ", Source: " + train.source + ", Destination: " + train.destination +
                ", Seats Available: " + train.seatsAvailable);
    }

    // Book now option
    System.out.print("Do you want to book tickets for any of these trains? (yes/no): ");
    String bookOption = scanner.nextLine();
    if (bookOption.equalsIgnoreCase("yes")) {
        System.out.print("Enter number of tickets: ");
        int numberOfTickets = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter berth type (e.g., lower/upper): ");
        String berthType = scanner.nextLine();

        // Display food menu
        System.out.println("Food Menu:");
        for (String foodItem : getFoodMenu()) {
            System.out.println(foodItem);
        }

        // Select food items
        System.out.print("Enter food items (comma-separated): ");
        String foodInput = scanner.nextLine();
        String[] foodItems = foodInput.split(",");
        List<String> selectedFoodItems = Arrays.asList(foodItems);

        // Calculate total price
        double totalPrice = calculateTotalPrice(numberOfTickets, selectedFoodItems);

        // Payment gateway option
        System.out.println("Total Price: $" + totalPrice);
        System.out.println("Proceed to payment gateway? (yes/no): ");
        String proceedToPayment = scanner.nextLine();
        if (proceedToPayment.equalsIgnoreCase("yes")) {
            // Book ticket
            bookedTickets.add("Train Number: " + trains.get(0).trainNumber + ", Tickets: " + numberOfTickets +
                    ", Berth Type: " + berthType + ", Food Items: " + selectedFoodItems + ", Total Price: $" + totalPrice);
            System.out.println("Ticket booked successfully!");
        } else {
            System.out.println("Booking canceled.");
        }
    }
}


    // Show user's bookings
    public static void showBookings() {
        System.out.println("My Bookings:");
        for (String booking : bookedTickets) {
            System.out.println(booking);
        }
    }

    // Train kitchen (Not implemented)
   public static void trainKitchen() {
    System.out.println("Train Kitchen");

    // Dummy food menu for demonstration
    Map<String, Double> menu = new HashMap<>();
    menu.put("Sandwich", 8.0);
    menu.put("Burger", 7.5);
    menu.put("Pizza", 9.0);

    // Create a priority queue to store menu items based on their ratings
    PriorityQueue<Map.Entry<String, Double>> pq = new PriorityQueue<>(
            (a, b) -> Double.compare(b.getValue(), a.getValue())
    );

    // Add all menu items to the priority queue
    for (Map.Entry<String, Double> entry : menu.entrySet()) {
        pq.offer(entry);
    }

    // Display high-rated items first
    System.out.println("High-Rated Menu Items:");
    while (!pq.isEmpty()) {
        Map.Entry<String, Double> entry = pq.poll();
        System.out.println(entry.getKey() + " - $" + entry.getValue());
    }

    // Display cost of each food item
    System.out.println("\nCost of Each Food Item:");
    for (Map.Entry<String, Double> entry : menu.entrySet()) {
        System.out.println(entry.getKey() + ": $" + entry.getValue());
    }
}
   
    // Functionality for ordering food from the train kitchen can be implemented here.


    // My Journey
    public static void myJourney() {
        System.out.println("My Journey:");

        // if (currentUser == null) {
        ////   System.out.println("Please log in first.");
        // return;
        // }

        // Assuming the user has a booked ticket
        if (bookedTickets.isEmpty()) {
            System.out.println("No booked tickets found.");
            return;
        }

        // Assuming the user's journey is with the first booked ticket
        String bookedTicket = bookedTickets.get(0);
        System.out.println("Current Journey: " + bookedTicket);

        // Extracting source and destination from the booked ticket
        String[] parts = bookedTicket.split(",");
        String source = parts[0].substring(parts[0].indexOf(":") + 1).trim();
        String destination = parts[1].substring(parts[1].indexOf(":") + 1).trim();

        // Initialize distance map and predecessor map
        Map<String, Integer> distanceMap = new HashMap<>();
        Map<String, String> predecessorMap = new HashMap<>();
        for (Train train : trains) {
            distanceMap.put(train.source, Integer.MAX_VALUE);
            distanceMap.put(train.destination, Integer.MAX_VALUE);
            predecessorMap.put(train.source, null);
            predecessorMap.put(train.destination, null);
        }
        distanceMap.put(source, 0);

        // Run Dijkstra's algorithm
        Set<String> visited = new HashSet<>();
        while (!visited.contains(destination)) {
            String currentStation = null;
            int minDistance = Integer.MAX_VALUE;
            for (Map.Entry<String, Integer> entry : distanceMap.entrySet()) {
                String station = entry.getKey();
                int distance = entry.getValue();
                if (!visited.contains(station) && distance < minDistance) {
                    minDistance = distance;
                    currentStation = station;
                }
            }
            if (currentStation == null) {
                System.out.println("No path found from " + source + " to " + destination);
                return;
            }
            visited.add(currentStation);

            for (Train train : trains) {
                if (train.source.equals(currentStation)) {
                    int alt = distanceMap.get(currentStation) + train.getDistance(destination);
                    if (alt < distanceMap.get(destination)) {
                        distanceMap.put(destination, alt);
                        predecessorMap.put(destination, currentStation);
                    }
                }
            }
        }

        // Display current location and remaining distance
        System.out.println("Current Location: " + predecessorMap.get(destination));
        System.out.println("Remaining Distance to Destination: " + distanceMap.get(destination));
    }

    // Dummy method to get food menu
    public static List<String> getFoodMenu() {
        // Dummy food menu for demonstration
        List<String> foodMenu = new ArrayList<>();
        foodMenu.add("Sandwich");
        foodMenu.add("Burger");
        foodMenu.add("Pizza");
        return foodMenu;
    }

    // Dummy method to calculate total price
    public static double calculateTotalPrice(int numberOfTickets, List<String> selectedFoodItems) {
        // Dummy calculation for demonstration
        double baseTicketPrice = 50; // Assuming base ticket price
        double foodPrice = selectedFoodItems.size() * 5; // Assuming $5 per food item
        return numberOfTickets * baseTicketPrice + foodPrice;
    }
   
    // Update availability of seats
public static void updateSeatAvailability() {
    System.out.println("Update Availability of Seats");

    // Display list of trains
    System.out.println("List of Trains:");
    for (int i = 0; i < trains.size(); i++) {
        System.out.println((i + 1) + ". " + trains.get(i).trainNumber + ": " + trains.get(i).trainName);
    }

    // Select train
    System.out.print("Select a train to update availability (enter train number): ");
    int trainIndex = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    if (trainIndex < 1 || trainIndex > trains.size()) {
        System.out.println("Invalid train number.");
        return;
    }
    Train selectedTrain = trains.get(trainIndex - 1);

    // Update availability
    System.out.print("Enter new availability of seats: ");
    int newAvailability = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    selectedTrain.seatsAvailable = newAvailability;

    System.out.println("Availability of seats for train " + selectedTrain.trainNumber + " updated successfully.");
}

public static void customerHelplineAndFeedback() {
    System.out.println("Customer Helpline and Feedback");
    System.out.println("1. Call Customer Helpline Desk");
    System.out.println("2. Make Online Written Complaint");
    System.out.println("3. Provide Feedback");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    switch (choice) {
        case 1:
            callCustomerHelpline();
            break;
        case 2:
            makeComplaint();
            break;
        case 3:
            provideFeedback();
            break;
        default:
            System.out.println("Invalid choice.");
    }
}
public static void callCustomerHelpline() {
    System.out.println("Calling Customer Helpline Desk...");
    // Logic for calling the customer helpline can be implemented here.
}

// Make online written complaint
public static void makeComplaint() {
    System.out.println("Make Online Written Complaint");
    System.out.print("Enter your complaint: ");
    String complaint = scanner.nextLine();
    // Logic for submitting the complaint can be implemented here.
    System.out.println("Complaint submitted successfully.");
}

// Provide feedback
public static void provideFeedback() {
    System.out.println("Provide Feedback");
    System.out.print("Enter your feedback: ");
    String feedback = scanner.nextLine();
    // Logic for submitting the feedback can be implemented here.
    System.out.println("Feedback submitted successfully.");
}
// View feedback and complaints
public static void viewFeedbackAndComplaints() {
    System.out.println("Feedback and Complaints:");

    // Check if there are any feedback or complaints
    if (feedbackAndComplaints.isEmpty()) {
        System.out.println("No feedback or complaints available.");
        return;
    }

    // Display all feedback and complaints
    for (String entry : feedbackAndComplaints) {
        System.out.println(entry);
    }
}

}
