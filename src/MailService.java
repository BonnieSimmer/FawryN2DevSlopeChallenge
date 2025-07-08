import allBooks.Emailable;

public class MailService {
    Emailable emailableItem;
    String email;
    public MailService(Emailable emailable, String email) {
        this.emailableItem = emailable;
        this.email = email;
    }

    public void sendEmail() {
        emailableItem.sendToEmail(email);
    }

}
