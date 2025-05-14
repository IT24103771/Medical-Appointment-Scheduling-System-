package Medical.Appointment.System.Back_end.AdminNotification;

public class Notification {
    private String recipient;
    private String date;
    private String message;
    private String method;

    public Notification(String recipient, String date, String message, String method) {
        this.recipient = recipient;
        this.date = date;
        this.message = message;
        this.method = method;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return String.format(
                "Recipient: %s%nDate: %s%nMessage: %s%nMethod: %s%n%n",
                recipient, date, message, method
        );
    }

    // ADD THIS METHOD:
    public static Notification fromFormattedString(String entry) {
        String[] lines = entry.split("\n");
        if (lines.length < 4) return null;

        String recipient = lines[0].replace("Recipient: ", "").trim();
        String date = lines[1].replace("Date: ", "").trim();
        String message = lines[2].replace("Message: ", "").trim();
        String method = lines[3].replace("Method: ", "").trim();

        return new Notification(recipient, date, message, method);
    }
}

