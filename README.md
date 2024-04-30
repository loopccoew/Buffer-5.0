# Buffer-5.0
Buffer is a Data Structures and Algorithms Project Series, in which students can participate as mentees in teams of 2-4. Under Buffer 5.0, the themes on which students can create a project are: 

1. Public Welfare
2. Tourism
3. College level applications
4. Custom Data structure

This repository is created for all the teams to be able to upload their final project source code. While submitting, note that all the submission guidelines given are followed, and all the files are named appropiately. Also ensure that your README file contains the links of the progress reports and the drive link containing the video of the project.


																																	COLLEGE APPLICATION_TASK SCHEDULER
package sem2practice;
import java.util.*;
import java.io.*;
public class Main {
public static void main(String args[])  {

//User Interface Menu

Scanner sc=new Scanner(System.in);
String a;
System.out.println("\n");
operations op=new operations();
System.out.println("***** WELCOME TO CUMMINS DAILY TASK SCHEDULER ******");
System.out.println("\n");
System.out.println("The key to your success is hidden in your daily routine");
System.out.println("\n");

System.out.println("enter no of tasks");

int n=sc.nextInt();
for(int i=0;i<n;i++){
//Formation of Stack
op.add();
}

//Displaying the Stack
op.disp(n);
System.out.println("Enter 1 if you have completed your current task \nEnter 2 to check the upcoming task\n  " );

int choice=0;
for(int i=0;i<(3*n);i++)
{
try
{
choice=sc.nextInt();

// User operation menu
switch(choice){
case 1:
op.remove ();

break;
case 2:
op.peek();

break;

}
}catch(EmptyStackException v) {
System.out.println("Great work! You are done for the day");

}
catch(InputMismatchException h) {
System.out.println("Invalid input");
}
}
}}

class operations extends Main{
Scanner sc=new Scanner(System.in);
Stack<String> stack2 = new Stack<String>();
Stack<String> stack1=new Stack<String>();

//operation 1

public void add() {
System.out.println("enter task");
String a=sc.nextLine();
stack2.push(a);
System.out.println(stack2);
System.out.println("\n");

}

public void disp(int j) {
String temp;
// Reversing Stack2 and storing it in Stack for display function
for(int i=0;i<j;i++) {
temp=stack2.pop();
stack1.push(temp);
}
}
//Operation 2
void remove(){
stack1.pop();
System.out.println("Task completed");
System.out.println("\n");
System.out.println("Enter 1 if you have completed your current task \nEnter 2 to check the upcoming task\n  " );


}
//Operation 3
public void peek(){
System.out.println("Next task is "+stack1.peek());
System.out.println("\n");
System.out.println("Enter 1 if you have completed your current task \nEnter 2 to check the upcoming task\n  " );


}
}
//THE END! Hope you liked the project :)

drive link for the video!
https://drive.google.com/file/d/1roScnS-vVlTnqyXm7lbx75yIKVK_1xmZ/view?usp=sharing
