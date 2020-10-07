package library.Model;

public class RecordEntry {
    BookCopy copy;
    LibraryMember member;

    public RecordEntry(BookCopy c, LibraryMember m) {
        this.copy = c;
        this.member = m;
    }
    public BookCopy getBookCopy() {return this.copy;}
    public LibraryMember getMember() {return this.member;}

}
