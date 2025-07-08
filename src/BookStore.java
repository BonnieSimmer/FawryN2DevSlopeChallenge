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

    // for ebooks and quantity i actually looked into it and some websites are allowed to sell you multiple files to the same ebook it is weird but it happens mainly in small businesses.
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
            if (wantedBook instanceof Emailable) {
                System.out.println("You have successfully placed your order for " + wantedBook.getTitle() + ".");
                System.out.println("The paid amount was: " + ((Sellable)wantedBook).getPrice());
                System.out.println("Check your email for your order as it will be there momentarily.");
                new MailService((Emailable) wantedBook, email).sendEmail();
                // I will be ignoring the quantity for the email-able content as it doesn't seem fair to make them pay for multiples of it.
            } else if (wantedBook instanceof Shippable) {
                if (quantity >((Shippable) wantedBook).getStock() ) {
                    throw new IllegalArgumentException("Sorry, but we only have " + ((Shippable) wantedBook).getStock() + " copies of " + wantedBook.getTitle() + ".");
                }
                System.out.println("You have successfully placed your order for "+ quantity + " copies of " + wantedBook.getTitle() + ".");
                System.out.println("The paid amount was: " + ((Sellable)wantedBook).getPrice()*(quantity));
                System.out.println("Your order will be shipped to your address.");
                new ShippingService((Shippable) wantedBook, address).ship();
                int leftOverQuantity = ((Shippable) wantedBook).getStock() - quantity;
                if (leftOverQuantity > 0) {
                    ((Shippable) wantedBook).setStock(leftOverQuantity);
                } else {
                    removeBook(wantedBook);
                }
            } else {
                throw new IllegalArgumentException("That book is not for sale.");
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

    public void addBooks(ArrayList<Book> books) {
        inventory.addAll(books);
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

    public void printInventory() {
        System.out.println("*** Inventory ***");
        for (Book book : inventory) {
            System.out.println(book.getIsbn() + " " + book.getTitle() + " " + book.getPublishYear());
        }
        System.out.println("****************");
    }

}
