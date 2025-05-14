package Medical.Appointment.System.Back_end.AdminNotification;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notifications")
public class NotificationReadController {

    private final String NOTIFICATION_FILE = "C:\\Users\\User\\Documents\\OOP project\\Medical-Appointment-Scheduling-System-\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\AdminNotification\\notification.txt";

    @GetMapping("/user/{username}")
    public List<Map<String, String>> getUserNotifications(@PathVariable String username) {
        List<Map<String, String>> userNotifications = new ArrayList<>();

        try {
            File file = new File(NOTIFICATION_FILE);
            if (!file.exists()) {
                return userNotifications; // empty list
            }

            List<String> lines = Files.readAllLines(Paths.get(NOTIFICATION_FILE));
            for (String line : lines) {
                // Assuming each line format: username;date;message;method
                String[] parts = line.split(";");
                if (parts.length == 4 && parts[0].equalsIgnoreCase(username)) {
                    Map<String, String> notif = new HashMap<>();
                    notif.put("date", parts[1]);
                    notif.put("message", parts[2]);
                    notif.put("method", parts[3]);
                    userNotifications.add(notif);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userNotifications;
    }
}
