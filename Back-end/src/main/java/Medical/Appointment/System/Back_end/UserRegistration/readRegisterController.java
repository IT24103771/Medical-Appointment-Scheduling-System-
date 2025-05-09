package Medical.Appointment.System.Back_end.UserRegistration;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/users")
public class readRegisterController {

    private final String filePath = "C:\\Users\\User\\Documents\\OOP project\\Medical-Appointment-Scheduling-System-\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\UserRegistration\\Registration.txt";

    @GetMapping("/all")
    public String getAllUsers() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return "No users registered.";
            }
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            return "Error reading users: " + e.getMessage();
        }
    }
}
