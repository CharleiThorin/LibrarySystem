package library.Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import library.data.DataAccess;


public class CheckoutRecordEntry implements Serializable, Cloneable {

	private static final long serialVersionUID = -3589079118415139349L;
	private static final Logger LOG = Logger.getLogger(CheckoutRecordEntry.class.getName());
	private LocalDate checkoutDate;
	private LocalDate dueDate;
	private CheckOutRecord checkoutRecord;
    BookCopy copy;
    private boolean wasReturned = false;
    
    public CheckoutRecordEntry(BookCopy copy, LocalDate checkoutDate, LocalDate dueDate) {
        this.copy = copy;
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
    }
    
	public static CheckoutRecordEntry createEntry(BookCopy copy,
			LocalDate checkoutDate, LocalDate dueDate) {
		return new CheckoutRecordEntry(copy, checkoutDate, dueDate);
	}
    
    public BookCopy getBookCopy() {return this.copy;}
    

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public CheckOutRecord getCheckoutRecord() {
		return checkoutRecord;
	}

	public boolean isOverdue() {
		if(wasReturned) return false;
		if(dueDate.isBefore(LocalDate.now())) return true;
		return false;
	}
	
	public CheckoutRecordEntry returnCopy() {
		if(wasReturned) {
			LOG.warning("Attempt to return a book that was already returned");
			return this;
		}
		CheckoutRecordEntry retval = clone();
		retval.wasReturned = true;
		return retval;
		
	}
	
	@Override
	public CheckoutRecordEntry clone() {
		CheckoutRecordEntry retval = null;
		try {
			retval = (CheckoutRecordEntry)super.clone();
		} catch(CloneNotSupportedException e) {
			throw new IllegalStateException("Cannot clone CheckoutRecordEntry");
		}
		return retval;
	}
	
	@Override
	public String toString() {
		return "[" + "checkoutdate:" + 
	        checkoutDate.format(DateTimeFormatter.ofPattern(DataAccess.DATE_PATTERN)) +
	        ", dueDate: " + dueDate.format(DateTimeFormatter.ofPattern(DataAccess.DATE_PATTERN)) +
	        ", publication: " + copy + "]";
	}
    
    
//    public LibraryMember getMember() {return this.member;}

}
