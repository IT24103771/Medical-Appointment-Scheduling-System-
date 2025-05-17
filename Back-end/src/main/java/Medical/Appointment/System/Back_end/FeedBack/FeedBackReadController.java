package Medical.Appointment.System.Back_end.FeedBack;

import Medical.Appointment.System.Back_end.Constents;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
@RestController
@RequestMapping("/api/feedback") // changed from /api/users
@CrossOrigin(origins = "*")
public class FeedBackReadController {
    String filePath = "src/main/java/Medical/Appointment/System/Back_end/FeedBack/FeedBack.txt";

    @GetMapping("/all")
    public String getAllFeedBacks() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return "No feedback found.";
            }
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            return "Error reading feedback: " + e.getMessage();
        }
    }
}


