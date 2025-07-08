package allBooks;

public interface Shippable {
    void ship(String address);
    int getStock();
    void setStock(int stock) throws IllegalArgumentException;
}
