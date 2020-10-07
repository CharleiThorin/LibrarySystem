package library.Model;

import java.time.LocalDate;

public class BookCopy {
    int copyId;
    LocalDate dueDate;
    LocalDate borrowedDate;
    Book book;
    boolean isBorrowed = false;
    public  BookCopy(int id, Book b) {
        this.copyId = id;
        this.book = b;
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

}
