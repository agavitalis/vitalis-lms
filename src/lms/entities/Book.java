package lms.entities;

import java.util.List;

public class Book {

	private String title;
	private List<String> authors;
	private String isbn;
	private int publicationYear;
	private String language;
	private String publisher;
	private List<String> genres;
	private int pageCount;
	private String summary;
	private String format;
	private Boolean availability;

	public Book() {
	}

	public Book(String title, List<String> authors, String isbn, int publicationYear, String language, String publisher,
			List<String> genres, int pageCount, String summary, String format, Boolean availability) {
		this.title = title;
		this.authors = authors;
		this.isbn = isbn;
		this.publicationYear = publicationYear;
		this.language = language;
		this.publisher = publisher;
		this.genres = genres;
		this.pageCount = pageCount;
		this.summary = summary;
		this.format = format;
		this.availability = availability;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getAuthors() {
		return authors;
	}

	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public Boolean getAvailability() {
		return availability;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Book {" + "title='" + title + '\'' + ", authors=" + authors + ", isbn='" + isbn + '\''
				+ ", publicationYear=" + publicationYear + ", language='" + language + '\'' + ", publisher='"
				+ publisher + '\'' + ", genres=" + genres + ", pageCount=" + pageCount + ", summary='" + summary + '\''
				+ ", format='" + format + '\'' + ", availability="
				+ (availability != null ? (availability ? "Available" : "Not Available") : "Unknown") + '}';
	}

}
