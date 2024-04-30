package buffer;
import java.util.*;

class info{
	String name;
	int age;
	String gender;
	String mail;
}

class Buffer{
	Scanner sc=new Scanner(System.in);
	info Buffer[]=new info[10];
	void accept() {        
            Buffer[0]=new info();
            System.out.println("Enter name of customer");
            Buffer[0].name=sc.next();
            System.out.println("Enter age of customer");
            Buffer[0].age=sc.nextInt();
            System.out.println("Enter the gender of customer");
            Buffer[0].gender=sc.next();        
        	System.out.println("Enter the mail of customer");        
        	Buffer[0].mail=sc.next();            
    	}
}

class Option {
    char text;
    Node nextNode;

    public Option(char text, Node nextNode) {
        this.text = text;
        this.nextNode = nextNode;
    }
}

class Node {
    String question;
    List<Option> options;

    public Node(String question) {
        this.question = question;
        this.options = new ArrayList<>();
    }

    public void addOption(char text, Node nextNode) {
        options.add(new Option(text, nextNode));
    }

    public List<Option> getOptions() {
        return options;
    }
}

class Prompt{
    void create(){
        Node q1 = new Node("a. Are you constantly Worried?\n"+
                           "b. Do you always get negative thoughts?\n"+
                           "c. Do you experience hallucinations or dellusions?\n"+
                           "d. Do you have trouble focusing on work?");
        Node q2 = new Node("a. Do you constantly feel restless?\n"+
                           "b. Are you frequently feeling tired?");
        Node q3 = new Node("a. Are you frequently feeling sad\n"+
                           "b. Are you percieved as a person with bad temper?");
        Node q4 = new Node("a. Are you extremely paranoid about your surroundings?\n"+
                           "b. Do you smoke/drink a lot?\n"+
                           "c. Do you often see/hear/experience non existent things?");
        Node q5 = new Node("a. Is it been for long time?\n"+
                            "b. Is it a recent experience");
        Node q6 = new Node("a. do you get this feeling when certain things misplaced or cluttered?\n"+
                            "b. Is it induced due to daytoday events?");
        Node q7 = new Node("a. Do you experience muscle tension?\n"+
                             "b. Have you experienced any change in body weight?");
        Node q8 = new Node("a. Is it been for long time?\n"+
                            "b. Is it a recent experience");
        Node q9 = new Node("a. Are you exercising more than required?\n"+
                            "b. Are you overworking yourself?");
        Node q10=new Node("a. Have you lost significant bodyweight?\n"+
                            "b. Have you suddenly gained bodyweight?");
        
        Node root1=new Node("OCD");
        Node root2=new Node("Anxiety");
        Node root3=new Node("Depression");
        Node root4=new Node("Situational depression");
        Node root5=new Node("Dellusional disorder");
        Node root6=new Node("Alcoholism");
        Node root7=new Node("Shizophrenia");
        Node root8=new Node("Stress");
        Node root9=new Node("Fatigue");
        Node root10=new Node("Eating disorder");
        Node root11=new Node("ADHD");
        
        q1.addOption('a', q2);
        q1.addOption('b', q3);
        q1.addOption('c', q4);
        q1.addOption('d', q5);
       
        q2.addOption('a', q6);  
        q2.addOption('b', q7);  
       
        q3.addOption('a', q8);
        q3.addOption('b', root8);
       
        q4.addOption('a',root5);
        q4.addOption('b',root6);
        q4.addOption('c',root7);
       
        q5.addOption('a', root11);
        q5.addOption('b', q2);
       
        q6.addOption('a', root1);
        q6.addOption('b', root2);
       
        q7.addOption('a', q9);
        q7.addOption('b', q10);
       
        q8.addOption('a', root3);
        q8.addOption('b', root4);
       
        q9.addOption('a', root9);
        q9.addOption('b', root8);
       
        q10.addOption('a', q9);
        q10.addOption('b', root10);
       
        Scanner scanner = new Scanner(System.in);
        Node currentNode = q1;
        while (currentNode != null) {
            if (currentNode.getOptions().isEmpty()) {
                System.out.println("Result: " + currentNode.question);
                break;
            }
            System.out.println(currentNode.question);
            System.out.print("Choose an option: ");
            char option = scanner.next().charAt(0);
            currentNode = getNextNode(currentNode, option);
        }
    }
   
    Node getNextNode(Node currentNode, char option) {
        for (Option opt : currentNode.options) {
            if (opt.text==option) {
                return opt.nextNode;
            }
        }
        System.out.println("Invalid option. Please choose again.");
        return currentNode;
    }
}

class Graphs{
	void mtd(int location){
         int distances[][]=new int[6][4];
// 6 locations, 4 clinics
//location-location, clinic-location and clinic-clinic distances not required only location-clinic distance is required hence the the dimension is not 10*10

     distances[0][0]=5;//distance from Aundh to clinic 1 is 5km
     distances[0][1]=8;
     distances[0][2]=9;
     distances[0][3]=12;
     distances[1][0]=2;//distance from Baner to clinic 1 is 2km
     distances[1][1]=10;
     distances[1][2]=12;
     distances[1][3]=14;
     distances[2][0]=4;//distance from Hadapsar to clinic 1 is 2km
     distances[2][1]=1;
     distances[2][2]=2;
     distances[2][3]=5;
     distances[3][0]=4;//distance from  Hinjewadi to clinic 1 is 4km
     distances[3][1]=1;
     distances[3][2]=8;
     distances[3][3]=15;
     distances[4][0]=2;//distance from Deccan to clinic 1 is 2km
     distances[4][1]=1;
     distances[4][2]=4;
     distances[4][3]=5;
     distances[5][0]=2;//distance from Prabhat road to clinic 1 is 4km
     distances[5][1]=10;
     distances[5][2]=4;
     distances[5][3]=15;
     int minimum=distances[location][0];
     int index=0;
     for(int j=0;j<4;j++)
     {  
         if(distances[location][j]<=minimum)
         {
             minimum=distances[location][j];
              index=j;             
        }
     }
     
     switch(index)
     {
         case 0:
             {
     System.out.println("The nearest clinic from your location is MindfulHealing Hub which is"+" "+distances[location][index]+"km away\ncontact us on 020-24456782 to book your appointment or book on www.minfulhealing.com ");
                 break;
             }
         case 1:
             {
                 System.out.println("The nearest clinic from your location is ZenLife, which is"+" "+distances[location][index]+" km away\ncontact us on 020-24466782 to book your appointment or book on www.zenlife.com");
                 break;                
             }
           case 2:
             {
                 System.out.println("The nearest clinic from your location is LifeLine Clinic, which is"+" "+distances[location][index]+" km away \ncontact us on 020-21166782 to book your appointment or book on www.lifeline.com");
                 break;                
             }
              case 3:
             {
                 System.out.println("The nearest clinic from your location is EmpowerMinds Clinic, which is"+" "+distances[location][index]+" km away \ncontact us on 020-21166744 to book your appointment or book on www.empowerminds.com");
                 break;                
             }
     }
     }
 }
public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
        Buffer b=new Buffer();
	    b.accept();
	    
        Prompt obj=new Prompt();
        obj.create();
        
        Graphs obj2=new Graphs();
        int location=0;
        System.out.println("Enter your location of residence\n0.Aundh\n1.Baner\n2.Hadapasar\n3.Hinjewadi\n4.Deccan\n5.Prabhat road");
	    try {
	        location=sc.nextInt();
	    } catch (NoSuchElementException e) {
	        System.out.println("Invalid input. Please enter a valid integer.");
	        sc.nextInt(); 
	    }
	    obj2.mtd(location);
    }
}

/*
 OUTPUT:
 Enter name of customer
shweta
Enter age of customer
19
Enter the gender of customer
female
Enter the mail of customer
shweta@gmail.com
a. Are you constantly Worried?
b. Do you always get negative thoughts?
c. Do you experience hallucinations or dellusions?
d. Do you have trouble focusing on work?
Choose an option: d
a. Is it been for long time?
b. Is it a recent experience
Choose an option: b
a. Do you constantly feel restless?
b. Are you frequently feeling tired?
Choose an option: b
a. Do you experience muscle tension?
b. Have you experienced any change in body weight?
Choose an option: b
a. Have you lost significant bodyweight?
b. Have you suddenly gained bodyweight?
Choose an option: b
Result: Eating disorder
Enter your location of residence
0.Aundh
1.Baner
2.Hadapasar
3.Hinjewadi
4.Deccan
5.Prabhat road
1
The nearest clinic from your location is MindfulHealing Hub which is 2km away
contact us on 020-24456782 to book your appointment or book on www.minfulhealing.com 
 
*/