package library.Model;


import java.io.Serializable;

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;
	public String street;
    public String city;
  
	//END SAMU ATTRIBUTES
    public Integer zipCode;

    
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
    
//    //SAMU CODE
//=======
//public class Address {
//	public String street;
//	public String city;
//	public String state; // Added By Abrha
//	public String zipCode;
//
//	public Address(String street, String city, String state, String zipCode) {
//		this.street = street;
//		this.city = city;
//		this.zipCode = zipCode;
//		this.state = state;
//	}
//
//	public String getStreet() {
//		return street;
//	}
//
//	public String getZipCode() {
//		return zipCode;
//	}
//
//	public String getCity() {
//		return city;
//	}
//
//	// -----------Start Modification by Abrha , Setters are added -------------
//>>>>>>> c3cb6a4b25f5e64d819acaecff6d6cc33d24af3d
//	public String getState() {
//		return state;
//	}
//
//<<<<<<< HEAD
////    @Override
////    public String toString() {
////        return getCity() +" "+getStreet()+" "+getZipCode();
////    }
	
	@Override
	public String toString() {
		return "Address [street=" + street + ", city=" + city + ", state=" + state + ", zipCode=" + zipCode2 + "]";
	}
    
=======
	public void setState(String state) {
		this.state = state;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	// End Modification -------------------------

	@Override
	public String toString() {
		return getCity() + " " + getStreet() + " " + getZipCode();
	}
//>>>>>>> c3cb6a4b25f5e64d819acaecff6d6cc33d24af3d
}
