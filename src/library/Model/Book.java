package library.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Book implements Cloneable, Serializable {

	private static final long serialVersionUID = 763281290891323752L;
	private String ISBN;
    private String title;
    private String author;
    private List<BookCopy> copies;
    //SAMU CODE
    private List<Author> authors;
	private BookCopy[] copies1;
    private int maxCheckoutLenght;
    //need to implements into the constructor

    public Book(String title, String author, String ISBN) {
       this.title = title;
       this.author = author;
       this.ISBN = ISBN;
       this.copies = new ArrayList<BookCopy>();
       this.copies.add(new BookCopy(1, this));
    }
    
    //SAMU CONSTRUCTOR
    public Book(String title, List<Author> author, String ISBN, int numCopies) {
        this.title = title;
        this.authors = Collections.unmodifiableList(author);
        this.ISBN = ISBN;
        this.maxCheckoutLenght = numCopies;
        copies1 = new BookCopy[]{new BookCopy(this, 1, true)};
     }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getISBN() {
        return this.ISBN;
    }
    public void setISBN(String isbn) {
        this.ISBN = isbn;
    }

    public void addCopy(int numCopies) {
        int l = this.copies.size();
        int copyId;
        for(int i = 0; i < numCopies; i++) {
            copyId = l+1;
            this.copies.add(new BookCopy(copyId, this));
        }
    }

    public BookCopy getOneCopy() {
        for(int i = 0; i < this.copies.size(); i++) {
            if(!this.copies.get(i).isBorrowed) {
                this.copies.get(i).isBorrowed = true;
                return this.copies.get(i);
            }
        }
        return null;
    }

    //CODE SAMU METHODS START SEVERAL OF THEM...
	public void updateBookCopyArray(BookCopy copy) {
		for(int i = 0; i < copies1.length; ++i) {
			if(copies1[i].getCopyNum() == copy.getCopyNum()) {
				copies1[i] = copy;
				break;
			}
		}
	}
	
	public List<Integer> getCopyNums() {
		List<Integer> retVal = new ArrayList<>();
		for(BookCopy c : copies1) {
			retVal.add(c.getCopyNum());
		}
		return retVal;
		
	}
	
	public void addCopy() {
		BookCopy[] newArr = new BookCopy[copies1.length + 1];
		System.arraycopy(copies1, 0, newArr, 0, copies1.length);
		newArr[copies1.length] = new BookCopy(this, copies1.length +1, true);
		copies1 = newArr;
	}
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(ob.getClass() != getClass()) return false;
		Book b = (Book)ob;
		return b.ISBN.equals(ISBN);
	}
	
	@Override
	public Book clone() {
		Book retval = null;
		try {
			retval = (Book)super.clone();
			BookCopy[] newCopies = new BookCopy[copies1.length];
			for(int i = 0; i < copies1.length; ++i) {
				newCopies[i] = new BookCopy(
						new Book(ISBN, authors, title, maxCheckoutLenght),
						copies1[i].getCopyNum(),
						copies1[i].isAvailable());
				
			}
			for(int i = 0; i < copies1.length; ++i) {
				((Book)newCopies[i].getBorrowedBookCopy()).copies1 = newCopies;
			}
			retval.copies1 = newCopies;
		} catch(CloneNotSupportedException e) {
			throw new IllegalStateException("Cannot clone Book");
		}
		return retval;
	}
	public boolean isAvailable() {
		if(copies1 == null) {
			return false;
		}
		return Arrays.stream(copies1)
				     .map(l -> l.isAvailable())
				     .reduce(false, (x,y) -> x || y);
	}
	
	
	
	public BookCopy getNextAvailableCopy() {	
		Optional<BookCopy> optional 
			= Arrays.stream(copies1)
			        .filter(x -> x.isAvailable()).findFirst();
		return optional.isPresent() ? optional.get() : null;
	}
	
	
	public BookCopy getCopy(int copyNum) {
		for(BookCopy c : copies1) {
			if(copyNum == c.getCopyNum()) {
				return c;
			}
		}
		return null;
	}
	
	
	
	@Override
	public String toString() {
		return "isbn: " + ISBN + ", maxLength: " + maxCheckoutLenght + ", available: " + isAvailable();
	}
	
    public List<Author> getAuthors() {
        return this.authors;
    }
    
	public int getCheckoutMaxLenght() {
		return maxCheckoutLenght;
	}
	//END SAMU CODE....

}
