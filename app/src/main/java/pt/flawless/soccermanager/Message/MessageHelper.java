package pt.flawless.soccermanager.Message;

public class MessageHelper {
    private String message;

    public MessageHelper() {

    }

    public MessageHelper(String message) {
        this.message = message;
    }

    public static void quickMessage(String message) {
        System.out.println(message);
    }

    public void sendMessage() {
        System.out.println(this.message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
