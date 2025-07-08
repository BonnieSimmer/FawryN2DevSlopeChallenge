package allBooks;

public interface Shippable extends Sellable{
    void ship(String address);
    int getStock();
    void setStock(int stock) throws IllegalArgumentException;
}
