package allBooks;

import java.time.Year;

public class PaperBook extends Book implements Shippable {
    private int stock;
    private double price;
    public PaperBook(String isbn, String title, Year year, double price, int stock) {
        super(isbn, title, year);
        this.price = price;
        this.stock = stock;
    }
    public PaperBook(String isbn, String title, Year year, double price) {
        super(isbn, title, year);
        this.price = price;
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setStock(int stock) throws IllegalArgumentException {
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.stock = stock;
    }

    @Override
    public void ship(String address) {
        System.out.println(title +" is being shipped to " + address + ".");
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}
