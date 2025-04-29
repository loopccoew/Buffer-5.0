package radcode;
import java.util.*;

class Backend {

HashMap<String, HashMap<String, Object>> studentDetails; // Main hashmap to store student details and nested hashmaps for scores and messages

Backend() {

studentDetails = new HashMap<>();

}

//Method to add a student with details and password
public void addStudent(String rollNumber, String name, String year, String branch, Map<String, HashMap<String, Object>> likings, String password) {
 HashMap<String, Object> details = new HashMap<>();
 details.put("name", name);
 details.put("year", year);
 details.put("branch", branch);
 details.put("likings", likings);
 details.put("scores", new HashMap<String, Integer>()); // Nested hashmap to store scores
 details.put("messages", new HashMap<String, List<String>>()); // Nested hashmap to store messages
 details.put("password", password); // Nested field to store password
 studentDetails.put(rollNumber, details);
}


// Method to verify password

public boolean verifyPassword(String rollNumber, String password) {

HashMap<String, Object> details = studentDetails.get(rollNumber);

if (details != null) {

String storedPassword = (String) details.get("password");

return storedPassword != null && storedPassword.equals(password);

}

return false;

}

// Method to add scores for a student

public void addScores(String studentRollNumber, Map<String, Integer> scores) {

HashMap<String, Object> details = studentDetails.get(studentRollNumber);

HashMap<String, Integer> scoresTable = (HashMap<String, Integer>) details.get("scores");

scoresTable.putAll(scores);

}

// Method to send message from one student to another

public void sendMessage(String senderRollNumber, String receiverRollNumber, String message) {

HashMap<String, Object> senderDetails = studentDetails.get(senderRollNumber);

HashMap<String, List<String>> messages = (HashMap<String, List<String>>) senderDetails.get("messages");

List<String> messageList = messages.getOrDefault(receiverRollNumber, new ArrayList<>());

messageList.add(message);

messages.put(receiverRollNumber, messageList);

senderDetails.put("messages", messages);

}

// Method to get student details by name

public String getRollNumberByName(String name) {

for (Map.Entry<String, HashMap<String, Object>> entry : studentDetails.entrySet()) {

if (((String) entry.getValue().get("name")).equalsIgnoreCase(name)) {

return entry.getKey();

}

}

return null;

}

// Method to get student details by roll number

public HashMap<String, Object> getStudentDetails(String rollNumber) {

return studentDetails.get(rollNumber);

}

// Method to get scores for a student

public HashMap<String, Integer> getScores(String studentRollNumber) {

HashMap<String, Object> details = studentDetails.get(studentRollNumber);

return (HashMap<String, Integer>) details.get("scores");

}

// Method to get messages for a student

public HashMap<String, List<String>> getMessages(String studentRollNumber) {

HashMap<String, Object> details = studentDetails.get(studentRollNumber);

return (HashMap<String, List<String>>) details.get("messages");

}

// Method to get name by roll number

public String getNameByRollNumber(String rollNumber) {

HashMap<String, Object> details = studentDetails.get(rollNumber);

if (details != null) {

return (String) details.get("name");

}

return null;

}

}

public class Main {

static HashMap<String, List<String>> messageLog = new HashMap<>(); // Declare messageLog as a static member of Main class

public static void main(String[] args) {

final String ANSI_RESET = "\u001B[0m";

final String ANSI_CYAN = "\u001B[36m";

final String ANSI_BLUE = "\u001B[34m";

final String ANSI_PURPLE = "\u001B[35m";

final String ANSI_GREEN = "\u001B[32m";

Backend backend = new Backend();

Scanner scanner = new Scanner(System.in);

// Add students with passwords

System.out.println(ANSI_BLUE +"*************************************************************************************************************************************************************"+ ANSI_RESET);

centerPrint(ANSI_BLUE +"\t\t\t\t\t\tWelcome Explorers!!"+ ANSI_RESET);

System.out.println();

centerPrint(ANSI_BLUE +"Step into a digital realm where friendships flourish, ideas ignite, and communities come alive."+ ANSI_RESET);

centerPrint(ANSI_BLUE +"Imagine a place where every click opens doors to new possibilities, where serendipitous encounters"+ ANSI_RESET);

centerPrint(ANSI_BLUE +"spark meaningful conversations, and where the world feels smaller, yet infinitely more vibrant."+ ANSI_RESET);

System.out.println();

System.out.println(ANSI_BLUE +"*************************************************************************************************************************************************************"+ ANSI_RESET);

System.out.println(ANSI_BLUE +"Lets collect some data for the databse(backend):"+ ANSI_RESET);

System.out.println();

System.out.println("Enter the number of students for the database creation:");

System.out.print(ANSI_GREEN);

int numStudents = scanner.nextInt();

System.out.print(ANSI_RESET);

scanner.nextLine(); // Consume newline

for (int i = 1; i <= numStudents; i++) {

int flag = 0;

String name = "";

String year = "";

String branch = "";

String rollNumber = "";

String password = "";

do {

flag = 0;

System.out.println("Enter details for student " + i + ":");

System.out.println("Name:");

System.out.print(ANSI_GREEN);

name = scanner.nextLine();

System.out.print(ANSI_RESET);

System.out.println("Year of study(FY/SY/TY/B.TECH):");

System.out.print(ANSI_GREEN);

year = scanner.nextLine().toUpperCase(); // Convert to uppercase for case-insensitive comparison

if (!year.equals("FY") && !year.equals("SY") && !year.equals("TY") && !year.equals("B.TECH")) {

flag = 1;

}

System.out.print(ANSI_RESET);

System.out.println("Branch(Comp/IT/Instru/Mech):");

System.out.print(ANSI_GREEN);

branch = scanner.nextLine().toUpperCase(); // Convert to uppercase for case-insensitive comparison

if (!branch.equals("COMP") && !branch.equals("IT") && !branch.equals("INSTRU") && !branch.equals("MECH")) {

flag = 2;

}

System.out.print(ANSI_RESET);

System.out.println("Roll Number(enter U-no):");

System.out.print(ANSI_GREEN);

rollNumber = scanner.nextLine().toUpperCase(); // Convert to uppercase for case-insensitive comparison

if (!rollNumber.startsWith("UCE") || rollNumber.length() != 10) {

flag = 3;

}

System.out.print(ANSI_RESET);

System.out.println("Set a password:");

System.out.print(ANSI_GREEN);

password = scanner.nextLine();

System.out.print(ANSI_RESET);

switch (flag) {

case 1:

System.out.println("Error in Year of study!! Please re-enter the form!!");

break;

case 2:

System.out.println("Error in Branch!! Please re-enter the form!!");

break;

case 3:

System.out.println("Error in Roll Number!! Please re-enter the form!!");

break;

}

} while (flag != 0);

System.out.println("Enter number of likings:");

System.out.print(ANSI_GREEN);

int numLikings = scanner.nextInt();

System.out.print(ANSI_RESET);

scanner.nextLine(); // Consume newline

Map<String, HashMap<String, Object>> likings = new HashMap<>();

for (int j = 1; j <= numLikings; j++) {

	 System.out.print("Enter liking " + j + ": ");
     String liking = scanner.nextLine();

     System.out.print("Enter description for liking " + j + ": ");
     String description = scanner.nextLine();

     HashMap<String, Object> likingDetails = new HashMap<>();
     likingDetails.put("description", description);
     likings.put(liking, likingDetails);

}

backend.addStudent(rollNumber, name, year, branch, likings, password);

System.out.println("*************************************************************************************************************************************************************");

centerPrint("Data entered successfully!");

}

centerPrint("Now lets go to login page:");

System.out.println("*************************************************************************************************************************************************************");

System.out.println();

int opt=0;

System.out.println("Do you want to:");

System.out.println("1.Log in :)");

System.out.println("2.Exit :(");

System.out.print(ANSI_GREEN);

opt=scanner.nextInt();

scanner.nextLine();

System.out.print(ANSI_RESET);

if(opt==1) {

do {

boolean loggedIn = false;

String rollNumber = "";

while (!loggedIn) {

// Login process

System.out.println("*************************************************************************************************************************************************************");

System.out.println();

System.out.println("Login:");

System.out.println("Enter name:");

System.out.print(ANSI_GREEN);

String name = scanner.nextLine();

System.out.print(ANSI_RESET);

System.out.println("Enter password:");

System.out.print(ANSI_GREEN);

String password = scanner.nextLine();

System.out.print(ANSI_RESET);

rollNumber = backend.getRollNumberByName(name);

if (rollNumber != null && backend.verifyPassword(rollNumber, password)) {

loggedIn = true;

System.out.println("Logged in successfully as " + name);

} else {

System.out.println("Incorrect name or password. Please try again.");

}

System.out.println();

System.out.println("*************************************************************************************************************************************************************");

System.out.println();

}

boolean exit = false;

while (!exit) {

System.out.println("*************************************************************************************************************************************************************");

System.out.println();

centerPrint(ANSI_PURPLE + "Menu:"+ ANSI_RESET);

centerPrint(ANSI_PURPLE + "1. Calculate score with others"+ ANSI_RESET);

centerPrint(ANSI_PURPLE + "2. See section members with matched scores and send messages"+ ANSI_RESET);

centerPrint(ANSI_PURPLE + "3. See messages sent"+ ANSI_RESET);

System.out.println(ANSI_PURPLE + "\t\t\t\t\t       4. See new messages"+ ANSI_RESET);

centerPrint(ANSI_PURPLE + "5. All Conversions"+ ANSI_RESET);

System.out.println(ANSI_PURPLE + "\t\t\t\t\t       6. Logout"+ ANSI_RESET);

centerPrint(ANSI_PURPLE + "Choose an option:"+ ANSI_RESET);

System.out.println();

System.out.println("*************************************************************************************************************************************************************");

System.out.print(ANSI_GREEN);

int option = scanner.nextInt();

System.out.print(ANSI_RESET);

scanner.nextLine(); // Consume newline

switch (option) {

case 1:

//System.out.println("*************************************************************************************************************************************************************");

calculateAndDisplayScores(backend, rollNumber);

//System.out.println("*************************************************************************************************************************************************************");

break;

case 2:

//System.out.println("*************************************************************************************************************************************************************");

displaySectionMembersWithMatchedScoresAndSendMessage(backend, rollNumber, scanner);

//System.out.println("*************************************************************************************************************************************************************");

break;

case 3:

//System.out.println("*************************************************************************************************************************************************************");

displaySentMessages(backend, rollNumber);

//System.out.println("*************************************************************************************************************************************************************");

break;

case 4:

//System.out.println("*************************************************************************************************************************************************************");

displayReceivedMessages(backend, rollNumber);

//System.out.println("*************************************************************************************************************************************************************");

break;

case 5:

System.out.println("*************************************************************************************************************************************************************");

displayAllConversations(backend, rollNumber);

System.out.println("*************************************************************************************************************************************************************");

break;

case 6:

System.out.println("*************************************************************************************************************************************************************");

System.out.println("Logged out successfully.");

System.out.println("*************************************************************************************************************************************************************");

loggedIn = false;

exit=true;

break;

default:

System.out.println("Invalid option. Please choose again.");

}

}

System.out.println("Do you want to:");

System.out.println("1.Log in :)");

System.out.println("2.Exit :(");

opt=scanner.nextInt();

scanner.nextLine();

}while(opt!=2);

}

else {

System.out.println("See you next time!!");

}

scanner.close();

}

// Method to calculate score between two students based on likings

public static int calculateScore(Backend backend, String rollNumber1, String rollNumber2) {
    HashMap<String, Object> details1 = backend.getStudentDetails(rollNumber1);
    HashMap<String, Object> details2 = backend.getStudentDetails(rollNumber2);
    
    Map<String, HashMap<String, Object>> likings1 = (Map<String, HashMap<String, Object>>) details1.get("likings");
    Map<String, HashMap<String, Object>> likings2 = (Map<String, HashMap<String, Object>>) details2.get("likings");

    int score = 0;

    for (Map.Entry<String, HashMap<String, Object>> entry : likings1.entrySet()) {
        String liking1 = entry.getKey();
        if (likings2.containsKey(liking1)) {
            score++;
        }
    }

    return score;
}


public static void displayAllConversations(Backend backend, String rollNumber) {
    String name = backend.getNameByRollNumber(rollNumber);

    if (name != null) {
        System.out.println("*************************************************************************************************************************************************************");
        System.out.println("All Conversations:");

        // Iterate through messageLog and display conversations involving the logged-in student
        for (Map.Entry<String, List<String>> entry : messageLog.entrySet()) {
            String conversationKey = entry.getKey();
            List<String> conversation = entry.getValue();

            // Check if the conversation involves the logged-in student
            if (conversationKey.startsWith(rollNumber + "-") || conversationKey.endsWith("-" + rollNumber)) {
                String[] participants = conversationKey.split("-");
                String otherStudentRollNumber = participants[0].equals(rollNumber) ? participants[1] : participants[0];
                String otherStudentName = backend.getNameByRollNumber(otherStudentRollNumber);

                if (otherStudentName != null) {
                    System.out.println("Conversation with " + otherStudentName + ":");

                    // Iterate through messages in the conversation
                    for (String message : conversation) {
                        String senderName = backend.getNameByRollNumber(participants[0].equals(rollNumber) ? rollNumber : otherStudentRollNumber);

                        // Determine the direction of the message (sent or received)
                        //String direction = participants[0].equals(rollNumber) ? "Sent" : "Received";
                        System.out.println("- " + senderName + " : " + message);
                    }

                    System.out.println();
                }
            }
        }
    }
}

// Method to calculate and display scores for the logged-in student

public static void calculateAndDisplayScores(Backend backend, String rollNumber) {

String name = backend.getNameByRollNumber(rollNumber);

if (name != null) {

Map<String, Integer> studentScores = new HashMap<>();

for (Map.Entry<String, HashMap<String, Object>> entry : backend.studentDetails.entrySet()) {

String otherRollNumber = entry.getKey();

if (!otherRollNumber.equals(rollNumber)) {

int score = calculateScore(backend, rollNumber, otherRollNumber);

studentScores.put(otherRollNumber, score);

}

}

backend.addScores(rollNumber, studentScores); // Update the scores for the logged-in student

displayScores(backend, studentScores, name);

}

}

// Method to display section members with matched scores and send messages

public static void displaySectionMembersWithMatchedScoresAndSendMessage(Backend backend, String rollNumber, Scanner scanner) {

String name = backend.getNameByRollNumber(rollNumber);

if (name != null) {

System.out.println("*************************************************************************************************************************************************************");

System.out.println("Section members with matched scores:");

Map<String, Integer> studentScores = backend.getScores(rollNumber);

boolean foundScores = false; // Flag to track if any scores are found

if (!studentScores.isEmpty()) {

List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(studentScores.entrySet());

sortedScores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

int count = 0;

for (Map.Entry<String, Integer> entry : sortedScores) {

String otherStudentRollNumber = entry.getKey();

int score = entry.getValue();

String otherStudentName = backend.getNameByRollNumber(otherStudentRollNumber);

if (otherStudentName != null && !otherStudentRollNumber.equals(rollNumber)) {

System.out.println("- Name: " + otherStudentName + ", Score: " + score);

displayStudentDetails(backend, otherStudentRollNumber);

count++;

if (count >= 5) {

break; // Display maximum 5 members

}

foundScores = true;

}

}

}

if (!foundScores) {

System.out.println("No scores found for " + name + " with other students.");

}

System.out.println("*************************************************************************************************************************************************************");

// Send messages

while (true) {

final String ANSI_RESET = "\u001B[0m";

final String ANSI_GREEN = "\u001B[32m";

System.out.println("Enter the names of members you want to send messages (enter 'stop' to finish):");

System.out.print(ANSI_GREEN);

String receiverName = scanner.nextLine();

System.out.print(ANSI_RESET);

if (receiverName.equalsIgnoreCase("stop")) {

break;

}

String receiverRollNumber = backend.getRollNumberByName(receiverName);

if (receiverRollNumber != null && !receiverRollNumber.equals(rollNumber)) {

while (true) {

System.out.println("Enter your message to " + receiverName + " (type 'stop' to exit this member):");

System.out.print(ANSI_GREEN);

String message = scanner.nextLine();

System.out.print(ANSI_RESET);

if (message.equalsIgnoreCase("stop")) {

break;

}

backend.sendMessage(rollNumber, receiverRollNumber, message);

System.out.println("Message sent successfully to " + receiverName + "!");

}

} else {

if (receiverRollNumber == null) {

System.out.println("Invalid name: " + receiverName + ". Please enter a valid name or 'stop' to finish.");

} else {

System.out.println("You cannot send a message to yourself.");

}

}

}

}

}

// Helper method to display scores for the logged-in student

public static void displayScores(Backend backend, Map<String, Integer> studentScores, String studentName) {

System.out.println("*************************************************************************************************************************************************************");

System.out.println("Scores for " + studentName + " with other students (Descending Order):");

if (studentScores.isEmpty()) {

System.out.println("No scores found for " + studentName + " with other students.");

} else {

List<Map.Entry<String, Integer>> sortedScores = new ArrayList<>(studentScores.entrySet());

sortedScores.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

for (Map.Entry<String, Integer> entry : sortedScores) {

String otherStudentRollNumber = entry.getKey();

String otherStudentName = backend.getNameByRollNumber(otherStudentRollNumber);

int score = entry.getValue();

System.out.println("- Name: " + otherStudentName + ", Score: " + score);

displayStudentDetails(backend, otherStudentRollNumber);

}

}

System.out.println("*************************************************************************************************************************************************************");

}

// Method to display student details

public static void displayStudentDetails(Backend backend, String rollNumber) {
    HashMap<String, Object> details = backend.getStudentDetails(rollNumber);

    if (details != null) {
        System.out.println(" Details:");
        System.out.println(" Roll Number: " + rollNumber);
        System.out.println(" Name: " + details.get("name"));
        System.out.println(" Year: " + details.get("year"));
        System.out.println(" Branch: " + details.get("branch"));
        
        System.out.println(" Likings:");
        Map<String, HashMap<String, Object>> likings = (Map<String, HashMap<String, Object>>) details.get("likings");
        for (Map.Entry<String, HashMap<String, Object>> entry : likings.entrySet()) {
            String liking = entry.getKey();
            String description = (String) entry.getValue().get("description");
            System.out.println(" - " + liking + ": " + description);
        }
    }
}


// Method to display sent messages

public static void displaySentMessages(Backend backend, String rollNumber) {

String name = backend.getNameByRollNumber(rollNumber);

if (name != null) {

System.out.println("*************************************************************************************************************************************************************");

System.out.println("Messages sent:");

HashMap<String, List<String>> sentMessages = backend.getMessages(rollNumber);

if (sentMessages.isEmpty()) {

System.out.println("No messages sent yet.");

} else {

for (Map.Entry<String, List<String>> entry : sentMessages.entrySet()) {

String receiverName = backend.getNameByRollNumber(entry.getKey());

if (receiverName != null) {

System.out.println("Messages sent to " + receiverName + ":");

for (String message : entry.getValue()) {

System.out.println("- " + message);

}

}

}

}

}

System.out.println("*************************************************************************************************************************************************************");

}

// Method to display received messages

public static void displayReceivedMessages(Backend backend, String rollNumber) {

String name = backend.getNameByRollNumber(rollNumber);

int flag=0; //to check for messages
if (name != null) {

System.out.println("*************************************************************************************************************************************************************");

System.out.println("Received Messages:");

HashMap<String, List<String>> receivedMessages = new HashMap<>();



for (Map.Entry<String, HashMap<String, Object>> entry : backend.studentDetails.entrySet()) {

String senderRollNumber = entry.getKey();

String senderName = backend.getNameByRollNumber(senderRollNumber);

if (senderName != null) {

HashMap<String, List<String>> senderMessages = backend.getMessages(senderRollNumber);

if (senderMessages.containsKey(rollNumber)) {

List<String> messages = senderMessages.get(rollNumber);

//System.out.println("Messages from "+senderName);

if (!messageLog.containsKey(senderRollNumber + "-" + rollNumber)) {

for (String message : messages) {

System.out.println(senderName+"- " + message);

flag++;

}

messageLog.put(senderRollNumber + "-" + rollNumber, new ArrayList<>(messages));

} 
else {

List<String> logMessages = messageLog.get(senderRollNumber + "-" + rollNumber);

//System.out.println("Messages from "+senderName);

for (String message : messages) {

if (!logMessages.contains(message)) {

System.out.println(senderName+"- " + message);

logMessages.add(message);

flag++;

}

}

messageLog.put(senderRollNumber + "-" + rollNumber, logMessages);






}

}

}

}

}

if(flag==0)
	System.out.println("No messages received yet");
	

System.out.println("*************************************************************************************************************************************************************");

}

// Method to center-align text with each option on its own line

public static void centerPrint(String text) {

int screenWidth = 80; // Assuming console width of 80 characters

int padding = (screenWidth - text.length()) / 2;

String centeredText = String.format("%" + (padding + text.length() / 2) + "s%s%" + (padding - text.length() / 2) + "s", "", text, "");

System.out.println(centeredText);

}

}    