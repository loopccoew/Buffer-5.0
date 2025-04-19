package tourismbooking;

public class Person{

		private String fname;
		private String lname;
		private int age;
		private String aadhaar;
		private char gender;
	
		public Person(String firstName, String lastName, int age, String aadhaar, char gender) {
			this.fname = firstName;
			this.lname = lastName;
			this.age = age;
			this.aadhaar = aadhaar;
			this.gender = gender;
	
		}
		public String getFirstName() {
	        return fname;
	    }

	    public String getLastName() {
	        return lname;
	    }

	    public int getAge() {
	        return age;
	    }

	    public String getAadhaar() {
	        return aadhaar;
	    }

	    public char getGender() {
	        return gender;
	    }
	}
