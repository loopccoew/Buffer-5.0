package Buffer;


import java.util.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.io.*;
//import java.util.Arrays;

class User implements Serializable {
    private String username;
    private String password;
    //private String uadd;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        //this.uadd=uadd;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    

   
}

class Vol implements Serializable {
    private String volname;
    private String vol_password;
    private List<String> preferredAreas;
   // private List<String> preferredAreas;
   private User assignedUser;
    public Vol(String volname, String vol_password,List<String> preferredAreas) {
        this.volname = volname;
        this.vol_password = vol_password;
       // this.preferredAreas = preferredAreas;
       this.preferredAreas = preferredAreas;
    }

    public String getVolname() {
        return volname;
    }

    public String getVPassword() {
        return vol_password;
    }
    public List<String> getPreferredAreas() {
        return preferredAreas;
    }
    public User getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(User assignedUser) {
        this.assignedUser = assignedUser;
    }
}

 class Trashcash {
    private static final String USER_DATA_FILE = "users.dat";
    private static final String VOL_DATA_FILE = "vol.dat";
    private static List<User> users = new ArrayList<>();
    private static List<Vol> vol = new ArrayList<>();
    private static VolunteerQueueManager volunteerQueueManager = new VolunteerQueueManager();
    public static final String ANSI_RESET="\u001B[0m";
    public static final String ANSI_BOLD="\u001B[1m";private int value;

    public static void main(String[] args) {
        loadUsers();
        loadVol();
        for (Vol volunteer : vol) {
            volunteerQueueManager.addVolunteer(volunteer);
        }
        Scanner sc = new Scanner(System.in);

        System.out.println(     ANSI_BOLD+"Welcome to TrashCash!"+ANSI_RESET     );
        System.out.println("Are you a "+ANSI_BOLD+"user"+ANSI_RESET+" or a "+ANSI_BOLD+"volunteer"+ANSI_RESET+"(1 for user and 2 for volunteer)");
        int ch2 = sc.nextInt();
        sc.nextLine(); // Consume newline left-over

        // Registration loop
        while (true) {
            if (ch2 == 1) {
                System.out.println();
                System.out.println(ANSI_BOLD+"USER PROFILE"+ANSI_RESET);
                System.out.print("  Enter a username: ");
                String username = sc.nextLine();
                if (usernameExists(username)) {
                    System.out.print("  Username already exists. Do you want to log in?"+ANSI_BOLD+"(yes/no):"+ANSI_RESET);
                    String choice = sc.nextLine().toLowerCase();
                    if (choice.equals("yes")) {
                        if (loginUser(sc, username)) {
                            
                            break; // Exit the loop since login is successful
                        }
                    } else {
                        continue; // Go to the beginning of the loop to retry registration
                    }
                } else {
                    System.out.print("  Enter a password: ");
                    String password = sc.nextLine();
                    User newUser = new User(username, password);
                    users.add(newUser);
                    saveUsers();
                    System.out.print(ANSI_BOLD+"  Registration successful for user: " + username+ANSI_RESET+"\n");
                    // Exit the loop since registration is successful
            
                    break;
                }
            } else if (ch2 == 2) {
                System.out.println(ANSI_BOLD+"VOLUNTEER PROFILE"+ANSI_RESET);
                System.out.print("  Enter a username: ");
                String volname = sc.nextLine();
                if (volExists(volname)) {
                    System.out.print("  Username already exists. Do you want to log in? "+ANSI_BOLD+"(yes/no):"+ANSI_RESET);
                    String choice = sc.nextLine().toLowerCase();
                    if (choice.equals("yes")) {
                        if (loginVol(sc, volname)) {
                            break; // Exit the loop since login is successful
                        }
                    } else {
                        continue; // Go to the beginning of the loop to retry registration
                    }
                } else {
                    System.out.print("  Enter a password: ");
                    String vol_password = sc.nextLine();
        List<String> preferredAreas = new ArrayList<>();
        
        System.out.print("  Enter your preferred areas,separated by commas:");
        String areasInput = sc.nextLine();
        String[] areasArray = areasInput.split(",");
        preferredAreas.addAll(Arrays.asList(areasArray));

                    // Create a new user profile and add it to the list
                    Vol newVol = new Vol(volname, vol_password, preferredAreas);
                    
                    vol.add(newVol);
                    saveVol();
                    System.out.println(ANSI_BOLD+"  Registration successful for volunteer: " + volname+ANSI_RESET);
                    // Exit the loop since registration is successful
                    break;
                }
        
            }
        }

        // After successful registration/login
        if (ch2 == 1) {
            // Booking appointment
            System.out.print("  Do you want to book an appointment? "+ANSI_BOLD+"(yes/no):"+ANSI_RESET);
            String bookAppointment = sc.nextLine().toLowerCase();
            if (bookAppointment.equals("yes")) {
                System.out.println();
                System.out.print(ANSI_BOLD+"  Choose the dry waste items you want to dispose:"+ANSI_RESET+"\n");
                Map<Integer, String> dryWastes = new HashMap<>();
                dryWastes.put(1, "Newspaper/books/Magazines");
                dryWastes.put(2, "Plastic No. 1-bottles, clothing, carpet fibers, and packaging materials.");
                dryWastes.put(3, "Plastic No. 2-milk jugs, detergent bottles, household cleaner bottles, plastic bags, and plastic lumber.");
                dryWastes.put(4, "Plastic No. 3-pipes and fittings, vinyl siding, window frames, flooring, medical tubing, and credit cards.");
                dryWastes.put(5, "Tyres and tubes");
                dryWastes.put(6, "Rubber seals and gaskets");
                dryWastes.put(7, "Rubber toys and sporting goods");
                dryWastes.put(8, "Rubberised fibres and textiles");
                dryWastes.put(9, "Rubber hoses and tubing");
                dryWastes.put(10, "Thermocol");
                dryWastes.put(11, "Styrofoam");
                dryWastes.put(12, "Aluminium cans");
                dryWastes.put(13, "Clear Glass bottles");
                dryWastes.put(14, "Colored Glass bottles");
            
                // Displaying dry waste items
                for (Integer key : dryWastes.keySet()) {
                    System.out.println(key + ". " + dryWastes.get(key));
                }
            
                List<String> selectedItems = new ArrayList<>();
                boolean validInput = false;
                Map<String, Float> weightsMap = new HashMap<>();
                while (!validInput) {
                    // Handling user selection
                    System.out.print("  Enter the serial numbers of the items you want to dispose (comma-separated):");
                    String choicesInput = sc.nextLine();
                    String[] choicesArray = choicesInput.split(",");
                    selectedItems.clear();
            
                    for (String choiceStr : choicesArray) {
                        int choice;
                        try {
                            choice = Integer.parseInt(choiceStr.trim());
                        } catch (NumberFormatException e) {
                            System.out.println("  Invalid input. Please enter comma-separated serial numbers.");
                            validInput = false;
                            break;
                        }
            
                        if (choice >= 1 && choice <= dryWastes.size()) {
                            selectedItems.add(dryWastes.get(choice));
                            validInput = true;
                        } else {
                            System.out.println("  Invalid choice: " + choice);
                            validInput = false;
                            break;
                        }
                    }
            
                    if (validInput) {
                        // Print selected items in the specified format
                        System.out.println(ANSI_BOLD+"  You selected:"+ANSI_RESET);
                        for (String item : selectedItems) {
                            System.out.println("   - " + item);
                        }
                        
                     float cost=0;
                        for (String item1 : selectedItems){
                           System.out.print("  Enter the weight for "+ANSI_BOLD+item1+ANSI_RESET+"(in kg): ");
                           float w=sc.nextFloat();
                           weightsMap.put(item1, w);
                        }

                        float w1; 
                        System.out.println("\n  The "+ANSI_BOLD+"approximate amount "+ANSI_RESET+"you will get in return for the given dry waste:");
                       if(weightsMap.containsKey("Newspaper/books/Magazines")){
                        w1=weightsMap.get("Newspaper/books/Magazines");
                        System.out.println("   Newspaper/books/Magazines:"+ANSI_BOLD+(w1*5 ) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*5);
                       }
                       if(weightsMap.containsKey("Plastic No. 1-bottles, clothing, carpet fibers, and packaging materials.")){
                        w1=weightsMap.get("Plastic No. 1-bottles, clothing, carpet fibers, and packaging materials.");
                        System.out.println("   Plastic No. 1-bottles, clothing, carpet fibers, and packaging materials.:"+ANSI_BOLD+(w1*18 ) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*18);
                       }
                       if(weightsMap.containsKey("Plastic No. 2-milk jugs, detergent bottles, household cleaner bottles, plastic bags, and plastic lumber.")){
                        w1=weightsMap.get("Plastic No. 2-milk jugs, detergent bottles, household cleaner bottles, plastic bags, and plastic lumber.");
                        System.out.println("   Plastic No. 2-milk jugs, detergent bottles, household cleaner bottles, plastic bags, and plastic lumber.:"+ANSI_BOLD+(w1*13 ) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*13);
                       }
                       if(weightsMap.containsKey("Plastic No. 3-pipes and fittings, vinyl siding, window frames, flooring, medical tubing, and credit cards.")){
                        w1=weightsMap.get("Plastic No. 3-pipes and fittings, vinyl siding, window frames, flooring, medical tubing, and credit cards.");
                        System.out.println("   Plastic No. 3-pipes and fittings, vinyl siding, window frames, flooring, medical tubing, and credit cards.:"+ANSI_BOLD+(w1*10 ) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*10);
                       }
                       if(weightsMap.containsKey("Tyres and tubes")){
                        w1=weightsMap.get("Tyres and tubes");
                        System.out.println("   Tyres and tubes:"+ANSI_BOLD+(w1*30) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*30);
                       }
                       if(weightsMap.containsKey("Rubber seals and gaskets")){
                        w1=weightsMap.get("Rubber seals and gaskets");
                        System.out.println("   Rubber seals and gaskets:"+ANSI_BOLD+(w1*6 ) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*6);
                       }
                       if(weightsMap.containsKey("Rubber toys and sporting goods")){
                        w1=weightsMap.get("Rubber toys and sporting goods");
                        System.out.println("   Rubber toys and sporting goods:"+ANSI_BOLD+(w1*24) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*24);
                       }
                       if(weightsMap.containsKey("Rubber hoses and tubing")){
                        w1=weightsMap.get("Rubber hoses and tubing");
                        System.out.println("   Rubber hoses and tubing:"+ANSI_BOLD+(w1*12) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*12);
                       }
                       if(weightsMap.containsKey("Rubberised fibres and textiles")){
                        w1=weightsMap.get("Rubberised fibres and textiles");
                        System.out.println("   Rubberised fibres and textiles:"+ANSI_BOLD+(w1*17) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*17);
                       }
                       if(weightsMap.containsKey("Thermocol")){
                        w1=weightsMap.get("Thermocol");
                        System.out.println("   Thermocol:"+ANSI_BOLD+(w1*19) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*19);
                       }
                       if(weightsMap.containsKey("Styrofoam")){
                        w1=weightsMap.get("Styrofoam");
                        System.out.println("   Styrofoam:"+ANSI_BOLD+(w1*15) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*15);
                       }
                       if(weightsMap.containsKey("Aluminium cans")){
                        w1=weightsMap.get("Aluminium cans");
                        System.out.println("   Aluminium cans:"+ANSI_BOLD+(w1*75) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*75);
                       }
                       if(weightsMap.containsKey("Clear Glass bottles")){
                        w1=weightsMap.get("Clear Glass bottles");
                        System.out.println("   Clear Glass bottles:"+ANSI_BOLD+(w1*7) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*7);
                       }
                       if(weightsMap.containsKey("Colored Glass bottles")){
                        w1=weightsMap.get("Colored Glass bottles");
                        System.out.println("   Colored Glass bottles:"+ANSI_BOLD+(w1*5) +" Rs/-"+ANSI_RESET);
                        cost+=(w1*5);
                       }
                      
                       System.out.println(ANSI_BOLD+"  The approximate amount that you will get in return of your total waste is:" + cost+"Rs/-"+ANSI_RESET);
                       

                    }
                    
                }
                System.out.println();
                System.out.print("  Enter the area:");
                String uadd=sc.next();
                String date;
                System.out.print("  Enter your preferred date of appointment(DD/MM/YY):");
                date=sc.next();
                
                
    
            Vol assignedVolunteer = volunteerQueueManager.assignVolunteer(uadd,date);
        
        if (assignedVolunteer!=null) {
            System.out.println("  You are assigned to volunteer: "+ANSI_BOLD +assignedVolunteer.getVolname()+ANSI_RESET);
        } else {
            System.out.println("  No volunteer available for your residential area.");
        }
       
                
            
            
    
        sc.close();
  System.out.println("\nNever let your waste go to waste because");
  System.out.println(ANSI_BOLD+"We have got Cash for your Trash"+ANSI_RESET);
     }
     
    }// Close the Scanner object
    }


    private static void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_DATA_FILE))) {
            users = (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file does not exist or cannot be read, continue with an empty list of users
        }
    }

    private static void saveUsers() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_DATA_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean usernameExists(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

   // @SuppressWarnings("unused")
    private static boolean loginUser(Scanner scanner, String username ) {
        System.out.print("  Enter your password: ");
        String password = scanner.nextLine();
       

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println(ANSI_BOLD+"  Login successful. Welcome back, " + username + "!"+ANSI_RESET);
                return true;
            }
        }
        System.out.println("  Invalid username or password."+ANSI_BOLD+" Please try again."+ANSI_RESET);
        return false;
    }

   // @SuppressWarnings("unchecked")
    private static void loadVol() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(VOL_DATA_FILE))) {
           vol = (List<Vol>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file does not exist or cannot be read, continue with an empty list of users
        }
    }

    private static void saveVol() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(VOL_DATA_FILE))) {
            oos.writeObject(vol);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean volExists(String volname) {
        for (Vol user : vol) {
            if (user.getVolname().equals(volname)) {
                return true;
            }
        }
        return false;
    }

    private static boolean loginVol(Scanner scanner, String volname) {
        System.out.print("Enter your password: ");
        String vol_password = scanner.nextLine();

        for (Vol user : vol) {
            if (user.getVolname().equals(volname) && user.getVPassword().equals(vol_password)) {
                System.out.println(ANSI_BOLD+"  Login successful. Welcome back, " + volname + "!"+ANSI_RESET);
                return true;
            }
        }
        System.out.println("  Invalid username or password."+ANSI_BOLD+" Please try again."+ANSI_RESET);
        return false;
    }
    
}


 class VolunteerQueueManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String VOLUNTEER_QUEUE_DATA_FILE = "volunteer_queues.dat";
    private Map<String, Queue<Vol>> volunteerQueues;
    private Map<String, Queue<Vol>> assignedVolunteers;

    public VolunteerQueueManager() {
        volunteerQueues = new HashMap<>();
        assignedVolunteers = new HashMap<>();
        loadVolunteerQueues();
    }

    public void addVolunteer(Vol volunteer) {
        List<String> preferredAreas = volunteer.getPreferredAreas();
        for (String area : preferredAreas) {
            volunteerQueues.putIfAbsent(area, new LinkedList<>());
            volunteerQueues.get(area).offer(volunteer);
        }
        saveVolunteerQueues();
    }
     

    public Vol assignVolunteer(String userResidentialArea,String date) {
        Queue<Vol> queue = volunteerQueues.get(userResidentialArea);
        //System.out.println("  User's residential area: " + userResidentialArea);
    
        if (queue != null && !queue.isEmpty()) {
            Queue<Vol> assignedQueue = assignedVolunteers.getOrDefault(userResidentialArea, new LinkedList<>());
            Vol assignedVolunteer = null;
    
            // Ensure that a different volunteer is assigned each time
            while (!queue.isEmpty()) {
                Vol volunteer = queue.poll();
                if (!assignedQueue.contains(volunteer)) {
                    assignedVolunteer = volunteer;
                    assignedQueue.offer(assignedVolunteer);
                    assignedVolunteers.put(userResidentialArea, assignedQueue); // Update assigned volunteers map
                    System.out.println("  Your waste will be collected on the date:" + date);
                    break;
                }
            }
            saveVolunteerQueues(); // Save the updated queues
            return assignedVolunteer;
        }
          
        return null;
    }
    

    private void loadVolunteerQueues() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(VOLUNTEER_QUEUE_DATA_FILE))) {
            volunteerQueues = (Map<String, Queue<Vol>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file does not exist or cannot be read, continue with empty queues
        }
    }

    private void saveVolunteerQueues() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(VOLUNTEER_QUEUE_DATA_FILE))) {
            oos.writeObject(volunteerQueues);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



    

    


