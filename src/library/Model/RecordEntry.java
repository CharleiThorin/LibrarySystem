package library.Model;

public class RecordEntry {
    BookCopy copy;
   // LibraryMember member; // Commented by Abrha
    Person libraryMember;  // Added by Abrha

    public RecordEntry(BookCopy c, Person libraryMember) {
        this.copy = c;
        this.libraryMember = libraryMember;
    }
    public BookCopy getBookCopy() {return this.copy;}
    //public LibraryMember getMember() {return this.member;} //Commented by Abrha
    public Person getMember() {return this.libraryMember;} // Added by Abrha

}
