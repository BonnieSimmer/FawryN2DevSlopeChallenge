package allBooks;

public interface Emailable extends Sellable {
    void sendToEmail(String email);
    String getFileType();
    void setFileType(String fileType);

}
