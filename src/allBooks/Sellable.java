package allBooks;

public interface Sellable {
    double getPrice();
    void setPrice(double price) throws IllegalArgumentException;
}
