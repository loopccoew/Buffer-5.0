package buffer;
//ganpati bappa morya
import java.util.*;
import java.time.LocalTime;
class Laboratory {
static void generateLabParameters() {
       Random random = new Random();
       int glucoseLevel = random.nextInt(200); // Random glucose level
       int cholesterolLevel = random.nextInt(300); // Random cholesterol level
       int hemoglobinLevel = random.nextInt(20); // Random hemoglobin level
       int whiteBloodCellCount = random.nextInt(15000); // Random white bloocell count
       double bodyTemperature = 35.0 + random.nextDouble() * (42.0 - 35.0); // Random body temperature
       System.out.println("Glucose Level: " + glucoseLevel + " mg/dL");
       System.out.println("Cholesterol Level: " + cholesterolLevel + " mg/dL");
       System.out.println("Hemoglobin Level: " + hemoglobinLevel + " g/dL");
       System.out.println("White Blood Cell Count: " + whiteBloodCellCount + " cells/mcL");
       System.out.println("Body Temperature: " + String.format("%.2f", bodyTemperature) + " °C");
}
}
abstract class Admin {
   abstract void replace(HashMap<Integer, Doctor> details);
   abstract void accept(HashMap<Integer, Doctor> details);
}
class Patient {
	 static int idCounter = 1; // Counter to generate unique patient IDs
	    int id;
	    String name;
	    int age, section;
	    static String consultancysections[] = {"orthopedic", "gynaecologist", "ENT specialist", "Emergency", "cardiologist"};
	    static String doctorsections[] = {"orthopedic", "gynaecologist", "ENT specialist", "surgeon", "cardiologist"};
	    boolean appointmentBooked;
	    boolean labReportRequested;
	    boolean isOccupied; // New attribute to indicate bed occupancy
	    boolean isemergency;
	    LocalTime Slot;
   Patient() {
       id = idCounter++; // Assigning a unique ID to each patient
   }
   Patient(String name, int age, int section, boolean isOccupied,boolean isemergency,LocalTime Slot) {
   	  this(); // Call the default constructor to assign ID
         this.name = name;
         this.age = age;
         this.section = section;
         this.appointmentBooked = false;
         this.labReportRequested = false;
         this.isOccupied = isOccupied; // Initialize bed occupancy
         this.isemergency=isemergency;
         this.Slot=Slot;
   }
   public static Comparator<Patient> BedPriorityComparator = new Comparator<Patient>() {
   	  public int compare(Patient p1, Patient p2) {
             if (p1.isOccupied && !p2.isOccupied) {
                 return 1; // p2 has higher priority (empty bed)
             } else if (!p1.isOccupied && p2.isOccupied) {
                 return -1; // p1 has higher priority (empty bed)
             } else {
                 return 0; // Equal priority
             }
         }
     };
   public static Comparator<Patient> emergencyComparator = new Comparator<Patient>() {
       @Override
       public int compare(Patient p3, Patient p4) {
       	 if (p3.isemergency && !p4.isemergency) {
                return 1; // p2 has higher priority (empty bed)
            } else if (!p3.isemergency && p4.isemergency) {
                return -1; // p1 has higher priority (empty bed)
            } else {
                return 0; // Equal priority
            }
        }
    };
   public String toString() {
       return "\nID: " + id + "\nName: " + name + "\nAge: " + age + "\nSection for consultancy: " + consultancysections[section];
   }
   public void setSlot(LocalTime slot) {
    this.Slot = slot;
   }
   public LocalTime getSlot() {
      return Slot;
   }
 
   public static void viewDoctors(HashMap<Integer, Doctor> doctors, int section) {
   	
       System.out.println("Available Doctors in section: " + doctorsections[section]);
       for (Map.Entry<Integer, Doctor> entry : doctors.entrySet()) {
           Doctor doctor = entry.getValue();
           if (doctor.section == section) {
               System.out.println("ID: " + entry.getKey() + ", " + doctor);
           }
       }
   }
}
class Doctor extends Admin {
	  String name, experience;
	    int section;
	    int id;
	    int intime;
	    int outtime;
	    String speciality[] = {"orthopedic", "gynaecologist", "ENT specialist", "surgeon", "cardiologist"};
	    Doctor() {
	    }
	    Doctor(String name, String experience, String speciality, int section, int intime, int outtime) {
	        this.name = name;
	        this.experience = experience;
	       // this.speciality = speciality;
	        this.section = section;
	        this.intime=intime;
	        this.outtime=outtime;
	    }
	    
	    String[] addDoctor() {
	        String add[] = new String[3];
	        Scanner s = new Scanner(System.in);
	        System.out.println("Enter name of doctor to be added");
	        add[0] = s.nextLine();
	        System.out.println("Enter experience of doctor ");
	        add[1] = s.nextLine();
	        System.out.println("Enter Speciality of doctor");
	        add[2] = s.nextLine();
	        return add;
	    }
    void replace(HashMap<Integer, Doctor> details) {
       // Implement replacement logic
   }
 @Override
   void accept(HashMap<Integer, Doctor> details) {
       // Implement addition logic
   }
   public String toString() {
       return "Name: " + name + ", Experience: " + experience + ", Speciality: " + speciality[section-1]+"\nTimings : "+
               intime+" to "+outtime+" hrs";
   }
   public int getsection() {
       return section;
   }
   public int getId() {
       return id;
   }
   public int getouttime() {
       return outtime;
   }
   public int getintime() {
       return intime;
   }
}
class Nurse{
	String name;
	int ward;
	String shift;
	int id;
	Nurse(String name,int ward,String shift){
		this.name=name;
		this.ward=ward;
		this.shift=shift;
		
	}
void display() {
System.out.println("Name of the nurse : "+name);
System.out.println("ward no. assigned : "+ward);
System.out.println("Shift assigned : "+shift);
}
public String getShift() {
       return shift;
   }
   public void setShift(String shift) {
       this.shift = shift;
   }
   public int getWardNumber() {
       return ward;
   }
   public String toString() {
       return "Name: " + name + "\nAssigned Ward Number: " +ward + "\nAssigned Shift: " + shift;
   }
   public int getId() {
       return id;
   }
}
public class BufferProject {
public static void main(String args[]) {
int i;
int id=0;
int wardno = 0;
String newshift="c";
int nursechoice=0;
int ch=0;
int ptid=0;
int emptybed=10;
int occupiedbed=0;
   LocalTime startTime1 = LocalTime.of(10,0);
   LocalTime endTime1 = LocalTime.of(14,0);
   LocalTime startTime2 = LocalTime.of(16,0);
   LocalTime endTime2 = LocalTime.of(20,0);
   //gynac
   LocalTime gynacstartTime1 = LocalTime.of(10,0);
   LocalTime gynacendTime1 = LocalTime.of(14,0);
   LocalTime gynacstartTime2 = LocalTime.of(10,0);
   LocalTime gynacendTime2 = LocalTime.of(14,0);
   //ent
   LocalTime entstartTime1 = LocalTime.of(10,0);
   LocalTime  entendTime1 = LocalTime.of(14,0);
   LocalTime entstartTime2 = LocalTime.of(10,0);
   LocalTime entendTime2 = LocalTime.of(14,0);
    //emergency
   LocalTime emstartTime1 = LocalTime.of(10,0);
   LocalTime emendTime1 = LocalTime.of(14,0); 
   LocalTime emstartTime2 = LocalTime.of(10,0);
   LocalTime emendTime2 = LocalTime.of(14,0);
   //cardio
   LocalTime cardstartTime1 = LocalTime.of(10,0);
   LocalTime cardendTime1 = LocalTime.of(14,0);
   LocalTime cardstartTime2 = LocalTime.of(10,0);
   LocalTime cardendTime2 = LocalTime.of(14,0);
LocalTime Slot=LocalTime.of(0,0);            
Scanner sc=new Scanner(System.in);
ArrayList<Integer> dischargeid=new ArrayList<Integer>();
ArrayList<Integer> admitid=new ArrayList<Integer>();
HashMap<Integer,Nurse> nurse=new HashMap<Integer,Nurse>();
nurse.put(101,new Nurse("Miss Shreya Gaikwad",1,"morning"));
nurse.put(102,new Nurse("Miss anjali shinde",2,"morning"));
nurse.put(103,new Nurse("Miss mayuri deshpande",3,"morning"));
nurse.put(104,new Nurse("Miss anita kelkar",1,"night"));
nurse.put(105,new Nurse("Miss sakshi burse",2,"night"));
nurse.put(106,new Nurse("Miss bhargavi dange ",3,"night"));
HashMap<Integer, Doctor> details = new HashMap<Integer, Doctor>();
details.put(100, new Doctor("Dr.Sanjay Gaikwad", "18yrs", "Surgeon", 1,10,14));
details.put(200, new Doctor("Dr.Rama Singh", "12yrs", "Gynaecologist", 2,10,14));
details.put(300, new Doctor("Dr.Shilpa Ranade", "10yrs", "ENT", 3,10,14));
details.put(400, new Doctor("Dr.Rakesh Nikam", "8yrs", "orthopedic", 4,10,14));
details.put(500, new Doctor("Dr.Vaishali Jagdale", "7yrs", "cardiologist", 5,10,14));
details.put(600, new Doctor("Dr.Komal Lodha", "15yrs", "surgeon", 1,16,20));
details.put(700, new Doctor("Dr.Pallavi Dange", "20yrs", "Gynaecologist", 2,16,20));
details.put(800, new Doctor("Dr.Avinash Chaudhari", "22yrs", "ENT", 3,16,20));
details.put(900, new Doctor("Dr.Amit Patil", "27yrs", "orthopedic", 4,16,20));
details.put(1000, new Doctor("Dr.Madhura Deshpande", "10yrs", "Cardiologist", 5,16,20));
HashMap<Integer,Patient> patient=new HashMap<Integer,Patient>();
PriorityQueue<Patient> orthoappointments = new PriorityQueue<Patient>(Patient.emergencyComparator);
PriorityQueue<Patient> gynacappointments = new PriorityQueue<>(Patient.emergencyComparator);
PriorityQueue<Patient> entappointments = new PriorityQueue<>(Patient.emergencyComparator);
PriorityQueue<Patient> surgeonappointments = new PriorityQueue<>(Patient.emergencyComparator);
PriorityQueue<Patient> cardioappointments = new PriorityQueue<>(Patient.emergencyComparator);
PriorityQueue<Patient> tempQueue = new PriorityQueue<>(Patient.emergencyComparator);
PriorityQueue<Patient> bedQueue = new PriorityQueue<>(Patient.BedPriorityComparator);
System.err.println("\t\t➕  WELCOME TO PRIMECARE MULTISPECIALITY HOSPITAL  ➕");
   do {
System.out.println("-------------------------------------------------------------------------------");
System.out.println("\nEnter user index :");
System.out.println("\n1.Doctor\n2.patient\n3.Nurse\n4.Laboratory\n5.Admin\n6.To exit");
try {
ch=sc.nextInt();
}catch(InputMismatchException e) {
	sc.nextLine();
	System.out.println("Please Enter valid index !");
	ch=sc.nextInt();
}
String dummy =sc.nextLine();
int dch;
switch(ch){
case 1 :
	int did=0;
	System.out.print("Enter your ID to login : ");
	try {
    did=sc.nextInt();
	}catch(InputMismatchException e) {
		sc.nextLine();
		System.out.print("Enter valid ID");
		did=sc.nextInt();
	}
	if(details.containsKey(did)) {
		System.out.println("Logged in Successfully !");
		Doctor obj=details.get(did);
		do {
		System.out.println("\n1.View Appointments\n2.View patient reports\n3.To admit patient\n4.To discharge patient\n5.To exit");
		System.out.print("\nEnter choice :");
		try {
	    dch=sc.nextInt();
		}catch(InputMismatchException e) {
			sc.next();
			System.out.print("Enter valid choice !");
			dch=sc.nextInt();
		}
		switch(dch) {
		case 1:
			
			int sec=obj.getsection();
			System.out.println("\n------------Today's Appointments------------");
			
			if(sec==1) {
				 for(Patient element : orthoappointments)
				 {
					 if(element.isemergency==true) {
		       		System.out.println("\nID :"+element.id);
		       		System.out.println("name :"+element.name);
		       		System.err.println("Emergency case!");
		       		}
				 }
				 for(Patient element : orthoappointments)
				 {
					 if(element.isemergency==false) {
						 System.out.println("\nID :"+element.id);
				       	 System.out.println("name :"+element.name);
				       	 System.out.println("Slot :"+element.getSlot());
					 }
				 }
			}
			else if (sec==2){
				 for(Patient element : gynacappointments)
				 {
					 if(element.isemergency==true) {
		       		System.out.println("ID :"+element.id);
		       		System.out.println("name :"+element.name);
		       		System.out.println("Slot :"+element.getSlot());
					 }
					
		       	 }
				 for(Patient element : gynacappointments)
				 {
					 if(element.isemergency==false) {
						 System.out.println("\nID :"+element.id);
				       	 System.out.println("name :"+element.name);
				       	 System.out.println("Slot :"+element.getSlot());
					 }
				 }
			}
			else if(sec==3) {
				 for(Patient element : entappointments)
				 {
					 if(element.isemergency==true) {
		       		System.out.println("ID :"+element.id);
		       		System.out.println("name :"+element.name);
		       		System.out.println("Slot :"+element.getSlot());
					 }
		       	 }
				 for(Patient element : entappointments)
				 {
					 if(element.isemergency==false) {
						 System.out.println("\nID :"+element.id);
				       	 System.out.println("name :"+element.name);
				       	 System.out.println("Slot :"+element.getSlot());
					 }
				 }
			}else if (sec==4) {
				 for(Patient element : surgeonappointments)
				 {
					 if(element.isemergency==true) {
		       		System.out.println("ID :"+element.id);
		       		System.out.println("name :"+element.name);
		       		System.out.println("Slot :"+element.getSlot());
					 }
		       	 }
				 for(Patient element : surgeonappointments)
				 {
					 if(element.isemergency==false) {
						 System.out.println("\nID :"+element.id);
				       	 System.out.println("name :"+element.name);
				       	 System.out.println("Slot :"+element.getSlot());
					 }
				 }
			}
			else if(sec==5) {
				 for(Patient element : cardioappointments)
				 {
					 if(element.isemergency==true) {
		       		System.out.println("ID :"+element.id);
		       		System.out.println("name :"+element.name);
		       		System.out.println("Slot :"+element.getSlot());
					 }
		       	 }
				 for(Patient element : cardioappointments)
				 {
					 if(element.isemergency==false) {
						 System.out.println("\nID :"+element.id);
				       	 System.out.println("name :"+element.name);
				       	 System.out.println("Slot :"+element.getSlot());
				       	
					 }
				 }
			}
			else {
				System.out.println("wrong section !");
			}
				
			break;
	 
       case 2:
       	System.out.print("Enter patient ID : ");
       	int rid=sc.nextInt();
       	if(patient.containsKey(rid)) {
       		System.out.println("📌 Reports :\n-----------------------------");
       		Laboratory.generateLabParameters();
       		
       	}
       	else {
       		System.out.println("Patient with ID "+rid+" not found !");
       	}
	break;
       case 3:
       	System.out.print("Enter the id of patient who needs to be admitted: ");
           int adid=sc.nextInt();
           admitid.add(adid);
  break;
       case 4:
       	System.out.print("Enter the id of patient who needs to be discharged: ");
       	int dischid=sc.nextInt();
       	dischargeid.add(dischid);
  break;
}
}while(dch<5);
} else {
	System.out.println("ID not found.\nIf you forgot your ID, Recheck your details with admin!");
}
            break;
case 2:
String name="c";
int age=0;
int section=0;
String speciality[] = {"orthopedic", "gynaecologist", "ENT specialist", "surgeon", "cardiologist"};
boolean isemergency=false;
String sections[]= {"orthopedic","gynaecologist","ENT specialist","Emergency","cardiologist"};
try {
System.out.print("\nEnter your name : ");
name=sc.nextLine();
System.out.print("Enter your age : ");
age=sc.nextInt();
System.out.println("\n1.orthopedic\n2.gynaecologist\n3.ENT Specialist\n4.emergency\n5.cardiologist");
System.out.print("Enter index of section : ");
section=sc.nextInt()-1;
}catch(InputMismatchException e) {
	 sc.next();
	 System.out.println("Enter valid data !");
}
if(section==3) {
isemergency=true;
}
sc.nextLine();
boolean isoccupied=true;
patient.put(++ptid,new Patient(name,age,(section),isoccupied,isemergency,Slot));
System.out.println("\nYOUR UNIQUE PATIENT ID IS :"+ptid);
System.out.println("\n1. Book Appointment");
       System.out.println("2. Request Lab Reports\n3. To exit");
       System.out.print("Enter your choice:");     
       int option = sc.nextInt();
        switch (option) {
           case 1:
           	  System.out.print("Enter your patient ID :");
                 int patientId = sc.nextInt();
                 if(patient.containsKey(patientId)) {
                 	Patient ptobj=patient.get(patientId);
                 System.out.println("\n1.orthopedic\n2.gynaecologist\n3.ENT Specialist\n4.Surgeon\n5.cardiologist");
                 System.out.print("Enter section number of doctor: ");
                  int appsection = sc.nextInt(); // Corrected section number
                  System.out.println("\nAVAILABLE DOCTORS IN THE SECTION :-"+speciality[appsection-1]);
                  System.out.println("--------------------------------------------------");
                  for (Map.Entry<Integer, Doctor> entry : details.entrySet()) {
                 	    Doctor docobj = entry.getValue();
                 	   
                 	    if (docobj.getsection() == appsection) {
                 	        System.out.println("\nDoctor ID: " + entry.getKey());
                 	        System.out.println(docobj);
                 	    }
                  
                 	}
                  System.out.print("\nEnter Id of doctor who's appointment is to be booked : ");
            	    int appid=sc.nextInt();
                  for (Map.Entry<Integer, Doctor> entry : details.entrySet()) {
                 	 Doctor docobj = entry.getValue();
                 	
           	    if(appid==entry.getKey()) {
           	    	if(ptobj.isemergency==false) {
           	    	if(docobj.section==1) {
           	    	orthoappointments.add(ptobj);
           	    	for (Map.Entry<Integer, Doctor> entry1 : details.entrySet()) {
             		     // Corrected
             		    if (appid == entry1.getKey()) {
             		      Doctor docobj1 = entry1.getValue();
             		      LocalTime intime=LocalTime.of(docobj1.getintime(),0);
             		      LocalTime outtime=LocalTime.of(docobj1.getouttime(),0);
             		      if(LocalTime.of(10,0)==intime) {
             		       while(startTime1.isBefore(endTime1)) {
             		    	 System.out.println("Appointment booked successfully for slot timing : "+startTime1+" ✅");
             		    	  ptobj.setSlot(startTime1);
             		    	 startTime1=startTime1.plusMinutes(15);
             		    	 break;
             		       }
             		      break;
             		      }
             		      else if(LocalTime.of(16, 0)==intime) {
                   		       while(startTime2.isBefore(endTime2)) {
                   		    	   System.out.println("Appointment booked successfully for slot timing : "+startTime2+" ✅");
                   		    	   ptobj.setSlot(startTime2);
                   		    	   startTime2=startTime2.plusMinutes(15);
                   		    	   break;
             		   }
                   		       break;
             	  }
             		    }
             		}
           	    }
           	   
           	    	else if(docobj.section==2) {
           	    	gynacappointments.add(ptobj);
           	    	for (Map.Entry<Integer, Doctor> entry1 : details.entrySet()) {
              		     // Corrected
              		    if (appid == entry1.getKey()) {
              		      Doctor docobj1 = entry1.getValue();
              		      LocalTime intime=LocalTime.of(docobj1.getintime(),0);
              		      LocalTime outtime=LocalTime.of(docobj1.getouttime(),0);
              		      if(LocalTime.of(10,0)==intime) {
              		       while(gynacstartTime1.isBefore(gynacendTime1)) {
              		    	 System.out.println("Appointment booked successfully for slot timing : "+gynacstartTime1+" ✅");
              		    	  ptobj.setSlot(gynacstartTime1);
              		    	 gynacstartTime1=gynacstartTime1.plusMinutes(15);
              		    	 break;
              		       }
              		      break;
              		      }
              		      else if(LocalTime.of(16, 0)==intime) {
                    		       while(gynacstartTime2.isBefore(gynacendTime2)) {
                    		    	   System.out.println("Appointment booked successfully for slot timing : "+gynacstartTime2+" ✅");
                    		    	   ptobj.setSlot(gynacstartTime2);
                    		    	   gynacstartTime2=gynacstartTime2.plusMinutes(15);
                    		    	   break;
              		   }
                    		       break;
              	  }
              		    }
              		}
                    }
           	    	
           	    	else if(docobj.section==3) {
           	    	entappointments.add(ptobj);
           	    	for (Map.Entry<Integer, Doctor> entry1 : details.entrySet()) {
             		     // Corrected
             		    if (appid == entry1.getKey()) {
             		      Doctor docobj1 = entry1.getValue();
             		      LocalTime intime=LocalTime.of(docobj1.getintime(),0);
             		      LocalTime outtime=LocalTime.of(docobj1.getouttime(),0);
             		      if(LocalTime.of(10,0)==intime) {
             		       while(entstartTime1.isBefore(entendTime1)) {
             		    	 System.out.println("Appointment booked successfully for slot timing : "+entstartTime1+" ✅");
             		    	  ptobj.setSlot(entstartTime1);
             		    	 entstartTime1=entstartTime1.plusMinutes(15);
             		    	 break;
             		       }
             		      break;
             		      }
             		      else if(LocalTime.of(16, 0)==intime) {
                   		       while(entstartTime2.isBefore(entendTime2)) {
                   		    	   System.out.println("Appointment booked successfully for slot timing : "+entstartTime2+" ✅");
                   		    	   ptobj.setSlot(entstartTime2);
                   		    	   entstartTime2=entstartTime2.plusMinutes(15);
                   		    	   break;
             		   }
                   		       break;
             	  }
             		    }
             		}
           	    	}else if(docobj.section==4) {
           	    		surgeonappointments.add(ptobj);
           	    		for (Map.Entry<Integer, Doctor> entry1 : details.entrySet()) {
                 		     // Corrected
                 		    if (appid == entry1.getKey()) {
                 		      Doctor docobj1 = entry1.getValue();
                 		      LocalTime intime=LocalTime.of(docobj1.getintime(),0);
                 		      LocalTime outtime=LocalTime.of(docobj1.getouttime(),0);
                 		      if(LocalTime.of(10,0)==intime) {
                 		       while(emstartTime1.isBefore(emendTime1)) {
                 		    	 System.out.println("Appointment booked successfully for slot timing : "+emstartTime1+" ✅");
                 		    	  ptobj.setSlot(emstartTime1);
                 		    	 emstartTime1=emstartTime1.plusMinutes(15);
                 		    	 break;
                 		       }
                 		      break;
                 		      }
                 		      else if(LocalTime.of(16, 0)==intime) {
                       		       while(emstartTime2.isBefore(cardendTime2)) {
                       		    	   System.out.println("Appointment booked successfully for slot timing : "+emstartTime2+" ✅");
                       		    	   ptobj.setSlot(emstartTime2);
                       		    	  emstartTime2=emstartTime2.plusMinutes(15);
                       		    	   break;
                 		   }
                       		       break;
                 	  }
                 		    }
                 		}
           	    	}
           	    	else {
           	    		cardioappointments.add(ptobj);
           	    		for (Map.Entry<Integer, Doctor> entry1 : details.entrySet()) {
                 		     // Corrected
                 		    if (appid == entry1.getKey()) {
                 		      Doctor docobj1 = entry1.getValue();
                 		      LocalTime intime=LocalTime.of(docobj1.getintime(),0);
                 		      LocalTime outtime=LocalTime.of(docobj1.getouttime(),0);
                 		      if(LocalTime.of(10,0)==intime) {
                 		       while(cardstartTime1.isBefore(cardendTime1)) {
                 		    	 System.out.println("Appointment booked successfully for slot timing : "+cardstartTime1+" ✅");
                 		    	  ptobj.setSlot(cardstartTime1);
                 		    	 cardstartTime1=cardstartTime1.plusMinutes(15);
                 		    	 break;
                 		       }
                 		      break;
                 		      }
                 		      else if(LocalTime.of(16, 0)==intime) {
                       		       while(cardstartTime2.isBefore(cardendTime2)) {
                       		    	   System.out.println("Appointment booked successfully for slot timing : "+cardstartTime2+" ✅");
                       		    	   ptobj.setSlot(cardstartTime2);
                       		    	   cardstartTime2=cardstartTime2.plusMinutes(15);
                       		    	   break;
                 		   }
                       		       break;
                 	  }
                 		    }
                 		}
           	    	}
           	    	}
           	    }
                  }
                if (ptobj.isemergency==true) {
               	    // Emergency case, give highest priority
               	   
               	    if (appsection == 1) {
               	    	orthoappointments.add(ptobj);
               	    	System.out.println("Emergency appointment booked !");
               	    	bedQueue.add(ptobj);
               	    } 
               	    else if (appsection == 2) {
              	    	gynacappointments.add(ptobj);
              	    	System.out.println("Emergency appointment booked !");
              	    	bedQueue.add(ptobj);
               	    } 
               	    else if (appsection == 3) {
              	    	entappointments.add(ptobj);
              	    	System.out.println("Emergency appointment booked !");
              	    	bedQueue.add(ptobj);
               	    } 
               	    else if (appsection == 4) {
              	    	surgeonappointments.add(ptobj);
              	    	System.out.println("Emergency appointment booked !");
              	    	bedQueue.add(ptobj);
               	    }
               	    else if (appsection == 5) {
               	        cardioappointments.add(ptobj);
               	        System.out.println("Emergency appointment booked !");
               	        bedQueue.add(ptobj);
               	    } else {
               	        // Handle invalid section number
               	        System.out.println("Invalid section number!");
               	        return; // Exit the method
               	    }
                }
                 }else {
               	  System.out.println("ID not found!\nRecheck your details with admin");
                 }
                 break;
           case 2:
             System.out.println("\nYOUR REPORTS:\n");
               Laboratory.generateLabParameters();
               break;
           case 3 :
           	break;
           default:
               System.out.println("Invalid option");
               break;
       }
break;
case 3:
	
do {
System.out.println("/nEnter\n1.To Change your shift\n2.To check current shift assignment:\n3.To exit");
nursechoice=sc.nextInt();
switch(nursechoice) {
case 1:
	System.out.print("Enter your ID : ");
id=sc.nextInt();
   if (nurse.containsKey(id)) {
   	Nurse nurseobj = nurse.get(id);
        wardno=nurseobj.getWardNumber();
   	 System.out.println("\n\nYour Current details : ");
      
   	 nurseobj.display();
           System.out.print("Enter new shift (morning/night):");
           newshift = sc.next();
           if(newshift.equalsIgnoreCase("night")||newshift.equalsIgnoreCase("morning")) {
           	nurseobj.setShift(newshift);
           }
           else {
           	System.out.print("Enter valid shift : ");
           	 newshift = sc.next();
           	 nurseobj.setShift(newshift);
           }
           System.out.println("\nShift updated successfully ");
           System.out.println("New shift : "+nurseobj.getShift());
   }else {
   	System.out.println("invalid ID ");
   }
   for (Nurse otherNurse : nurse.values()) {
       if (otherNurse.getWardNumber() == wardno && otherNurse.getId()!= id) {
      if(newshift.equals("morning")) {
   	   otherNurse.setShift("night");
      }
      else{
   	   otherNurse.setShift("morning");
      }
       }
   }
   break;
case 2:
	
   System.out.print("Enter your ID : ");
	 int ddid=sc.nextInt();
	
	  if (nurse.containsKey(ddid)) {
		  Nurse nurseobj2 = nurse.get(ddid);
	  System.out.println(nurseobj2);	
	  }
	
	  break;
}}while(nursechoice<3);
break;
case 4 :
System.out.println("To upload reports :");
System.out.print("Enter Id of patient : ");
int lid=sc.nextInt();
if(patient.containsKey(lid)) {
Laboratory.generateLabParameters();
}
break;
case 5:
Patient ward []=new Patient[10];
System.out.println("\n1.To generate consultancy bill\n2.To Admit\n3.To Discharge\n4.To view emergency ward patients\n"
		+ "5.To view doctor data\n6.To view nurse data\n7.To view patient data\n8.To Exit");
int x=sc.nextInt();
switch(x)
{
case 1:
boolean pay=false;
System.out.print("Enter ID: ");
int consult1=sc.nextInt();
for(int pt:admitid)
{
if(pt!=consult1)
{
System.out.println("Pay consultancy Charges ₹300");
if(pay==true)
{
System.out.println("Paid");
}
}
}
break;
case 2:
System.out.print("Patients to be admitted: ");
System.out.println(admitid);
System.out.print("Enter ID: ");
int admit1=sc.nextInt();
System.out.print("Enter bed number ");
int bedno=0;
try {
bedno=sc.nextInt();
}catch(InputMismatchException e) {
	System.out.print("Enter valid bed no.");
	sc.next();
	bedno=sc.nextInt();
}catch(ArrayIndexOutOfBoundsException e) {
	System.out.println("\nOops!!Something went wrong, Try again !");
	sc.next();
	bedno=sc.nextInt();
}
if(patient.containsKey(admit1))
{
Patient ptobj=patient.get(admit1);
ward[bedno]=ptobj;
occupiedbed++;
emptybed--;
}
System.out.println("Patient admmited succussfully!");
System.out.println("Occupied beds="+occupiedbed+"\nEmpty beds="+emptybed);
break;
case 3:
System.out.print("Patients to be discharged: ");
System.out.println(dischargeid);
System.out.print("Enter ID: ");
int disch1=sc.nextInt();
System.out.print("Enter bed number ");
try {
bedno=sc.nextInt();
}
catch(InputMismatchException e) {
	System.out.print("Enter valid bed no.");
	sc.next();
	bedno=sc.nextInt();
}catch(ArrayIndexOutOfBoundsException e) {
	System.out.println("\nOops!!Something went wrong, Try again !");
	sc.next();
	bedno=sc.nextInt();
}
if
(patient.containsKey(disch1))
{
	Patient ptobj=patient.get(disch1);
	ward[bedno]=null;
	if(occupiedbed<0)
	{
		occupiedbed=0;
		emptybed=10;
	}
	occupiedbed--;
	emptybed++;
	dischargeid.remove(Integer.valueOf(disch1));
}
System.out.println("Patient discharged succussfully!\n Pay the bill below");
System.out.println("Occupied bed="+occupiedbed+"\nEmpty bed="+emptybed);
Random robj=new Random();
int RNcharges=robj.nextInt(50000); 
int otcharges=robj.nextInt(50000);
int medcharges=robj.nextInt(10000);
int totalch=RNcharges+otcharges+medcharges;
System.err.println("BILL");
System.out.println("------------------------------");
System.out.println("Room and Nursing Charges:"+RNcharges);
System.out.println("OT Charges:"+otcharges);
System.out.println("Medicine and Professional Charges: "+medcharges);
System.out.println("Total Charges="+totalch);
break;
case 4:
System.out.println("Emergency patients ward :");
    for(Patient element : bedQueue)
{
{
        System.out.println("ID :"+element.id);
      		System.out.println("name :"+element.name);
}	 }
break;
case 5:
	System.out.println("Doctors Data :\n--------------------------");
	for(Map.Entry<Integer,Doctor>entry:details.entrySet()) {
		System.out.println("\nID : "+entry.getKey());
		System.out.println(entry.getValue());
	}
 break;
case 6 :
	System.out.println("Nurse Data :\n--------------------------");
	for(Map.Entry<Integer,Nurse>entry:nurse.entrySet()) {
		System.out.println("\nID : "+entry.getKey());
		System.out.println(entry.getValue());
	}
	break;
case 7 :
	System.out.println("Patients Data :\n--------------------------");
	for(Map.Entry<Integer,Patient>entry:patient.entrySet()) {
		System.out.println("\nID : "+entry.getKey());
		System.out.println(entry.getValue());
	}
	break;
}
break;
default :
	System.out.println("Please Enter valid index");
	break;
}
}while(ch<6);
}
}
 