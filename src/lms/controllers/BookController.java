package lms.controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import lms.entities.Book;
import lms.entities.User;
import lms.services.BookService;
import lms.services.BooksBorrowedService;
import lms.services.UserService;

public class BookController {

	public static List<Book> getBooksCatalog() {

		BookService bookService = new BookService();
		try {

			System.out.println("Here is a list of our catalogs:");

			for (Book book : bookService.getBooks()) {
				System.out.println("\n--------------------------------");
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

	public static void searchBook(Scanner scanner) {

		String userSearchTerm = "";

		while (userSearchTerm.isEmpty()) {
			System.out.print("Enter a book isbn to search: ");
			userSearchTerm = scanner.nextLine();
			if (userSearchTerm.isEmpty()) {
				System.out.println("Your search term is empty. Please enter a valid search term.");
			}
		}

		BookService bookService = new BookService();
		try {
			Book book = bookService.getBook(userSearchTerm);
			System.out.println("Search result:" + book.toString());
		} catch (IOException e) {
			System.out.println("Internal server error: " + e.getMessage());
		}
	}

	public static void borrowBook(Scanner scanner) throws IOException {

		String userInput = "";
		UserService userService = new UserService();
		User user = userService.getLoggedInUser();
		
		if (user == null) return;

		while (userInput.isEmpty()) {
			System.out.print("Enter a book isbn to borrow: ");
			userInput = scanner.nextLine();
			if (userInput.isEmpty()) {
				System.out.println("Your input term is empty. Please enter a valid isbn term.");
			}
		}

		BookService bookService = new BookService();
		BooksBorrowedService booksBorrowedService = new BooksBorrowedService();
		
		try {
			Book book = bookService.issueBook(userInput);
			if(book != null) {
				LocalDate today = LocalDate.now();
		        LocalDate dueDate = today.plusDays(14);
				booksBorrowedService.create(user, book, dueDate);
			}
			System.out.println("Book successfully issued");
		} catch (IOException e) {
			System.out.println("Internal server error: " + e.getMessage());
		}
	}

	public static void returnBook(Scanner scanner) throws IOException {

		String userInput = "";
		UserService userService = new UserService();
		User user = userService.getLoggedInUser();
		
		if (user == null) return;

		while (userInput.isEmpty()) {
			System.out.print("Enter a book isbn to return: ");
			userInput = scanner.nextLine();
			if (userInput.isEmpty()) {
				System.out.println("Your input term is empty. Please enter a valid isbn term.");
			}
		}

		BookService bookService = new BookService();
		BooksBorrowedService booksBorrowedService = new BooksBorrowedService();
		
		try {
			Book book = bookService.returnBook(userInput);
			if(book != null) {
				booksBorrowedService.returnBook(user, book);
			}
			System.out.println("Book successfully returned");
		} catch (IOException e) {
			System.out.println("Internal server error: " + e.getMessage());
		}
	}

	public static void createBook(Scanner scanner) {
		BookService bookService = new BookService();
		try {
			System.out.print("Enter book title: ");
			String title = scanner.nextLine().trim();
			while (title.isEmpty()) {
				System.out.print("Title cannot be empty. Enter book title: ");
				title = scanner.nextLine().trim();
			}

			System.out.print("Enter authors (comma-separated): ");
			String authorsInput = scanner.nextLine().trim();
			while (authorsInput.isEmpty()) {
				System.out.print("Authors cannot be empty. Enter authors (comma-separated): ");
				authorsInput = scanner.nextLine().trim();
			}
			List<String> authors = List.of(authorsInput.split("\\s*,\\s*"));

			System.out.print("Enter ISBN: ");
			String isbn = scanner.nextLine().trim();
			while (isbn.isEmpty() || !isbn.matches("\\d+")) {
				System.out.print("ISBN must be numeric and cannot be empty. Enter ISBN: ");
				isbn = scanner.nextLine().trim();
			}

			System.out.print("Enter publication year: ");
			String pubYearInput = scanner.nextLine().trim();
			int publicationYear = 0;
			while (pubYearInput.isEmpty() || !pubYearInput.matches("\\d{4}")) {
				System.out.print("Publication year must be a valid 4-digit year. Enter publication year: ");
				pubYearInput = scanner.nextLine().trim();
			}
			publicationYear = Integer.parseInt(pubYearInput);

			System.out.print("Enter language: ");
			String language = scanner.nextLine().trim();
			while (language.isEmpty()) {
				System.out.print("Language cannot be empty. Enter language: ");
				language = scanner.nextLine().trim();
			}

			System.out.print("Enter publisher: ");
			String publisher = scanner.nextLine().trim();
			while (publisher.isEmpty()) {
				System.out.print("Publisher cannot be empty. Enter publisher: ");
				publisher = scanner.nextLine().trim();
			}

			System.out.print("Enter genres (comma-separated): ");
			String genresInput = scanner.nextLine().trim();
			while (genresInput.isEmpty()) {
				System.out.print("Genres cannot be empty. Enter genres (comma-separated): ");
				genresInput = scanner.nextLine().trim();
			}
			List<String> genres = List.of(genresInput.split("\\s*,\\s*"));

			System.out.print("Enter page count: ");
			String pageCountInput = scanner.nextLine().trim();
			int pageCount = 0;
			while (pageCountInput.isEmpty() || !pageCountInput.matches("\\d+")) {
				System.out.print("Page count must be numeric and cannot be empty. Enter page count: ");
				pageCountInput = scanner.nextLine().trim();
			}
			pageCount = Integer.parseInt(pageCountInput);

			System.out.print("Enter summary: ");
			String summary = scanner.nextLine().trim();
			while (summary.isEmpty()) {
				System.out.print("Summary cannot be empty. Enter summary: ");
				summary = scanner.nextLine().trim();
			}

			System.out.print("Enter format: ");
			String format = scanner.nextLine().trim();
			while (format.isEmpty()) {
				System.out.print("Format cannot be empty. Enter format: ");
				format = scanner.nextLine().trim();
			}

			Boolean availability = true;

			Book newBook = new Book(title, authors, isbn, publicationYear, language, publisher, genres, pageCount,
					summary, format, availability);
			bookService.createBook(newBook);

			System.out.println("Book created successfully!");
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		} catch (NumberFormatException e) {
			System.out.println("Invalid number format entered. Please try again.");
		}
	}

	public static void deleteBook(Scanner scanner) {
		System.out.print("Enter ISBN of the book to delete: ");
		String isbn = scanner.nextLine();

		BookService bookService = new BookService();
		try {
			bookService.deleteBook(isbn);
			System.out.println("Book deleted successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	public static void updateBook(Scanner scanner) {
		BookService bookService = new BookService();

		try {
			System.out.print("Enter the ISBN of the book to update: ");
			String isbn = scanner.nextLine().trim();
			while (isbn.isEmpty() || !isbn.matches("\\d+")) {
				System.out.print("ISBN must be numeric and cannot be empty. Enter ISBN: ");
				isbn = scanner.nextLine().trim();
			}

			Book book = bookService.getBook(isbn);
			if (book == null) {
				System.out.println("Book not found with the given ISBN.");
				return;
			}

			System.out.println("Updating book details. Leave a field empty to keep its current value.");

			System.out.print("Current Title: " + book.getTitle() + ". Enter new title: ");
			String title = scanner.nextLine().trim();
			if (!title.isEmpty()) {
				book.setTitle(title);
			}

			System.out.print("Current Authors: " + String.join(", ", book.getAuthors())
					+ ". Enter new authors (comma-separated): ");
			String authorsInput = scanner.nextLine().trim();
			if (!authorsInput.isEmpty()) {
				List<String> authors = List.of(authorsInput.split("\\s*,\\s*"));
				book.setAuthors(authors);
			}

			System.out
					.print("Current Publication Year: " + book.getPublicationYear() + ". Enter new publication year: ");
			String pubYearInput = scanner.nextLine().trim();
			if (!pubYearInput.isEmpty()) {
				while (!pubYearInput.matches("\\d{4}")) {
					System.out.print("Publication year must be a valid 4-digit year. Enter new publication year: ");
					pubYearInput = scanner.nextLine().trim();
				}
				book.setPublicationYear(Integer.parseInt(pubYearInput));
			}

			System.out.print("Current Language: " + book.getLanguage() + ". Enter new language: ");
			String language = scanner.nextLine().trim();
			if (!language.isEmpty()) {
				book.setLanguage(language);
			}

			System.out.print("Current Publisher: " + book.getPublisher() + ". Enter new publisher: ");
			String publisher = scanner.nextLine().trim();
			if (!publisher.isEmpty()) {
				book.setPublisher(publisher);
			}

			System.out.print("Current Genres: " + String.join(", ", book.getGenres())
					+ ". Enter new genres (comma-separated): ");
			String genresInput = scanner.nextLine().trim();
			if (!genresInput.isEmpty()) {
				List<String> genres = List.of(genresInput.split("\\s*,\\s*"));
				book.setGenres(genres);
			}

			System.out.print("Current Page Count: " + book.getPageCount() + ". Enter new page count: ");
			String pageCountInput = scanner.nextLine().trim();
			if (!pageCountInput.isEmpty()) {
				while (!pageCountInput.matches("\\d+")) {
					System.out.print("Page count must be numeric. Enter new page count: ");
					pageCountInput = scanner.nextLine().trim();
				}
				book.setPageCount(Integer.parseInt(pageCountInput));
			}

			System.out.print("Current Format: " + book.getFormat() + ". Enter new format: ");
			String format = scanner.nextLine().trim();
			if (!format.isEmpty()) {
				book.setFormat(format);
			}

			System.out.print(
					"Current Availability: " + book.getAvailability() + ". Enter new availability (true/false): ");
			String availabilityInput = scanner.nextLine().trim();
			if (!availabilityInput.isEmpty()) {
				while (!availabilityInput.equalsIgnoreCase("true") && !availabilityInput.equalsIgnoreCase("false")) {
					System.out.print("Availability must be 'true' or 'false'. Enter new availability: ");
					availabilityInput = scanner.nextLine().trim();
				}
				book.setAvailability(Boolean.parseBoolean(availabilityInput));
			}

			// Save the updated book
			bookService.updateBook(book);
			System.out.println("Book updated successfully!");

		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

}
