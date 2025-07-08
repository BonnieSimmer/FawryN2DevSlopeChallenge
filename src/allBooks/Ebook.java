package allBooks;

import java.time.Year;

public class Ebook extends Book implements Emailable {
    private String fileType;
    private double price;

    public Ebook(String isbn, String title, Year year, double price, String fileType) {
        super(isbn, title, year);
        this.price = price;
        this.fileType = fileType;
    }
    public Ebook(String isbn, String title, Year year, double price) {
        super(isbn, title, year);
        this.price = price;
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
    public void setPrice(double price) {
        this.price = price;
    }
}
