package lms.interfaces;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import lms.entities.Book;
import lms.entities.BooksBorrowed;
import lms.entities.User;

public interface IBooksBorrowedService {
	
	List<BooksBorrowed> getAllBorrowedBooks() throws IOException;
	
	void create(User user, Book book, LocalDate dueDate) throws IOException;
	
	void returnBook(User user, Book book) throws IOException;
	 
}
