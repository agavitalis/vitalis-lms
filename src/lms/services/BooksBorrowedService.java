
package lms.services;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lms.entities.Book;
import lms.entities.BooksBorrowed;
import lms.entities.User;

public class BooksBorrowedService {
	
    private static final String FILE_PATH = "data/books_borrowed.json";

    private List<BooksBorrowed> getAllBorrowedBooks() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return List.of();
        }

        return mapper.readValue(file, new TypeReference<List<BooksBorrowed>>() {});
    }
   
    private void saveBorrowedBooksToFile(List<BooksBorrowed> borrowedBooks) throws IOException {
	    File file = new File(FILE_PATH);
	    ObjectMapper mapper = new ObjectMapper();

	    try {
	        mapper.writerWithDefaultPrettyPrinter().writeValue(file, borrowedBooks);
	    } catch (IOException e) {
	        System.out.println("An error occurred while saving the books.");
	        e.printStackTrace();
	    }
	}


    public void create(User user, Book book, LocalDate dueDate) throws IOException {
        List<BooksBorrowed> borrowedBooks = getAllBorrowedBooks();
        LocalDate today = LocalDate.now();
        BooksBorrowed newEntry = new BooksBorrowed(user, book, today, dueDate, null);
        borrowedBooks.add(newEntry);
        saveBorrowedBooksToFile(borrowedBooks);

        System.out.println("Book successfully borrowed!");
    }

    public void returnBook(User user, Book book) throws IOException {
        List<BooksBorrowed> borrowedBooks = getAllBorrowedBooks();

        // Find the borrowed book
        List<BooksBorrowed> matchingEntries = borrowedBooks.stream()
                .filter(b -> b.getUser().getEmail().equals(user.getEmail()) &&
                                 b.getBook().getIsbn().equals(book.getIsbn()) &&
                                 b.getDateReturned() == null)
                .collect(Collectors.toList());

        if (matchingEntries.isEmpty()) {
            System.out.println("Error: No record found for this user and book.");
            return;
        }

        BooksBorrowed borrowedEntry = matchingEntries.get(0);
        borrowedEntry.setDateReturned(LocalDate.now());
        saveBorrowedBooksToFile(borrowedBooks);

        System.out.println("Book successfully returned!");
    }
}
