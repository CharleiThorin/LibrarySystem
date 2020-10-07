package library.Model;

public class Author extends Person {
    private String credentials;
    private String shortBio;

    public Author(String firstName, String lastName, String phoneNumber,Address address){
        super(firstName,lastName,phoneNumber,address);
        this.shortBio = String.format("Author name is: %s %s. %n Address is %s  %s  ", getFirstName(), getLastName(), getAddress(),getPhoneNumber());
        this.credentials = String.format(" Author: %s %s   ", getFirstName(), getLastName());
    }

    public String getCredentials() {
        return credentials;
    }

    public String getShortBio() {
        return shortBio;
    }
}
