package Medical.Appointment.System.Back_end.ApointmentSytem;

import Medical.Appointment.System.Back_end.Constents;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")
public class ReadAppointmentsController {
    Constents constents = new Constents();
   // private final String filePath = "C:\\Users\\User\\Documents\\OOP project\\Medical-Appointment-Scheduling-System-\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\ApointmentSytem\\appointments.txt";


    @GetMapping("/all")
    public String getAllAppointments() {
        try {
            File file = new File(constents.appointmentFilePath);
            if (!file.exists()) {
                return "No appointments found.";
            }
            return new String(Files.readAllBytes(Paths.get(constents.appointmentFilePath)));
        } catch (IOException e) {
            return "Error reading appointments: " + e.getMessage();
        }
    }
}
