package Buffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);

		int ch,a,b,x,d,f,h,j,n,m;

		String u,p,c,z,menu,ch1,menu1;
		boolean flag = false;
		String uorg="Anushree";
		String porg="saga123";
		//signUp s=new signUp();
		logins l=new logins();
		pQueue pQ=new pQueue();
		System.out.println("Select Your Role ");
		System.out.println("1]Admin\n2]User");
		a=sc.nextInt();
		switch(a)
		{
		case 1:
			do {
				System.out.println("Enter your username and password");
				u=sc.next();
				System.out.println("Enter your Password");
				p=sc.next();
				if(u.equals(uorg)&& p.equals(porg))
				{
					System.out.println("Welcome Admin");
					flag=true;
				}
				else
					System.out.println("Wrong pasword or username please try again");

			}
			while(flag==false);
			do {
				System.out.println("HELLO ADMIN!");
				System.out.println("**********************MENU*******************");
				System.out.println("1]View planned events\n2]Append event list");
				m=sc.nextInt();
				switch(m)
				{

				case 1:

					pQ.dispadmin();
					break;
				

				case 2:
					

					pQ.addJan();


					break;
				}
				System.out.println("Return back to menu?");
				menu1=sc.next();
			}

			while(menu1.equals("yes"));

			break;


		case 2:
			do{
				System.out.println("Press 1 to login and 2 to Signup");
				ch=sc.nextInt();

				switch(ch)
				{
				case 1:

					while(true) {
						System.out.println("Enter username: ");
						String usernames = sc.next();
						System.out.println("Enter password: ");
						String passwords= sc.next();
						if (l.authenticate(usernames, passwords)) {
							System.out.println("Authentication successful! Welcome, " + usernames + "!");
							break; 
						} else {
							System.out.println("Invalid username or password. Please try again.");
						}

					}
					do {
						System.out.println("WELCOME!");
						System.out.println("Please select one of the following preferences");
						System.out.println("1]View the Events\n2]Do give us your feedback\n3]We accept donations");
						int logch=sc.nextInt();
						switch(logch)
						{
						case 1:
							pQ.displayPQ();
							
							break;
						case 2:

							System.out.println("Please provide your rating (1-5): ");
							int rating = sc.nextInt();


							sc.nextLine();
							System.out.println("Please provide your comments: ");
							String comments = sc.nextLine();

							feedback feedback = new feedback(rating, comments);


							System.out.println("\nThank you for your feedback:");
							feedback.displayFeedback();
							break;
						case 3:
							System.out.println("Would you like to donate cash? (yes/no)");
							String response = sc.next();
							double amount,donations=10000;
							if (response.equals("yes")) {
								// If yes, prompt for the amount
								System.out.println("How much would you like to donate?");
								amount = sc.nextDouble();
								System.out.println("Thank you for donating Rs. " + amount);
								donations=donations+amount;
								// Here you can proceed with further actions like processing the donation
							} else if (response.equals("no")) {
								System.out.println("Thank you for considering!");
							} else {
								System.out.println("Invalid response. Please enter 'yes' or 'no'.");
							}
							System.out.println("Donations received uptil now "+donations);



							break;
						}
						System.out.println("Return back to menu?");
						menu=sc.next();

					}while(menu.equals("yes"));

					break;	
				case 2:

					System.out.println("Welcome to the Sign-up Page");
					Boolean continues=true;
					while (continues) {
						System.out.println("Enter username: ");
						String username = sc.next();

						System.out.println("Enter password: ");
						String password = sc.next();

						boolean success = l.signUp(username, password);
						System.out.println("Do you want to sign up another user? (yes/no): ");
						String input = sc.next();
						if (input.equals("no")) {
							continues= false;
						}
					}
					break;
				}
				System.out.println("Do you wish to continue?");
				ch1=sc.next();
			} while(ch1.equalsIgnoreCase("yes"));





		}
		System.out.println("THANKYOU!DO VISIT AGAIN");
	}
}





