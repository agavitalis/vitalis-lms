package lms.controllers;

import java.io.IOException;
import java.util.List;
import lms.entities.Book;
import lms.services.BookService;

public class BookController {

	public static List<Book> getBooksCatalog() {

		BookService bookService = new BookService();
		try {
		
			System.out.println("Here is a list of our catalogs:");
			
			for (Book book : bookService.getBooks()) {
			    System.out.println("==========================================");
			    System.out.println("Title: " + book.getTitle());
			    System.out.println("Authors: " + String.join(", ", book.getAuthors()));
			    System.out.println("ISBN: " + book.getIsbn());
			    System.out.println("Publication Year: " + book.getPublicationYear());
			    System.out.println("Language: " + book.getLanguage());
			    System.out.println("Publisher: " + book.getPublisher());
			    System.out.println("Genres: " + String.join(", ", book.getGenres()));
			    System.out.println("Page Count: " + book.getPageCount());
			    System.out.println("Format: " + book.getFormat());
			    System.out.println("Availability: " + book.getAvailability());
			    System.out.println("Summary: " + book.getSummary());
			}

		} catch (IOException e) {
			System.out.println("Internal server error: " + e.getMessage());
		}

		return null;
	}

}
