package library.Model;

public class Address {
    public String street;
    public String city;
    public Integer zipCode;

    public Address(String street, String city, int zipCode){
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    @Override
    public String toString() {
        return getCity() +" "+getStreet()+" "+getZipCode();
    }
}
