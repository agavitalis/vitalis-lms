package lms.services;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lms.entities.Book;
import lms.entities.User;
import lms.interfaces.IBookService;

public class BookService implements IBookService {
	
    private static final String FILE_PATH = "data/books.json";
	
	public List<Book> getBooks() throws IOException {
		 ObjectMapper mapper = new ObjectMapper();
         List<Book> books = mapper.readValue(new File(FILE_PATH), new TypeReference<List<Book>>() {});
         return books;
	}

	public void saveBook(Book book) {
		
	}

	public Book getBook(String isbn) {
		return null;
	}


}
