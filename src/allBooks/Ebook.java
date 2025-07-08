package allBooks;

import java.time.Year;

public class Ebook extends Book implements Emailable {
    String fileType;

    public Ebook(String isbn, String title, Year year, double price, String fileType) {
        super(isbn, title, year, price);
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
    @Override
    public void sendToEmail() {
        System.out.println(title + " has been sent to your email.");
    }
}
