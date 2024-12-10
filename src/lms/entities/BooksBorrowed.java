package lms.entities;

import java.time.LocalDate;
import java.util.Date;

public class BooksBorrowed {
    private User user; 
    private Book book;
    private LocalDate dateBorrowed;
    private LocalDate dueDate;
    private LocalDate dateReturned;

 
    public BooksBorrowed() {}
    
    public BooksBorrowed(User user, Book book, LocalDate dateBorrowed, LocalDate dueDate, LocalDate dateReturned) {
        this.user = user;
        this.book = book;
        this.dateBorrowed = dateBorrowed;
        this.dueDate = dueDate;
        this.dateReturned = dateReturned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getDateBorrowed() {
        return dateBorrowed;
    }

    public void setDateBorrowed(LocalDate dateBorrowed) {
        this.dateBorrowed = dateBorrowed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDate getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(LocalDate dateReturned) {
        this.dateReturned = dateReturned;
    }

    @Override
    public String toString() {
        return "BooksBorrowed{" +
                "user=" + user.getFirstName() + " " + user.getLastName() +
                ", book=" + book.getTitle() +
                ", dateBorrowed=" + dateBorrowed +
                ", dueDate=" + dueDate +
                ", dateReturned=" + (dateReturned != null ? dateReturned : "Not returned") +
                '}';
    }
}
