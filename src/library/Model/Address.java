package library.Model;

import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	public String street;
    public String city;
    //SAMU CODE ATTRIBUTES
	private String state;
	private String zipCode2;
	//END SAMU ATTRIBUTES
    public Integer zipCode;

    public Address(String street, String city, int zipCode){
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
    
    //SAMU CONSTRUCTOR
	public Address(String street, String city, String state, String zipCode) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode2 = zipCode;
	}
	//END SAMU CONSTRUCTOR

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public Integer getZipCode() {
        return zipCode;
    }
    
    //SAMU CODE
	public String getState() {
		return state;
	}

//    @Override
//    public String toString() {
//        return getCity() +" "+getStreet()+" "+getZipCode();
//    }
	
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode2 + "]";
	}
    
}
