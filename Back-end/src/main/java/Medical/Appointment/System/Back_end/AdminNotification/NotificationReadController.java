package Medical.Appointment.System.Back_end.AdminNotification;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/notifications")
public class NotificationReadController {

    private final String NOTIFICATION_FILE = "src/main/java/Medical/Appointment/System/Back_end/AdminNotification/notification.txt";

    @GetMapping("/user/{username}")
    public List<Map<String, String>> getUserNotifications(@PathVariable String username) {
        List<Map<String, String>> userNotifications = new ArrayList<>();

        try {
            File file = new File(NOTIFICATION_FILE);
            if (!file.exists()) return userNotifications;

            List<String> lines = Files.readAllLines(Paths.get(NOTIFICATION_FILE));
            Map<String, String> currentNotif = new HashMap<>();

            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("To:")) {
                    currentNotif.put("recipient", line.substring(3).trim());
                } else if (line.startsWith("Message:")) {
                    currentNotif.put("message", line.substring(8).trim());
                } else if (line.startsWith("Method:")) {
                    currentNotif.put("method", line.substring(7).trim());
                } else if (line.startsWith("DateTime:")) {
                    currentNotif.put("date", line.substring(9).trim());
                } else if (line.isEmpty() && !currentNotif.isEmpty()) {
                    if (username.equalsIgnoreCase(currentNotif.get("recipient"))) {
                        userNotifications.add(new HashMap<>(currentNotif));
                    }
                    currentNotif.clear();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return userNotifications;
    }
}
