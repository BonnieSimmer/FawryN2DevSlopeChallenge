import allBooks.*;

import java.time.Year;
import java.util.ArrayList;

public class BookStore {
    private ArrayList<Book> inventory;
    static int yearsToBeOutDated = 10;

    public BookStore() {
        inventory = new ArrayList<>();
    }
    public BookStore(ArrayList<Book> inventory) {
        this.inventory = inventory;
    }

    public void buySingleBook (String isbn, int quantity, String email, String address) throws IllegalArgumentException{
        if (quantity < 1) {
            throw new IllegalArgumentException("You must buy at least one book.");
            // I am assuming in the question you meant one kind of book and not one cause then quantity wouldn't make sense.
        }

        Book wantedBook = null;
        for (Book book : inventory) {
            if (book.getIsbn().equals(isbn)) {
                wantedBook = book;
                break;
            }
        }
        if (wantedBook != null) {
            if (wantedBook.getPublishYear().plusYears(yearsToBeOutDated).compareTo(Year.now()) < 0) {
                System.out.println("Sorry, but " + wantedBook.getTitle() + " is out of date.");
                removeBook(wantedBook);
            }

        } else {
            throw new IllegalArgumentException("That book is not in our inventory.");
        }
    }

    public void checkForOutdatedBooks() {
        for (Book book : inventory) {
            if (book.getPublishYear().plusYears(yearsToBeOutDated).compareTo(Year.now()) < 0) {
                System.out.println(book.getTitle() + " is out of date.");
                removeBook(book);
            }
        }
    }

    public ArrayList<Book> getInventory() {
        return inventory;
    }
    public void addBook(Book book) {
        inventory.add(book);
    }

    public void removeBook(Book book) {
        inventory.remove(book);
    }
    static public int getYearsToBeOutDated() {
        return yearsToBeOutDated;
    }
    static public void setYearsToBeOutDated(int years) {
        yearsToBeOutDated = years;
        // I just used 10 but I guess the library could change their mind when they like about what is considered outdated.
    }
}
