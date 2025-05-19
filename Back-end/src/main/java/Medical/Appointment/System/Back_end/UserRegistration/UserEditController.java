package Medical.Appointment.System.Back_end.UserRegistration;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserEditController {

    private final String filePath = "src/main/java/Medical/Appointment/System/Back_end/UserRegistration/Registration.txt";

    @PutMapping("/update")
    public String updateUser(@RequestBody Map<String, String> payload) {
        String oldEmail = payload.get("oldEmail");
        String oldUsername = payload.get("oldUsername");
        String newEmail = payload.get("newEmail");
        String newUsername = payload.get("newUsername");

        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            String[] entries = content.split("(?=New User Registration:)");
            StringBuilder updatedContent = new StringBuilder();
            boolean found = false;

            for (String entry : entries) {
                if (entry.contains("Username: " + oldUsername) && entry.contains("Email: " + oldEmail)) {
                    found = true;
                    // Replace old values with new values
                    entry = entry.replace("Username: " + oldUsername, "Username: " + newUsername)
                            .replace("Email: " + oldEmail, "Email: " + newEmail);
                }
                updatedContent.append(entry);
            }

            if (!found) {
                return "User not found.";
            }

            Files.write(Paths.get(filePath), updatedContent.toString().getBytes());
            return "User updated successfully!";
        } catch (IOException e) {
            return "Error updating user: " + e.getMessage();
        }
    }
}
