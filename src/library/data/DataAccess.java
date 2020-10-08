package library.data;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.Model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class DataAccess {
    List<LibraryMember> mebers;
    List<Book> books = new ArrayList<Book>();
    List<RecordEntry> recordEntries;
    private static DataAccess dataAccess=null;
    
    //SAMU ATTRIBUTES
    public static final String DATE_PATTERN = "MM/dd/yyyy";
	private static DataAccess facade = new DataAccess();
	enum StorageType {
		BOOKS, MEMBERS;
	}
	public static final String OUTPUT_DIR = "C:\\Users\\sbart\\Desktop\\Final Project\\LibrarySystem\\src\\library\\data\\files";
	private static HashMap<String,Book> books2;
	private static HashMap<String, LibraryMember> members;
    //END SAMU ATTRIBUTES

    private DataAccess() {
        this.mebers = new ArrayList<LibraryMember>();
        this.books = new ArrayList<Book>();
        this.recordEntries = new ArrayList<RecordEntry>();
        loadSampleData();
    }

    public void loadSampleData() {
        Book b = new Book("Java", "Liang", "ABCD");
        b.addCopy(2);
        addBook(b);
        LibraryMember m = new LibraryMember("Sol", "sol","641-111-2222", new Address("1000N 4th street", "FairField", 52556));
        addMember(m);
        RecordEntry r = new RecordEntry(b.getOneCopy(), m);
        this.addRecord(r);
    }
    public static DataAccess getInstance(){
        if(dataAccess == null){
            dataAccess = new DataAccess();
        }
        return dataAccess;
    }

    public void addMember(LibraryMember m) {
        this.mebers.add(m);
    }

    public List<LibraryMember> getMembers() {
        return this.mebers;
    }

    public void addBook(Book b) {
        this.books.add(b);
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public Book getBook (String ISBN) {
        for(Book b: this.books) {
            if(b.getISBN().equals(ISBN)) {
                return b;
            }
        }
        return null;
    }

    public void updateBook(Book b) {
        for(int i = 0; i< this.books.size(); i++) {
            if(this.books.get(i).getISBN().equals(b.getISBN())) {
                this.books.set(i, b);
                return;
            }
        }
    }

    public void addRecord(RecordEntry r) {
        this.recordEntries.add(r);
    }

    public List<RecordEntry> getRecords() {
        return this.recordEntries;
    }

    public ObservableList<RecData> getAllRecords() {
        ObservableList<RecData> recs = FXCollections.observableArrayList();
        for(RecordEntry r: recordEntries) {
            recs.add(new RecData(r.getBookCopy().getBook().getISBN(),
                                 r.getBookCopy().getBook().getTitle(),
                                 r.getBookCopy().getBook().getAuthor(),
                                 r.getMember().getFullName(), r.getBookCopy().getDueDate()));
        }
        return recs;

    }

    public LibraryMember getmember(String name) {
        for(LibraryMember m: mebers) {
            if(name.equals(m.getFullName())) {
                return m;
            }
        }
        return null;
    }
    
    //SAMU METHODS
	public static DataAccess getDataAccessIstance() {
		return facade;
	}
	
	public LibraryMember searchMember(String memberId) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		if(mems.containsKey(memberId)) {
			return mems.get(memberId);
		}
		return null;
	}
	
	public Book searchBook(String isbn) {
		HashMap<String,Book> booksMap =  readBooksMap();
		return booksMap.get(isbn);
	}
	
	public void saveNewMember(LibraryMember member) {
		HashMap<String, LibraryMember> mems = readMemberMap();
		String memberId = member.getMemberID();
		mems.put(memberId, member);
		members = mems;
		saveToStorage(StorageType.MEMBERS, mems);	
	}
	
	public void updateMember(LibraryMember member) {
		saveNewMember(member);
	}
	
	public void saveNewBook(Book book) {
		HashMap<String, Book> bookMap = readBooksMap();
		String isbn = book.getISBN();
		bookMap.put(isbn, book);
		books2 = bookMap;
		saveToStorage(StorageType.BOOKS, bookMap);	
	}
	
	@SuppressWarnings("unchecked")
	public  HashMap<String,Book> readBooksMap() {
		if(books2 == null) {
			books2 = (HashMap<String,Book>) readFromStorage(StorageType.BOOKS);
		}
		
		return books2;
	}
	
	@SuppressWarnings("unchecked")
	public HashMap<String, LibraryMember> readMemberMap() {
		if(members == null) {
			members = (HashMap<String, LibraryMember>) readFromStorage(
					StorageType.MEMBERS);
		}
		return members;
	}
	
	public String computeStatus(BookCopy copy) {
		Book bookCopy = copy.getBorrowedBookCopy();
		List<LibraryMember> membersFound = readMemberMap().values()
				         .stream()			      
				         .filter(member ->  
				            {   //returns all members with a checkout record having an entry
				            	//containing a copy that matches input copy
				            	return member.getCheckoutRecord().getCheckoutRecordEntries()
				            	      .stream()
				            	      .filter(e -> 			                   
				            	            e.getBookCopy().getBorrowedBookCopy().equals(bookCopy))
				            	      .findAny()
				            	      .isPresent();
				            })
				          .collect(Collectors.toList());
		String status = null;
		
		if(!membersFound.isEmpty()) {
			LibraryMember borrower = membersFound.get(0);
			CheckOutRecord record = borrower.getCheckoutRecord();
			//returns true if the checkout record entry for this copy indicates the item is overdue
			boolean isOverdue = record.getCheckoutRecordEntries().stream()
					                  .filter(e -> e.getBookCopy().getBorrowedBookCopy().equals(bookCopy) 
					                		  && e.isOverdue())
					                  .findAny().isPresent();
		
			status = "Book: " + copy.getBorrowedBookCopy().getTitle() + "\n" + 
					"Member: " + borrower.getFirstName() + "Book is Overdue?: " +  isOverdue;
		} else {
			//no borrower was found
			status = "No Borrower has found";
		}
				    
		return status;
		
	}
	
	public void checkoutBook(String memberId, String isbn) throws LibrarySystemException  {
		LibraryMember mem = searchMember(memberId);
		if(mem == null) throw new LibrarySystemException("Library member with ID " + memberId + " not found!");
		Book book = searchBook(isbn);
		if(book == null || !book.isAvailable()) throw new LibrarySystemException("Book with ISBN = " + isbn + " is not available for checkout!");
		
		BookCopy nextAvailableCopy = book.getNextAvailableCopy();
		if(nextAvailableCopy == null) 
			throw new IllegalStateException("Book tests as 'true' for isAvailable, but cannot find available copy.");
		mem.checkout(nextAvailableCopy, 
				LocalDate.now(), 
				LocalDate.now().plus(book.getCheckoutMaxLenght(), ChronoUnit.DAYS));
		saveNewMember(mem);
	}
	
	public static void loadMemberMap(List<LibraryMember> memberList) {
		members = new HashMap<String, LibraryMember>();
		memberList.forEach(member -> members.put(member.getMemberID(), member));
		saveToStorage(StorageType.MEMBERS, members);
	}
	public static void loadBookMap(List<Book> bookList) {
		books2 = new HashMap<String, Book>();
		bookList.forEach(book -> books2.put(book.getISBN(), book));
		saveToStorage(StorageType.BOOKS, books2);
	}
	
	public static void saveToStorage(StorageType type, Object ob) {
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(ob);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
	}
	
	public static Object readFromStorage(StorageType type) {
		ObjectInputStream in = null;
		Object retVal = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
			in = new ObjectInputStream(Files.newInputStream(path));
			retVal = in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return retVal;
	}
	//END SAMU METHODS


}
