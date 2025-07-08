package allBooks;

import java.time.Year;

public class PaperBook extends Book implements Shippable {
    private int stock;
    public PaperBook(String isbn, String title, Year year, double price, int stock) {
        super(isbn, title, year, price);
        this.stock = stock;
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
}
