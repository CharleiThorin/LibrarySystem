package library.Model;

public class Address {
	public String street;
	public String city;
	public String state; // Added By Abrha
	public String zipCode;

	public Address(String street, String city, String state, String zipCode) {
		this.street = street;
		this.city = city;
		this.zipCode = zipCode;
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getCity() {
		return city;
	}

	// -----------Start Modification by Abrha , Setters are added -------------
	public String getState() {
		return state;
	}

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
}
