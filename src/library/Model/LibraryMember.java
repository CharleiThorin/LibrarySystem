package library.Model;

import java.time.LocalDate;

public class LibraryMember extends Person {
	 // Commented by Abrha
	
    /*private String firstName;
    private String lastName;
    private String phone;
    private Address address;*/
	
	private Integer memberID;  // Added by Abrha
	

    public LibraryMember(Integer memberID, String firstName, String lastName, String phoneNumber,Address address){
        super(firstName,lastName,phoneNumber,address);
        // Commented by Abrha
        
       /* this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phoneNumber;
        this.address = super.address; */ 
               
        this.memberID = memberID;   // Added by Abrha
    }

   /* public String getFullName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.address.city = city;
    }
    public void setStreet(String street) {
        this.address.street = street;
    }
    public void setZipCode(String zipcode) {
        this.address.zipCode = Integer.parseInt(zipcode);
    }

    public String getAddress() {
        return address.toString();
    } */

    public void checkout(BookCopy copy, LocalDate todaysDate, LocalDate dueDate){
        return;
    }
    public CheckOutRecord getCheckoutRecord(){
        return new CheckOutRecord();
    }
}
