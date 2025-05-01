package Medical.Appointment.System.Back_end.ApointmentSytem;

import org.springframework.web.bind.annotation.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")
public class
AppointmentController {

    @PostMapping("/save")
    public String saveAppointment(@RequestBody Appointment appointment) {
        String textMsg = String.format(
                "Appointment for %s %s Department  %s%n. Date %s%n. Doctor  %s%n. Email %s%n. Phone %s%n. Notes %s%n. Urgency Level %s%n",
                appointment.getFirstName(), appointment.getLastName(),appointment.getDepartment(),appointment.getAppointmentDate(),appointment.getDoctor(),appointment.getEmail(),
                appointment.getPhone(), appointment.getNotes(),appointment.getUrgencyLevel()
        );

        System.out.println("Hello WORLD "+appointment.getFirstName());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\User\\Documents\\Projects\\Medical Appointment System\\Back-end\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\ApointmentSytem\\appointments.txt", true))) {
            writer.write(textMsg);
        } catch (IOException e) {
            return "Failed to save appointment: " + e.getMessage();
        }

        return "Appointment saved as text message!";
    }
}
