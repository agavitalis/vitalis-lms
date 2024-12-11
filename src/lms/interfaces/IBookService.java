package lms.interfaces;
import java.io.IOException;
import java.util.List;
import lms.entities.Book;

public interface IBookService {
   
    void createBook(Book book) throws IOException;

    Book getBook(String isbn) throws IOException;

    List<Book> getBooks() throws IOException;
    
    void updateBook(Book updatedBook) throws IOException;
    
    void deleteBook(String isbn) throws IOException;
    
    Book issueBook(String isbn) throws IOException;
    
    Book returnBook(String isbn) throws IOException;
}
