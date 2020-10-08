package library.Model;

import java.io.Serializable;

public class Author extends Person implements Serializable {

	private static final long serialVersionUID = 5657548164061425343L;
	private String credentials;
    private String shortBio;

    public Author(String firstName, String lastName, String phoneNumber,Address address){
        super(firstName,lastName,phoneNumber,address);
        this.shortBio = String.format("Author name is: %s %s. %n Address is %s  %s  ", getFirstName(), getLastName(), getAddress(),getPhoneNumber());
        this.credentials = String.format(" Author: %s %s   ", getFirstName(), getLastName());
    }
    
    //SAMU CONSTRUCTOR
	public Author(String firstName, String lastName, String phoneNumber, Address address, String shortBiography, String credential) {
		super(firstName, lastName, phoneNumber, address);
		this.shortBio = shortBiography;
		this.credentials = credential;
	}
	//END SAMU CONSTRUCTOR

    public String getCredentials() {
        return credentials;
    }

    public String getShortBio() {
        return shortBio;
    }
}
