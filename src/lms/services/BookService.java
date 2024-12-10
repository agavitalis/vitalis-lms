package lms.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lms.entities.Book;
import lms.interfaces.IBookService;

public class BookService implements IBookService {
	
    private static final String FILE_PATH = "data/books.json";
	
	public List<Book> getBooks() throws IOException {
		 ObjectMapper mapper = new ObjectMapper();
         List<Book> books = mapper.readValue(new File(FILE_PATH), new TypeReference<List<Book>>() {});
         return books;
	}

	public void createBook(Book newBook) throws IOException {
		
		List<Book> books = getBooks();
        
        for (Book book : books) {
            if (book.getIsbn().equals(newBook.getIsbn())) {
                System.out.println("Error: Book already registered.");
                return;
            }
        }

        books.add(newBook);
        saveBooksToFile(books);
    }

	public Book getBook(String isbn)throws IOException {
    	List<Book> books = getBooks();
        for (Book book : books) {
            if (isbn.equals(book.getIsbn())) {
                return book; 
            }
        }
        return null; 
    }

	public void updateBook(Book updatedBook) throws IOException {
	    List<Book> books = getBooks();
	    boolean found = false;

	    for (Book book : books) {
	        if (book.getIsbn().equals(updatedBook.getIsbn())) {
	            book.setTitle(updatedBook.getTitle());
	            book.setAuthors(updatedBook.getAuthors());
	            book.setPublicationYear(updatedBook.getPublicationYear());
	            book.setLanguage(updatedBook.getLanguage());
	            book.setPublisher(updatedBook.getPublisher());
	            book.setGenres(updatedBook.getGenres());
	            book.setPageCount(updatedBook.getPageCount());
	            book.setSummary(updatedBook.getSummary());
	            book.setFormat(updatedBook.getFormat());
	            book.setAvailability(updatedBook.getAvailability());
	            found = true;
	            break;
	        }
	    }


	    if (!found) {
	        System.out.println("Error: Book not found.");
	        return;
	    }

	    saveBooksToFile(books);
	    System.out.println("Book updated successfully.");
	}

	public void deleteBook(String isbn) throws IOException {
	    List<Book> books = getBooks();
	    boolean removed = books.removeIf(book -> book.getIsbn().equals(isbn));

	    if (!removed) {
	        System.out.println("Error: Book not found.");
	        return;
	    }

	    saveBooksToFile(books);
	    System.out.println("Book deleted successfully.");
	}

	public void issueBook(String isbn) throws IOException {
	    List<Book> books = getBooks();
	    for (Book book : books) {
	        if (book.getIsbn().equals(isbn)) {
	            if (book.getAvailability()) {
	                System.out.println("Error: Book is already issued.");
	                return;
	            }
	            book.setAvailability(false);
	            saveBooksToFile(books);
	            System.out.println("Book issued successfully.");
	            return;
	        }
	    }
	    System.out.println("Error: Book not found.");
	}

	public void returnBook(String isbn) throws IOException {
	    List<Book> books = getBooks();
	    for (Book book : books) {
	        if (book.getIsbn().equals(isbn)) {
	            if (!book.getAvailability()) {
	                System.out.println("Error: Book is not issued.");
	                return;
	            }
	            book.setAvailability(true);
	            saveBooksToFile(books);
	            System.out.println("Book returned successfully.");
	            return;
	        }
	    }
	    System.out.println("Error: Book not found.");
	}

	private void saveBooksToFile(List<Book> books) throws IOException {
	    File file = new File(FILE_PATH);
	    ObjectMapper mapper = new ObjectMapper();

	    try {
	        mapper.writerWithDefaultPrettyPrinter().writeValue(file, books);
	    } catch (IOException e) {
	        System.out.println("An error occurred while saving the books.");
	        e.printStackTrace();
	    }
	}

}
