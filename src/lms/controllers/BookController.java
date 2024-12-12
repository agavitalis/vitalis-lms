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

	public static void browseCatalog() {
		BookService bookService = new BookService();
		List<Book> catalog = null;
		try {
			catalog = bookService.getBooks();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (catalog == null) {
			System.out.println("The catalog is empty. Please check back later.");
			return;
		}

		System.out.println("\nAvailable Books:");
		catalog.forEach(book -> System.out.println("- Title: " + book.getTitle() + ", Author: "
				+ String.join(", ", book.getAuthors()) + ", ISBN: " + book.getIsbn()));
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

		if (user == null)
			return;

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
			if (book != null) {
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

		if (user == null)
			return;

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
			if (book != null) {
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
			String title = getInput(scanner, "Enter book title: ", "Title cannot be empty. Enter book title: ");

			String authorsInput = getInput(scanner, "Enter authors (comma-separated): ",
					"Authors cannot be empty. Enter authors (comma-separated): ");
			List<String> authors = List.of(authorsInput.split("\\s*,\\s*"));

			String isbn = getInput(scanner, "Enter ISBN: ", "ISBN must be numeric and cannot be empty. Enter ISBN: ",
					"\\d+");

			String pubYearInput = getInput(scanner, "Enter publication year: ",
					"Publication year must be a valid 4-digit year. Enter publication year: ", "\\d{4}");
			int publicationYear = Integer.parseInt(pubYearInput);

			String language = getInput(scanner, "Enter language: ", "Language cannot be empty. Enter language: ");

			String publisher = getInput(scanner, "Enter publisher: ", "Publisher cannot be empty. Enter publisher: ");

			String genresInput = getInput(scanner, "Enter genres (comma-separated): ",
					"Genres cannot be empty. Enter genres (comma-separated): ");
			List<String> genres = List.of(genresInput.split("\\s*,\\s*"));

			String pageCountInput = getInput(scanner, "Enter page count: ",
					"Page count must be numeric and cannot be empty. Enter page count: ", "\\d+");
			int pageCount = Integer.parseInt(pageCountInput);

			String summary = getInput(scanner, "Enter summary: ", "Summary cannot be empty. Enter summary: ");

			String format = getInput(scanner, "Enter format: ", "Format cannot be empty. Enter format: ");

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
			String isbn = getInput(scanner, "Enter the ISBN of the book to update: ",
					"ISBN must be numeric and cannot be empty. Enter ISBN: ", "\\d+");

			Book book = bookService.getBook(isbn);
			if (book == null) {
				System.out.println("Book not found with the given ISBN.");
				return;
			}

			System.out.println("Updating book details. Leave a field empty to keep its current value.");

			updateField(scanner, "title", book.getTitle(), book::setTitle);
			updateField(scanner, "authors (comma-separated)", String.join(", ", book.getAuthors()),
					input -> book.setAuthors(List.of(input.split("\\s*,\\s*"))));
			updateField(scanner, "publication year", String.valueOf(book.getPublicationYear()),
					input -> book.setPublicationYear(Integer.parseInt(input)), "\\d{4}");
			updateField(scanner, "language", book.getLanguage(), book::setLanguage);
			updateField(scanner, "publisher", book.getPublisher(), book::setPublisher);
			updateField(scanner, "genres (comma-separated)", String.join(", ", book.getGenres()),
					input -> book.setGenres(List.of(input.split("\\s*,\\s*"))));
			updateField(scanner, "page count", String.valueOf(book.getPageCount()),
					input -> book.setPageCount(Integer.parseInt(input)), "\\d+");
			updateField(scanner, "format", book.getFormat(), book::setFormat);
			updateField(scanner, "availability (true/false)", String.valueOf(book.getAvailability()),
					input -> book.setAvailability(Boolean.parseBoolean(input)), "true|false");

			// Save the updated book
			bookService.updateBook(book);
			System.out.println("Book updated successfully!");

		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}

	private static void updateField(Scanner scanner, String fieldName, String currentValue,
			java.util.function.Consumer<String> updateAction) {
		updateField(scanner, fieldName, currentValue, updateAction, null);
	}

	private static void updateField(Scanner scanner, String fieldName, String currentValue,
			java.util.function.Consumer<String> updateAction, String regex) {
		System.out.print("Current " + fieldName + ": " + currentValue + ". Enter new " + fieldName + ": ");
		String input = scanner.nextLine().trim();

		if (!input.isEmpty()) {
			if (regex != null && !input.matches(regex)) {
				System.out.println(fieldName + " does not match the required format. Keeping the current value.");
			} else {
				updateAction.accept(input);
			}
		}
	}

	private static String getInput(Scanner scanner, String prompt, String errorMessage) {
		String input;
		do {
			System.out.print(prompt);
			input = scanner.nextLine().trim();
			if (input.isEmpty()) {
				System.out.println(errorMessage);
			}
		} while (input.isEmpty());
		return input;
	}

	private static String getInput(Scanner scanner, String prompt, String errorMessage, String regex) {
		String input;
		do {
			System.out.print(prompt);
			input = scanner.nextLine().trim();
			if (input.isEmpty() || !input.matches(regex)) {
				System.out.println(errorMessage);
			}
		} while (input.isEmpty() || !input.matches(regex));
		return input;
	}

}
