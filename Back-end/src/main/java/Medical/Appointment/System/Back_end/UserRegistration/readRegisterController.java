package Medical.Appointment.System.Back_end.UserRegistration;

import Medical.Appointment.System.Back_end.Constents;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class readRegisterController {

     Constents contents = new Constents();

    @GetMapping("/all")
    public String getAllUsers() {
        try {
            File file = new File(contents.usersListFilePath);
            if (!file.exists()) {
                return "No users registered.";
            }
            return new String(Files.readAllBytes(Paths.get(contents.usersListFilePath)));
        } catch (IOException e) {
            return "Error reading users: " + e.getMessage();
        }
    }
}
