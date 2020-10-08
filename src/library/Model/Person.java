package library.Model;

import java.io.Serializable;

public class Person implements Serializable {
 
	private static final long serialVersionUID = 2128819190515577029L;
	String firstName;
    String lastName;
    String phoneNumber;
    Address address;

    Person(String firstName, String lastName, String phoneNumber,Address address){
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

  	public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }
    
    // Setters Added By Abrha,
    public void setFirstName(String firstName) {
  		this.firstName = firstName;
  	}

  	public void setLastName(String lastName) {
  		this.lastName = lastName;
  	}

  	public void setPhoneNumber(String phoneNumber) {
  		this.phoneNumber = phoneNumber;
  	}

  	public void setAddress(Address address) {
  		this.address = address;
  	}
}
