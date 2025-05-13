package Medical.Appointment.System.Back_end.ApointmentSytem;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")
public class editAppointment {

    private final String filePath = "C:\\Users\\User\\Documents\\OOP project\\Medical-Appointment-Scheduling-System-\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\ApointmentSytem\\appointments.txt";

    @PutMapping("/edit")
    public String editAppointment(@RequestBody Appointment updatedAppointment) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            StringBuilder updated = new StringBuilder();

            String[] entries = content.split("(?=== URGENCY LEVEL:)");
            boolean appointmentFound = false;

            String email = updatedAppointment.getEmail();
            String oldDate = updatedAppointment.getAppointmentDate(); // Use a separate field like getOriginalDate() if needed

            for (String entry : entries) {
                if (entry.contains(email) && entry.contains("Date: " + oldDate)) {
                    appointmentFound = true;
                    continue; // Skip the old entry
                }
                updated.append(entry);
            }

            if (!appointmentFound) {
                return "Appointment not found for update.";
            }

            String urgency = updatedAppointment.getUrgencyLevel().toLowerCase();
            String newEntry = formatAppointment(updatedAppointment, urgency);

            switch (urgency) {
                case "emergency":
                    updated.insert(0, newEntry);
                    break;
                case "urgent":
                case "routine":
                default:
                    updated.append(newEntry);
            }

            Files.write(Paths.get(filePath), updated.toString().getBytes());
            return "Appointment updated successfully!";
        } catch (IOException e) {
            return "Error updating appointment: " + e.getMessage();
        }
    }

    private String formatAppointment(Appointment a, String urgency) {
        return String.format(
                "=== URGENCY LEVEL: %s ===%n" +
                        "Appointment for %s %s%nDepartment: %s%nDate: %s%nDoctor: %s%nEmail: %s%nPhone: %s%nNotes: %s%n%n",
                urgency.toUpperCase(),
                a.getFirstName(), a.getLastName(),
                a.getDepartment(), a.getAppointmentDate(),
                a.getDoctor(), a.getEmail(),
                a.getPhone(), a.getNotes()
        );
    }
}
