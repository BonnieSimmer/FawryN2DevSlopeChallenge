import allBooks.*;

import java.time.Year;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        BookStore quantum = new BookStore();
        ArrayList<Book> books = new ArrayList<>();
        books.add(new Ebook("E102","Children of Eden", Year.of(2018), 19.99, "pdf"));
        books.add(new Ebook("E103","The Great Gatsby", Year.of(1937), 15.99, "epub"));
        books.add(new PaperBook("P101","The Hobbit", Year.of(2020), 20.75, 10));
        books.add(new PaperBook("P102","The Lord of the Rings", Year.of(2024), 50.25, 1));
        books.add(new ShowcaseDemoBook("S101","The Hitchhiker's Guide to the Galaxy", Year.of(2003)));
        books.add(new ShowcaseDemoBook("S102","The Hobbit: An Unexpected Journey", Year.of(2023)));

        quantum.addBooks(books);
        quantum.printInventory();
        quantum.checkForOutdatedBooks(); // Will remove E103 and S101 names were too long for me to write
        quantum.printInventory();

        try {
            quantum.buySingleBook("E102", 1, "something@haga.com", "123 Main St");
            quantum.buySingleBook("P102", 1, "something@somethingelse.com", "456 Main St");
            quantum.buySingleBook("S101", 1, "test@test.com", "789 Main St");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getClass().getName() + ": " +e.getMessage() + "\n");
        } finally {
            quantum.printInventory();
            quantum.removeBook(books.getLast()); // just to test removing
            quantum.printInventory();
        }

    }
}
