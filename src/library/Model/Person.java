package library.Model;

public class Person {
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

    public String getAddress() {
        return address.toString();
    }
}
