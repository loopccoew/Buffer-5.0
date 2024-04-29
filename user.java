package Buffer;

import java.util.ArrayList;

public class user {
	private String email;
    private String name;
    private String surname;
    private double phoneNumber;

    public user(String email, String name, double phoneNumber,String surname) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.surname = name;
}

public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


}

