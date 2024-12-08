package lms.interfaces;
import java.util.List;
import lms.entities.Book;

public interface IBookService {
    /**
     * Save a new book.
     * 
     * @param book The book to be saved.
     */
    void saveBook(Book book);

    /**
     * Retrieve a book by its isbn.
     * 
     * @param isbn The unique identifier of the book.
     * @return The book if found, or null if not found.
     */
    Book getBook(String isbn);

    /**
     * Retrieve all books.
     * 
     * @return A list of all books.
     */
    List<Book> getBooks();
}
