package buffer;

import java.util.*;







class Question {







 private String question;







 private List<String> answers;















 public Question(String question) {







 this.question = question;







 this.answers = new ArrayList<>();







 }















 public String getQuestion() {







 return question;







 }















 public void setQuestion(String question) {







 this.question = question;







 }















 public List<String> getAnswers() {







 return answers;







 }















 public void addAnswer(String answer) {







 answers.add(answer);







 }







}















class askque {







 private static ArrayList<Question> questions = new ArrayList<>();







 private static Scanner scanner = new Scanner(System.in);















 public void main1() {



 int choice;







 do {



 displayMenu();



 



 // Read choice inside the loop



 choice = scanner.nextInt();



 scanner.nextLine(); // Consume newline







 switch (choice) {



 case 1:



 addQuestion();



 break;



 case 2:



 displayQuestions();



 break;



 case 3:



 answerQuestion();



 break;



 case 4:



 displayAnsweredQuestions();



 break;



 case 5:



 System.out.println("Exiting program. Goodbye!");



 break;



 default:



 System.out.println("Invalid choice. Please enter a number from the menu.");



 }







 } while (choice != 5);



 }















 void displayMenu() {







 System.out.println("\nQuestion Management System Menu:");







 System.out.println("1. Ask Question");







 System.out.println("2. Display Questions");







 System.out.println("3. Answer Question");







 System.out.println("4. Display Answered Questions");







 System.out.println("5. Exit");







 System.out.print("Enter your choice: ");



 



 }















 private static void addQuestion() {







 System.out.println("\nEnter your question:");







 String question = scanner.nextLine();







 questions.add(new Question(question));







 System.out.println("Question added successfully!");







 }















 private static void displayQuestions() {







 if (questions.isEmpty()) {







 System.out.println("No questions available.");







 } else {







 System.out.println("\nQuestions:");







 for (int i = 0; i < questions.size(); i++) {







 Question q = questions.get(i);







 System.out.println((i + 1) + ". " + q.getQuestion());







 List<String> answers = q.getAnswers();







 if (!answers.isEmpty()) {







 System.out.println(" Answers:");







 for (String answer : answers) {







 System.out.println(" - " + answer);







 }







 }







 }







 }







 }















 private static void answerQuestion() {







 if (questions.isEmpty()) {







 System.out.println("No questions available to answer.");







 return;







 }















 displayQuestions();







 System.out.print("\nEnter the number of the question you want to answer: ");







 int index = scanner.nextInt();







 scanner.nextLine(); // Consume newline















 if (index < 1 || index > questions.size()) {







 System.out.println("Invalid question number.");







 return;







 }















 Question question = questions.get(index - 1);







 System.out.println("Question: " + question.getQuestion());







 System.out.println("Enter your answer (type 'done' to finish):");















 while (true) {







 String answer = scanner.nextLine();







 if (answer.equalsIgnoreCase("done")) {







 break;







 }







 question.addAnswer(answer);







 }















 System.out.println("Question answered successfully!");







 }















 private static void displayAnsweredQuestions() {







 boolean answeredQuestionsExist = false;







 System.out.println("\nAnswered Questions:");







 for (Question q : questions) {







 if (!q.getAnswers().isEmpty()) {







 answeredQuestionsExist = true;







 System.out.println("- " + q.getQuestion());







 List<String> answers = q.getAnswers();







 for (String answer : answers) {







 System.out.println(" - " + answer);







 }







 }







 }







 if (!answeredQuestionsExist) {







 System.out.println("No questions have been answered yet.");







 }







 }







}











class Event {



 private String title;



 private String date;



 private String time;



 private String venue;



 private String eligibility;



 private double registrationFees;







 public Event(String title, String date, String time, String venue, String eligibility, double registrationFees) {



 this.title = title;



 this.date = date;



 this.time = time;



 this.venue = venue;



 this.eligibility = eligibility;



 this.registrationFees = registrationFees;



 }







 // Getters and setters



 public String getTitle() {



 return title;



 }







 public void setTitle(String title) {



 this.title = title;



 }







 public String getDate() {



 return date;



 }







 public void setDate(String date) {



 this.date = date;



 }







 public String getTime() {



 return time;



 }







 public void setTime(String time) {



 this.time = time;



 }







 public String getVenue() {



 return venue;



 }







 public void setVenue(String venue) {



 this.venue = venue;



 }







 public String getEligibility() {



 return eligibility;



 }







 public void setEligibility(String eligibility) {



 this.eligibility = eligibility;



 }







 public double getRegistrationFees() {



 return registrationFees;



 }







 public void setRegistrationFees(double registrationFees) {



 this.registrationFees = registrationFees;



 }







 @Override



 public String toString() {



 return "Event{" +



 "title='" + title + '\'' +



 ", date='" + date + '\'' +



 ", time='" + time + '\'' +



 ", venue='" + venue + '\'' +



 ", eligibility='" + eligibility + '\'' +



 ", registrationFees=" + registrationFees +



 '}';



 }



}







class Node {



 Event data;



 Node next;







 public Node(Event data) {



 this.data = data;



 this.next = null;



 }



}







class CircularLinkedList {



 private Node head;



 private Node tail;







 public CircularLinkedList() {



 head = null;



 tail = null;



 }







 public void insert(Event data) {



 Node newNode = new Node(data);



 if (head == null) {



 head = newNode;



 tail = newNode;



 tail.next = head; // Circular link



 } else {



 tail.next = newNode;



 tail = newNode;



 tail.next = head; // Circular link



 }



 }







 public void display() {



 if (head == null) {



 System.out.println("Circular Linked List is empty.");



 return;



 }



 Node current = head;



 do {



 System.out.println(current.data);



 current = current.next;



 } while (current != head);



 }



}



class EventManager {



 private static ArrayList<Event> events = new ArrayList<>();



 private static CircularLinkedList circularList = new CircularLinkedList();



 private static Scanner scanner = new Scanner(System.in);



 private static Scanner scanner1 = new Scanner(System.in);







 public static void menu() {



 while (true) {



 displayMenu();



 int choice = scanner1.nextInt();



 scanner.nextLine(); // Consume newline



 switch (choice) {



 case 1:



 addEvents();



 break;



 case 2:



 updateEvents();



 break;



 case 3:



 deleteEvents();



 break;



 case 4:



 displayEvents();



 break;



 case 5:



 System.out.println("Exiting program. Goodbye!");



 // System.exit(0);



 default:



 System.out.println("Invalid choice. Please enter a number from the menu.");



 }



 }



 



 }







 private static void displayMenu() {



 System.out.println("\nEvent Management System Menu:");



 System.out.println("1. Add Events");



 System.out.println("2. Update Events");



 System.out.println("3. Delete Events");



 System.out.println("4. Display Events");



 System.out.println("5. Exit");



 System.out.print("Enter your choice: ");



 }







 private static void addEvents() {



 int numEvents = getNumberOfEvents("add");



 for (int i = 0; i < numEvents; i++) {



 Event event = createEvent();



 events.add(event);



 circularList.insert(event);



 }



 }







 private static void updateEvents() {



 int numEvents = getNumberOfEvents("update");



 for (int i = 0; i < numEvents; i++) {



 System.out.println("\nEnter Title of Event to Update:");



 String title = scanner.nextLine();



 



 Event eventToUpdate = findEvent(title);



 if (eventToUpdate != null) {



 updateEventDetails(eventToUpdate);



 } else {



 System.out.println("Event not found. Update canceled.");



 }



 }



 }







 private static void deleteEvents() {



 int numEvents = getNumberOfEvents("delete");



 for (int i = 0; i < numEvents; i++) {



 System.out.println("\nEnter Title of Event to Delete:");



 String title = scanner.nextLine();



 Event eventToDelete = findEvent(title);



 if (eventToDelete != null) {



 events.remove(eventToDelete);



 System.out.println("Event deleted successfully!");



 } else {



 System.out.println("Event not found. Deletion canceled.");



 }



 }



 }







 private static void displayEvents() {



 circularList.display();



 }







 private static int getNumberOfEvents(String action) {



 System.out.print("Enter the number of events to " + action + ": ");



 return scanner.nextInt();



 }







 private static Event createEvent() {



 System.out.println("\nEnter Event Details:");



 System.out.print("Title: ");



 String title = scanner.nextLine();



 scanner.nextLine();



 System.out.println("Date: ");



 String date = scanner.nextLine();



 



 System.out.println("Time: ");



 String time = scanner.nextLine();



 System.out.println("Venue: ");



 String venue = scanner.nextLine();



 System.out.println("Eligibility: ");



 String eligibility = scanner.nextLine();



 System.out.println("Registration Fees: ");



 double registrationFees = scanner.nextDouble();



 scanner.nextLine(); // Consume newline







 return new Event(title, date, time, venue, eligibility, registrationFees);



 }







 private static void updateEventDetails(Event eventToUpdate) {



 System.out.print("New Date (Press Enter to skip): ");



 String date = scanner1.nextLine();



 if (!date.isEmpty()) {



 eventToUpdate.setDate(date);



 }



 System.out.print("New Time (Press Enter to skip): ");



 String time = scanner.nextLine();



 if (!time.isEmpty()) {



 eventToUpdate.setTime(time);



 }



 System.out.print("New Venue (Press Enter to skip): ");



 String venue = scanner.nextLine();



 if (!venue.isEmpty()) {



 eventToUpdate.setVenue(venue);



 }



 System.out.print("New Eligibility (Press Enter to skip): ");



 String eligibility = scanner.nextLine();



 if (!eligibility.isEmpty()) {



 eventToUpdate.setEligibility(eligibility);



 }



 System.out.print("New Registration Fees (Press Enter to skip): ");



 String regFeesStr = scanner.nextLine();



 if (!regFeesStr.isEmpty()) {



 try {



 double registrationFees = Double.parseDouble(regFeesStr);



 eventToUpdate.setRegistrationFees(registrationFees);



 } catch (NumberFormatException e) {



 System.out.println("Invalid input for registration fees. Update canceled.");



 }



 }



 System.out.println("Event updated successfully!");



 }







 private static Event findEvent(String title) {



 for (Event event : events) {



 if (event.getTitle().equalsIgnoreCase(title)) {



 return event;



 }



 }



 return null;



 }



}











public class studentregi {







 private static Hashtable<Integer, Student> studentProfiles = new Hashtable<>();







 private static Hashtable<Integer, Alumni> alumniProfiles = new Hashtable<>();







 private static int nextStudentId = 1000;







 private static int nextAlumniId = 2000;



 



 private ArrayList<String> registeredStudents;



 private static ArrayList<Event> events = new ArrayList<>();







 static Scanner scanner=new Scanner(System.in);







 public static void main(String[] args) {







 Scanner scanner = new Scanner(System.in);







 int choice;















 do {







 System.out.println("Menu:");







 System.out.println("1. Student Section");







 System.out.println("2. Alumni Section");







 System.out.println("3. Display Students");







 System.out.println("4. Display Alumni");







 System.out.println("5. Connect with Alumni");







 System.out.println("6. Initiate Contact with Alumni");



 



 System.out.println("7. FAQ");







 System.out.println("8. Exit");







 System.out.println("Enter your choice:");















 choice = scanner.nextInt();















 switch (choice) {







 case 1:







 registerStudent(scanner);







 break;







 case 2:







 registerAlumni(scanner);







 break;







 case 3:







 displayAllStudents();







 break;







 case 4:







 displayAllAlumni();







 break;







 case 5:







 connectWithAlumni(scanner);







 break;







 case 6:







 initiateContactWithAlumni(scanner);







 break;







 case 7:



 {



 askque q1=new askque();



 q1.main1();







 break;



 }



 case 8:



 



	System.out.println("Exiting...");







	break;







 default:







 System.out.println("Invalid choice. Please enter a valid option.");







 }







 } while (choice != 8);















 scanner.close();







 }



 public void registerStudent(String studentName) {



 registeredStudents.add(studentName);



 }



 



 private static Event findEvent(String title) {



 for (Event event : events) {



 if (event.getTitle().equalsIgnoreCase(title)) {



 return event;



 }



 }



 return null;



 }



 private static void registerStudentForEvent() {







 System.out.println("\nEnter the Title of the Event to register for:");







 String eventTitle = scanner.nextLine();







 Event event = findEvent(eventTitle);







 if (event != null) {







 System.out.println("Enter Student Name:");







 String studentName = scanner.nextLine();



 



 studentregi s1=new studentregi();







 s1.registerStudent(studentName);







 System.out.println(studentName + " has been successfully registered for the event: " + eventTitle);







 } else {







 System.out.println("Event not found.");







 }







 }







 private static void registerStudent(Scanner scanner) {

	while (true) {

	System.out.println("Select an option:");

	System.out.println("1. New Registration");

	System.out.println("2. Register for an event");

	System.out.println("3. Exit from student section");

	System.out.print("Enter your choice: ");

	

	int choice = scanner.nextInt();

	scanner.nextLine(); // Consume newline character after reading choice



	switch (choice) {

	case 1:

	registerNewStudent(scanner);

	break;

	case 2:

	registerForEvent(scanner);

	break;

	case 3:

	return; // Exit from the method if the user chooses to exit

	default:

	System.out.println("Invalid choice. Please try again.");

	break;

	}

	}

	}





	private static void registerForEvent(Scanner scanner) {



	// Implement your logic for event registration here



	System.out.println("You chose to register for an event.");



	}



	private static void registerNewStudent(Scanner scanner) {







	System.out.println("Enter student information:");







	System.out.print("Name: ");



	String name = scanner.nextLine();



	scanner.nextLine();







	System.out.print("Age: ");



	int age = scanner.nextInt();



	scanner.nextLine(); // Consume newline character after reading age







	System.out.print("Address: ");



	String address = scanner.nextLine();







	System.out.print("Email: ");



	String email = scanner.nextLine();







	System.out.print("Phone Number: ");



	String phoneNumber = scanner.nextLine();







	System.out.print("Major: ");



	String major = scanner.nextLine();







	System.out.print("Degree: ");



	String degree = scanner.nextLine();







	System.out.print("Number of courses: ");



	int numCourses = scanner.nextInt();



	scanner.nextLine(); // Consume newline character after reading numCourses







	String[] courses = new String[numCourses];



	for (int i = 0; i < numCourses; i++) {



	System.out.print("Enter course " + (i + 1) + ": ");



	courses[i] = scanner.nextLine();



	}







	System.out.print("Number of certificates: ");



	int numCertificates = scanner.nextInt();



	scanner.nextLine(); // Consume newline character after reading numCertificates







	String[] certificates = new String[numCertificates];



	for (int i = 0; i < numCertificates; i++) {



	System.out.print("Enter certificate " + (i + 1) + ": ");



	certificates[i] = scanner.nextLine();



	}







	System.out.print("Number of skills: ");



	int numSkills = scanner.nextInt();



	scanner.nextLine(); // Consume newline character after reading numSkills







	String[] skills = new String[numSkills];



	for (int i = 0; i < numSkills; i++) {



	System.out.print("Enter skill " + (i + 1) + ": ");



	skills[i] = scanner.nextLine();



	}







	Student student = new Student(name, age, address, email, phoneNumber, major, degree, courses, certificates, skills);



	studentProfiles.put(nextStudentId++, student);







	System.out.println("Student registered successfully!");



	



	}



 private static void registerAlumni(Scanner scanner) {



	while (true) {



	System.out.println("Menu:");



	System.out.println("1. Registration");



	System.out.println("2. Event Management");



	System.out.println("3. Exit");



	System.out.print("Enter your choice: ");







	int choice = scanner.nextInt();



	scanner.nextLine(); // Consume newline







	switch (choice) {



	case 1:



	registerAlumniDetails(scanner);



	



	break;



	case 2:



	{



	EventManager Manager = new EventManager();



	Manager.menu();



	break;



	}



	case 3:



	System.out.println("Exiting alumni registration and event management.");



	return;



	default:



	System.out.println("Invalid choice. Please enter a valid option.");



	}



	}



	}







	private static void registerAlumniDetails(Scanner scanner) {



	System.out.println("Enter alumni information:");



	System.out.print("Name: ");



	String name = scanner.nextLine();



	System.out.print("Graduation Year: ");



	int graduationYear = scanner.nextInt();



	scanner.nextLine(); // Consume newline character



	String email;



	do {



	System.out.print("Email: ");



	email = scanner.nextLine();



	if (!isValidEmail(email)) {



	System.out.println("Invalid email format. Please enter a valid Gmail address.");



	}



	} while (!isValidEmail(email));



	System.out.print("Contact Number: ");



	String contact = scanner.nextLine();



	while (!isValidContact(contact)) {



	System.out.println("Invalid contact number format. Please enter a valid 10-digit number.");



	System.out.print("Contact Number: ");



	contact = scanner.nextLine();



	}



	System.out.print("Company Name: ");



	String companyName = scanner.nextLine();



	System.out.print("Occupation: ");



	String occupation = scanner.nextLine();



	System.out.print("Experience Years: ");



	int experienceYears = scanner.nextInt();



	scanner.nextLine(); // Consume newline character



	List<String> projects = new ArrayList<>();



	System.out.println("Enter projects done (one per line, type 'done' to finish):");



	while (true) {



	String project = scanner.nextLine();



	if (project.equalsIgnoreCase("done")) {



	break;



	}



	projects.add(project);



	}



	List<String> achievements = new ArrayList<>();



	System.out.println("Enter achievements (one per line, type 'done' to finish):");



	while (true) {



	String achievement = scanner.nextLine();



	if (achievement.equalsIgnoreCase("done")) {



	break;



	}



	achievements.add(achievement);



	}



	Alumni alumni = new Alumni(name, graduationYear, email, companyName, occupation, experienceYears, projects, achievements, contact);



	alumniProfiles.put(nextAlumniId++, alumni);



	System.out.println("Alumni registered successfully!");



	}







	







 private static void displayAllStudents() {







 System.out.println("All Registered Students:");







 for (Integer studentId : studentProfiles.keySet()) {







 Student student = studentProfiles.get(studentId);







 System.out.println("Student ID: " + studentId);







 System.out.println("Name: " + student.getName());







 System.out.println("Age: " + student.getAge());







 System.out.println("Address: " + student.getAddress());







 System.out.println("Email: " + student.getEmail());







 System.out.println("Phone Number: " + student.getPhoneNumber());







 System.out.println("Major: " + student.getMajor());







 System.out.println("Degree: " + student.getDegree());







 System.out.println("Courses:");







 for (String course : student.getCourses()) {







 System.out.println("- " + course);







 }







 System.out.println("Certificates:");







 for (String certificate : student.getCertificates()) {







 System.out.println("- " + certificate);







 }







 System.out.println("Skills:");







 for (String skill : student.getSkills()) {







 System.out.println("- " + skill);







 }







 System.out.println();







 }







 }















 private static void displayAllAlumni() {







 System.out.println("All Registered Alumni:");







 for (Integer alumniId : alumniProfiles.keySet()) {







 Alumni alumni = alumniProfiles.get(alumniId);







 System.out.println("Alumni ID: " + alumniId);







 System.out.println("Name: " + alumni.getName());







 System.out.println("Graduation Year: " + alumni.getGraduationYear());







 System.out.println("Email: " + alumni.getEmail());







 System.out.println("Contact Number: " + alumni.getContact());







 System.out.println("Company Name: " + alumni.getCompanyName());







 System.out.println("Occupation: " + alumni.getOccupation());







 System.out.println("Experience Years: " + alumni.getExperienceYears());







 System.out.println("Projects:");







 for (String project : alumni.getProjects()) {







 System.out.println("- " + project);







 }







 System.out.println("Achievements:");







 for (String achievement : alumni.getAchievements()) {







 System.out.println("- " + achievement);







 }







 System.out.println();







 }







 }















 private static void connectWithAlumni(Scanner scanner) {







 scanner.nextLine(); // Consume newline character







 System.out.println("Enter criteria to search for alumni:");







 System.out.print("Graduation Year: ");







 int graduationYear = scanner.nextInt();







 scanner.nextLine(); // Consume newline character







 System.out.print("Occupation: ");







 String occupation = scanner.nextLine();















 // Search alumni based on criteria







 List<Alumni> matchingAlumni = searchAlumni(graduationYear, occupation);















 if (matchingAlumni.isEmpty()) {







 System.out.println("No alumni found matching the criteria.");







 } else {







 System.out.println("Matching Alumni Profiles:");







 for (Alumni alumni : matchingAlumni) {







 System.out.println("Alumni ID: " + findAlumniId(alumni));







 System.out.println("Name: " + alumni.getName());







 System.out.println("Graduation Year: " + alumni.getGraduationYear());







 System.out.println("Occupation: " + alumni.getOccupation());







 System.out.println("Experience Years: " + alumni.getExperienceYears());







 System.out.println();







 }







 }







 }















 private static void initiateContactWithAlumni(Scanner scanner) {







 System.out.print("Enter Alumni ID to initiate contact: ");







 int alumniId = scanner.nextInt();







 scanner.nextLine(); // Consume newline character















 if (alumniProfiles.containsKey(alumniId)) {







 Alumni alumni = alumniProfiles.get(alumniId);







 System.out.println("Initiating contact with Alumni: " + alumni.getName());







 System.out.println("Contact Information: " + alumni.getContact());







 // You can implement further actions here, such as sending a message to the alumni







 } else {







 System.out.println("Alumni with ID " + alumniId + " not found.");







 }







 }















 private static List<Alumni> searchAlumni(int graduationYear, String occupation) {







 List<Alumni> matchingAlumni = new ArrayList<>();







 for (Alumni alumni : alumniProfiles.values()) {







 if (alumni.getGraduationYear() == graduationYear && alumni.getOccupation().equalsIgnoreCase(occupation)) {







 matchingAlumni.add(alumni);







 }







 }







 return matchingAlumni;







 }















 private static int findAlumniId(Alumni alumni) {







 for (Map.Entry<Integer, Alumni> entry : alumniProfiles.entrySet()) {







 if (entry.getValue().equals(alumni)) {







 return entry.getKey();







 }







 }







 return -1; // Not found







 }















 private static boolean isValidEmail(String email) {







 // Email validation regex pattern for allowing alphanumeric characters before "@" and ending with ".com" or ".in"







 String emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|in)$";







 return email.matches(emailPattern);







 }















 private static boolean isValidContact(String contact) {







 // Contact number validation regex pattern for exactly 10 digits







 String contactPattern = "^\\d{10}$";







 return contact.matches(contactPattern);







 }







}







class Student {







 private String name;







 private int age;







 private String address;







 private String email;







 private String phoneNumber;







 private String major;







 private String degree;







 private String[] courses;







 private String[] certificates;







 private String[] skills;















 public Student(String name, int age, String address, String email, String phoneNumber, String major, String degree, String[] courses, String[] certificates, String[] skills) {







 this.name = name;







 this.age = age;







 this.address = address;







 this.email = email;







 this.phoneNumber = phoneNumber;







 this.major = major;







 this.degree = degree;







 this.courses = courses;







 this.certificates = certificates;







 this.skills = skills;







 }



 public String getName() {







 return name;







 }



public int getAge() {







 return age;







 }



 public String getAddress() {







 return address;







 }



 public String getEmail() {







 return email;







 }



 public String getPhoneNumber() {







 return phoneNumber;







 }



 public String getMajor() {







 return major;







 }



 public String getDegree() {







 return degree;







 }



 public String[] getCourses() {







 return courses;







 }



 public String[] getCertificates() {







 return certificates;







 }



 public String[] getSkills() {







 return skills;







 }







}



class Alumni {







 private String name;







 private int graduationYear;







 private String email;







 private String companyName;







 private String occupation;







 private int experienceYears;







 private List<String> projects;







 private List<String> achievements;







 private String contact;



 public Alumni(String name, int graduationYear, String email, String companyName, String occupation, int experienceYears, List<String> projects, List<String> achievements, String contact) {







 this.name = name;







 this.graduationYear = graduationYear;







 this.email = email;







 this.companyName = companyName;







 this.occupation = occupation;







 this.experienceYears = experienceYears;







 this.projects = projects;







 this.achievements = achievements;







 this.contact = contact;







 }



 public String getName() {







 return name;







 }



 public int getGraduationYear() {



 return graduationYear;



 }



 public String getEmail() {



 return email;



 }



 public String getCompanyName() {



 return companyName;



 }



 public String getOccupation() {



 return occupation;



 }



 public int getExperienceYears() {



 return experienceYears;



 }



 public List<String> getProjects() {







 return projects;







 }



 public List<String> getAchievements() {







 return achievements;







 }







 public String getContact() {







 return contact;







 }







}