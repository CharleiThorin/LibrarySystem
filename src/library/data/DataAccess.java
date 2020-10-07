package library.data;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import library.Model.*;

import java.util.ArrayList;
import java.util.List;

public class DataAccess {
    List<LibraryMember> mebers;
    List<Book> books = new ArrayList<Book>();
    List<RecordEntry> recordEntries;
    private static DataAccess dataAccess=null;

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


}
