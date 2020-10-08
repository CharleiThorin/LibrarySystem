//package library.Model;
//
//import java.io.Serializable;
//import java.time.LocalDate;
//
//<<<<<<< HEAD
//public class LibraryMember extends Person implements Serializable {
//
//	private static final long serialVersionUID = -6880393464941795836L;
//	private String firstName;
//    private String lastName;
//    private String phone;
//    private Address address;
//    
//    //SAMU ATTRIBUTES
//	private String memberID;
//	private CheckOutRecord checkoutRecord = new CheckOutRecord();
//	//END SAMU ATTRIBUTES
//=======
//public class LibraryMember extends Person {
//	 // Commented by Abrha
//	
//    /*private String firstName;
//    private String lastName;
//    private String phone;
//    private Address address;*/
//	
//	private Integer memberID;  // Added by Abrha
//	
//>>>>>>> c3cb6a4b25f5e64d819acaecff6d6cc33d24af3d
//
//    public LibraryMember(Integer memberID, String firstName, String lastName, String phoneNumber,Address address){
//        super(firstName,lastName,phoneNumber,address);
//        // Commented by Abrha
//        
//       /* this.firstName = firstName;
//        this.lastName = lastName;
//        this.phone = phoneNumber;
//        this.address = super.address; */ 
//               
//        this.memberID = memberID;   // Added by Abrha
//    }
//    
//    //SAMU CONSTRACTOR
//    public LibraryMember(String firstName, String lastName, String phoneNumber, Address address, String id) {
//		super(firstName, lastName, phoneNumber, address);
//		this.memberID = id;
//	}
//    //END SAMU CONSTRACTOR
//
//   /* public String getFullName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public void setCity(String city) {
//        this.address.city = city;
//    }
//    public void setStreet(String street) {
//        this.address.street = street;
//    }
//    public void setZipCode(String zipcode) {
//        this.address.zipCode = Integer.parseInt(zipcode);
//    }
//
//<<<<<<< HEAD
////    public String getAddress() {
////        return address.toString();
////    }
//=======
//    public String getAddress() {
//        return address.toString();
//    } */
//>>>>>>> c3cb6a4b25f5e64d819acaecff6d6cc33d24af3d
//
//    public void checkout(BookCopy copy, LocalDate todaysDate, LocalDate dueDate){
//        return;
//    }
//    public CheckOutRecord getCheckoutRecord(){
//        return new CheckOutRecord();
//    }
//    
//    //SAMU METHODS
//	//usual way of adding info to the record
//	public void checkout2(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
//		copy = copy.changeAvailability();
//		System.out.println(copy.isAvailable());
//		CheckoutRecordEntry entry = CheckoutRecordEntry.createEntry(copy, checkoutDate, dueDate);
//		checkoutRecord = checkoutRecord.addEntry(entry);	
//	}
//	
//	//not the usual way of adding info to the record -- no save to data storage
//	public void addCheckoutEntry(CheckoutRecordEntry entry) {
//		checkoutRecord = checkoutRecord.addEntry(entry);
//	}
//	
//	public String formattedCheckoutRecord() {
//		StringBuilder sb = new StringBuilder();
//		for(CheckoutRecordEntry e : checkoutRecord.getCheckoutRecordEntries()) {
//			sb.append(e.toString() + "\n");
//		}
//		return sb.toString();
//	}
//	
//
//	public String getMemberID() {
//		return memberID;
//	}
//
//	public CheckOutRecord getCheckoutRecord2() {
//		return checkoutRecord;
//	}
//	
//	@Override
//	public String toString() {
//		return "Member Info: " + "ID: " + memberID + ", name: " + getFirstName() + " " + getLastName() + 
//				", " + getPhoneNumber() + " " + getAddress();
//	}
//	//END SAMU METHODS
//}
