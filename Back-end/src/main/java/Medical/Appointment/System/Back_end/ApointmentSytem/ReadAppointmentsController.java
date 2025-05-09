package Medical.Appointment.System.Back_end.ApointmentSytem;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")
public class ReadAppointmentsController {

    private final String filePath = "C:\\Users\\Ahamed\\OneDrive\\Documents\\OOP Project\\Medical-Appointment-Scheduling-System-\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\ApointmentSytem\\appointments.txt";

    @GetMapping("/all")
    public String getAllAppointments() {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return "No appointments found.";
            }
            return new String(Files.readAllBytes(Paths.get(filePath)));
        } catch (IOException e) {
            return "Error reading appointments: " + e.getMessage();
        }
    }
}
