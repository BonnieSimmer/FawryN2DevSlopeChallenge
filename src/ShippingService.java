import allBooks.Shippable;

public class ShippingService {
    Shippable shippableItem;
    String address;
    public ShippingService(Shippable shippable, String address) {
        this.shippableItem = shippable;
        this.address = address;
    }
    public void ship() {
        shippableItem.ship(address);
    }
}
