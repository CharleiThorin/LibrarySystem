package library.Model;

import java.time.LocalDate;

public class RecData {
    private String ISBN;
    private String title;
    private String author;
    private String name;
    private LocalDate dueDate;

    public  RecData(String ISBN, String title, String author, String name , LocalDate due) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.name = name;
        this.setDueDate(due);
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }
}
