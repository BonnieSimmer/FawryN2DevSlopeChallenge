package allBooks;

abstract public class Book {
    String isbn;
    String title;
    int year;
    double price;

    public Book(String isbn, String title, int year, double price) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.price = price;
    }

}
