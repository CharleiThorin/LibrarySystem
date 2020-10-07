package library.Model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String ISBN;
    private String title;
    private String author;
    private List<BookCopy> copies;

    public Book(String title, String author, String ISBN) {
       this.title = title;
       this.author = author;
       this.ISBN = ISBN;
       this.copies = new ArrayList<BookCopy>();
       this.copies.add(new BookCopy(1, this));
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

    /* sammy */

}
