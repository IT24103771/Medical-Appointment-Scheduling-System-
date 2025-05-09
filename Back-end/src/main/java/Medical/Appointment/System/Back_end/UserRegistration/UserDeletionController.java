package Medical.Appointment.System.Back_end.UserRegistration;



import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class UserDeletionController {

    private static final String FILE_PATH = "C:\\Users\\User\\Documents\\OOP project\\Medical-Appointment-Scheduling-System-\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\UserRegistration\\Registration.txt";

    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam String email, @RequestParam String username) {
        try {
            Path path = Paths.get(FILE_PATH);
            if (!Files.exists(path)) {
                return "User registration file not found.";
            }

            // Read the file content as a String
            String content = new String(Files.readAllBytes(path));

            // Split entries based on a consistent block marker (e.g., "New User Registration:")
            String[] entries = content.split("(?=New User Registration:)");

            StringBuilder updatedContent = new StringBuilder();
            boolean userFound = false;

            for (String entry : entries) {
                if (entry.contains("Username: " + username) && entry.contains("Email: " + email)) {
                    // Skip this user (i.e., delete)
                    userFound = true;
                } else {
                    updatedContent.append(entry);
                }
            }

            if (!userFound) {
                return "User not found.";
            }

            // Write updated data back to file
            Files.write(path, updatedContent.toString().getBytes());
            return "User deleted successfully.";

        } catch (IOException e) {
            return "Error deleting user: " + e.getMessage();
        }
    }
}
