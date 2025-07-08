package allBooks;

import java.time.Year;

abstract public class Book {
    String isbn;
    String title;
    Year publishYear;
    double price;

    public Book(String isbn, String title, Year publishYear, double price) {
        this.isbn = isbn;
        this.title = title;
        this.publishYear = publishYear;
        this.price = price;
    }

    public double getPrice() {
        return price;
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
