package tourismbooking;

public class Booking {
	private int noOfPersons;
	private long mob;
	private String email;
	private Person persons[];
	private int index=0;
	Packages.Package selectedPackage;
	
	public Booking(int noOfPersons, long mob, String email) {
        this.noOfPersons = noOfPersons;
        this.mob = mob;
        this.email = email;
        this.persons = new Person[noOfPersons]; 
    }
	public void addPerson(Person person) {
	    if (index < noOfPersons) {
	        persons[index] = person;
	        index++;
	    } 
	    
	
	}
	
	public void addPackage(Packages.Package packageToAdd) {
        
            this.selectedPackage = packageToAdd; 
            System.out.println("----------------------------------------");
            System.out.println("Package added successfully : ");
            System.out.println(packageToAdd);
            System.out.println("----------------------------------------");
    }
	
	public int getNoOfPersons() {
        return noOfPersons;
    }

    public long getMob() {
        return mob;
    }

    public String getEmail() {
        return email;
    }

    public Person[] getPersons() {
        return persons; 
    }
}
