package library.Model;

import java.io.Serializable;
import java.time.LocalDate;

public class BookCopy implements Cloneable, Serializable {

	private static final long serialVersionUID = -5337004823228123082L;
	int copyId;
    LocalDate dueDate;
    LocalDate borrowedDate;
    Book book;
    boolean isBorrowed = false;
    
    //SAMU CODE ATTRIBUTES...
    private int copyNum;
    private boolean isAvailable; //same goal like isBorrowed
    //END SAMU ATTRIBUTES, needs to implement them to constructor.
    
    public  BookCopy(int id, Book b) {
        this.copyId = id;
        this.book = b;
    }
    
    //SAMU CONSTRUCTORS...
	BookCopy(Book book, int copyNum, boolean isAvailable) {
		this.book = book;
		this.copyNum = copyNum;
		this.isAvailable = isAvailable;
	}
	
	BookCopy(Book book, int copyNum) {
		this.book = book;
		this.copyNum = copyNum;
	}

    public void setDueDate(LocalDate d) {
        this.dueDate = d;
    }

    public  LocalDate getDueDate() {
        return this.dueDate;
    }

    public int getCopyId() {
        return copyId;
    }

    public void setBookID(int id) {
        this.copyId = id;
    }

    public void setBorrowedDate(LocalDate d) {
        this.borrowedDate = d;
    }

    public  LocalDate getBorrowedDate() {
        return this.borrowedDate;
    }

    public Book getBook() {return this.book;}

    public void setIsBorrowed(boolean b) {
        this.isBorrowed = b;
    }

    public boolean isBorrowed(){
        return this.isBorrowed;
    }
    
    //SAMU CODE METHODS
	public BookCopy changeAvailability() {
		BookCopy copy = clone();
		copy.isAvailable = !isAvailable;
		((Book)copy.getBorrowedBookCopy()).updateBookCopyArray(copy);
		return copy;
	}
	
	public int getCopyNum() {
		return copyNum;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}
	
	public Book getBorrowedBookCopy() {
		return book;
	}
	
	@Override
	public BookCopy clone() {
		BookCopy retval;
		try {
			retval = (BookCopy)super.clone();
			Book b = (Book)retval.getBorrowedBookCopy();
			retval.book = b.clone();
			
		} catch(CloneNotSupportedException e) {
			throw new IllegalStateException("Unable to clone BookCopy");
		}
		return retval;
	}
	//END SAMU METHODS....

}
