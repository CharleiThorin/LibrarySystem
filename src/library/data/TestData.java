package library.data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import library.Model.Address;
import library.Model.Author;
import library.Model.Book;
import library.Model.CheckOutRecord;
import library.Model.CheckoutRecordEntry;
import library.Model.LibraryMember;

public class TestData {
	List<LibraryMember> members = new ArrayList<LibraryMember>();
	@SuppressWarnings("serial")
	List<Address> addresses = new ArrayList<Address>() {
		{
			add(new Address("101 S. Main", "Fairfield", "IA", "52556"));
			add(new Address("51 S. George", "Georgetown", "MI", "65434"));
			add(new Address("23 Headley Ave", "Seville", "Georgia", "41234"));
			add(new Address("1 N. Baton", "Baton Rouge", "LA", "33556"));
			add(new Address("5001 Venice Dr.", "Los Angeles", "CA", "93736"));
			add(new Address("1435 Channing Ave", "Palo Alto", "CA", "94301"));
			add(new Address("42 Dogwood Dr.", "Fairfield", "IA", "52556"));
			add(new Address("501 Central", "Mountain View", "CA", "94707"));
		}
	};
	public List<Author> allAuthors = new ArrayList<Author>() {
		{
			add(new Author("Joe", "Thomas", "641-445-2123", addresses.get(0), "A happy man is he.", "supervisor"));
			add(new Author("Sandra", "Thomas", "641-445-2123", addresses.get(0), "A happy wife is she.", "supervisor"));
			add(new Author("Nirmal", "Pugh", "641-919-3223", addresses.get(1), "Thinker of thoughts.", "supervisor"));
			add(new Author("Andrew", "Cleveland", "976-445-2232", addresses.get(2), "Author of childrens' books.", "supervisor"));
			add(new Author("Sarah", "Connor", "123-422-2663", addresses.get(3), "Known for her clever style.", "supervisor"));
		}
	};
	
	//Periodical(int issueNumber, String title, int maxCheckoutLength)
//	List<Periodical> allPeriodicals = new ArrayList<Periodical>() {
//		{
//			add(new Periodical(1, "Journal of Skydiving", 3));
//			add(new Periodical(4, "Life Magazine", 7));
//			add(new Periodical(100, "Journal of Symbolic Logic", 3));
//		}
//	};
	//Book(int id, String isbn, String title, int maxCheckoutLength, List<Author> authors)
	List<Book> allBooks = new ArrayList<Book>() {
		{
			add(new Book("The Big Fish", Arrays.asList(allAuthors.get(0), allAuthors.get(1)), "23-11451", 21));
			add(new Book("Antartica", Arrays.asList(allAuthors.get(2)), "28-12331", 7));
			add(new Book("Thinking Java", Arrays.asList(allAuthors.get(3)), "99-22223", 21));
			add(new Book("Jimmy's First Day of School", Arrays.asList(allAuthors.get(4)), "48-56882", 7));
			
		}
	};
	//CheckoutRecordEntry(LendableCopy copy, LocalDate checkoutDate, LocalDate dueDate)
	List<CheckoutRecordEntry> allEntries = new ArrayList<CheckoutRecordEntry>() {
		{
			add(new CheckoutRecordEntry(
				allBooks.get(0).getNextAvailableCopy(), LocalDate.of(2011,12,1), LocalDate.of(2011,12,22)));
			add(new CheckoutRecordEntry(
				allBooks.get(0).getNextAvailableCopy(), LocalDate.of(2015,6,22), LocalDate.of(2015,7,13)));
			add(new CheckoutRecordEntry(
				allBooks.get(1).getNextAvailableCopy(), LocalDate.of(2015,6,27), LocalDate.of(2015,7,18)));
			add(new CheckoutRecordEntry(
				allBooks.get(2).getNextAvailableCopy(), LocalDate.of(2015,6,27), LocalDate.of(2015,7,18)));
			add(new CheckoutRecordEntry(
					allBooks.get(0).getNextAvailableCopy(), LocalDate.of(2015,6,20), LocalDate.of(2015,6,27)));
				add(new CheckoutRecordEntry(
						allBooks.get(0).getNextAvailableCopy(), LocalDate.of(2015,6,20), LocalDate.of(2015,6,27)));
				add(new CheckoutRecordEntry(
						allBooks.get(1).getNextAvailableCopy(), LocalDate.of(2015,6,22), LocalDate.of(2015,6,29)));
				add(new CheckoutRecordEntry(
						allBooks.get(2).getNextAvailableCopy(), LocalDate.of(2015,6,22), LocalDate.of(2015,6,25)));
      
			
		}
	};
	
	List<CheckOutRecord> allRecords = new ArrayList<CheckOutRecord>() {
		{
			add(new CheckOutRecord());
			add(new CheckOutRecord());
			add(new CheckOutRecord());
			add(new CheckOutRecord());
			add(new CheckOutRecord());
			add(new CheckOutRecord());
			add(new CheckOutRecord());
		}
	};
	public static void main(String[] args) {
		TestData td = new TestData();
		td.bookData();
//		td.periodicalData();
		td.libraryMemberData();
		DataAccess da = DataAccess.getDataAccessIstance();
		System.out.println(da.readBooksMap());
////		System.out.println(da.readPeriodicalsMap());
		System.out.println(da.readMemberMap());
	}
	///create books
	public void bookData() {
		allBooks.get(0).addCopy();
		allBooks.get(0).addCopy();
		allBooks.get(2).addCopy();
		allBooks.get(2).addCopy();
		DataAccess.loadBookMap(allBooks);
	}
	
	//create periodicals
//	public void periodicalData() {
//		allPeriodicals.get(0).addCopy();
//		allPeriodicals.get(1).addCopy();
//		allPeriodicals.get(2).addCopy();
//		DataAccessFacade.loadPeriodicalsMap(allPeriodicals);
//	}
	/*
	public void checkoutRecordData() {
		allRecords.get(0).addEntry(allEntries.get(0));
		allRecords.get(0).addEntry(allEntries.get(4));
		allRecords.get(1).addEntry(allEntries.get(1));
		allRecords.get(1).addEntry(allEntries.get(5));
		allRecords.get(2).addEntry(allEntries.get(2));
		allRecords.get(2).addEntry(allEntries.get(6));
		allRecords.get(3).addEntry(allEntries.get(3));
		allRecords.get(3).addEntry(allEntries.get(7));
	}*/
	
	//create library members
	//String memberId, String fname, String lname, String tel,Address add
	public void libraryMemberData() {
		LibraryMember libraryMember = new LibraryMember("Andy", "Rogers", "641-223-2211", addresses.get(4), "1001");
		libraryMember.addCheckoutEntry(allEntries.get(0));
		libraryMember.addCheckoutEntry(allEntries.get(4));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("Drew", "Stevens", "702-998-2414", addresses.get(5), "1002");
		libraryMember.addCheckoutEntry(allEntries.get(2));
		libraryMember.addCheckoutEntry(allEntries.get(5));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("Sarah", "Eagleton", "451-234-8811", addresses.get(6), "1003");
		libraryMember.addCheckoutEntry(allEntries.get(3));
		libraryMember.addCheckoutEntry(allEntries.get(6));
		members.add(libraryMember);
		
		libraryMember = new LibraryMember("Ricardo", "Montalbahn", "641-472-2871", addresses.get(7), "1004");
		libraryMember.addCheckoutEntry(allEntries.get(4));
		libraryMember.addCheckoutEntry(allEntries.get(7));
		members.add(libraryMember);
		
		DataAccess.loadMemberMap(members);
		
		
	}
}
