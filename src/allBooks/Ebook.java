package allBooks;

import java.time.Year;

public class Ebook extends Book implements Emailable {
    private String fileType;
    private double price;

    public Ebook(String isbn, String title, Year year, double price, String fileType) throws IllegalArgumentException {
        super(isbn, title, year);
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive.");
        }
        this.price = price;
        this.fileType = fileType;
    }

    @Override
    public String getFileType() {
        return fileType;
    }
    @Override
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
    @Override
    public void sendToEmail(String email) {
        System.out.println(title + " has been sent to " + email + ".");
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) throws IllegalArgumentException {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive.");
        }
        this.price = price;
    }
}
