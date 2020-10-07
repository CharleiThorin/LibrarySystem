package library.Model;

public class Employee {
    private UserRole access;
    private String userName;

    public Employee(UserRole access, String userName) {
        this.access = access;
        this.userName = userName;
    }

    public UserRole getAccess() {
        return access;
    }

    public void setAccess(UserRole access) {
        this.access = access;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void addNewMember(LibraryMember m) {
        // TO DO
    }

    public void editMember(LibraryMember m) {
        // TO DO
    }

    public void addNewBook(Book b) {
        // TO DO
    }

    public void checkOutBook(Book b, LibraryMember m) {
       // TO DO
    }

}
