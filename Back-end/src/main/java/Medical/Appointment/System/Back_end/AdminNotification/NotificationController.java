package Medical.Appointment.System.Back_end.AdminNotification;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final String filePath = "src/main/java/Medical/Appointment/System/Back_end/AdminNotification/notification.txt";

    @PostMapping("/send")
    public String sendNotification(@RequestBody Notification notification) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write("Notification:\n");
            writer.write("To: " + notification.getRecipient() + "\n");
            writer.write("Message: " + notification.getMessage() + "\n");
            writer.write("Method: " + notification.getMethod() + "\n");
            writer.write("DateTime: " + LocalDateTime.now() + "\n");
            writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
            return "Failed to send notification: " + e.getMessage();
        }
        return "Notification sent.";
    }

    @GetMapping("/all")
    public String getAllNotifications() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return "";
            }
            return new String(java.nio.file.Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            return "Error reading notifications: " + e.getMessage();
        }
    }
}
