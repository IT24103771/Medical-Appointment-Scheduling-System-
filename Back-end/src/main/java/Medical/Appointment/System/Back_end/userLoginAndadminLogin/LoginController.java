package Medical.Appointment.System.Back_end.userLoginAndadminLogin;

import Medical.Appointment.System.Back_end.Constents;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.POST, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api")
public class LoginController {
    Constents constents = new Constents();
//    private final String FILE_PATH =
//            "C:\\Users\\User\\Documents\\OOP project\\Medical-Appointment-Scheduling-System-\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\UserRegistration\\Registration.txt";

    @PostMapping("/login")
    public String loginUser(@RequestBody Login login) {
        String username = login.getUserName();
        String password = login.getPassword();

        try (BufferedReader reader = new BufferedReader(new FileReader(constents.usersListFilePath))) {
            String line;
            String currentUsername = null;
            String currentPassword = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    currentUsername = line.substring("Username: ".length()).trim();
                } else if (line.startsWith("Password: ")) {
                    currentPassword = line.substring("Password: ".length()).trim();
                }

                // Check if both username and password are read and match
                if (currentUsername != null && currentPassword != null) {
                    if (currentUsername.equals(username) && currentPassword.equals(password)) {
                        return "Login successful!";  // Success message
                    }

                    // Reset for next user block
                    currentUsername = null;
                    currentPassword = null;
                }
            }
        } catch (IOException e) {
            return "Login failed: " + e.getMessage();
        }

        return "Invalid username or password.";  // Failure message
    }
}
