package allBooks;

import java.time.Year;

abstract public class Book {
    String isbn;
    String title;
    Year publishYear;

    public Book(String isbn, String title, Year publishYear) throws IllegalArgumentException {
        if (isbn == null || title == null || publishYear == null) {
            throw new IllegalArgumentException("All arguments must be non-null.");
        }
        if (isbn.isBlank() || title.isBlank()) {
            throw new IllegalArgumentException("All arguments must be non-blank.");
        }
        this.isbn = isbn;
        this.title = title;
        this.publishYear = publishYear;
    }


    public Year getPublishYear() {
        return publishYear;
    }
    public String getTitle() {
        return title;
    }
    public String getIsbn() {
        return isbn;
    }
}
