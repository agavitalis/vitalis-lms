package lms.interfaces;
import java.io.IOException;
import java.util.List;
import lms.entities.Book;

public interface IBookService {
    /**
     * Save a new book.
     * 
     * @param book The book to be saved.
     */
    void saveBook(Book book) throws IOException;

    /**
     * Retrieve a book by its isbn.
     * 
     * @param isbn The unique identifier of the book.
     * @return The book if found, or null if not found.
     */
    Book getBook(String isbn) throws IOException;

    /**
     * Retrieve all books.
     * 
     * @return A list of all books.
     */
    List<Book> getBooks() throws IOException;
}
