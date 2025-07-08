package allBooks;

import java.time.Year;

abstract public class Book {
    String isbn;
    String title;
    Year publishYear;

    public Book(String isbn, String title, Year publishYear) {
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
