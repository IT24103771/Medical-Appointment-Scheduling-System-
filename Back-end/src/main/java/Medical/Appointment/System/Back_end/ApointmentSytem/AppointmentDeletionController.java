package Medical.Appointment.System.Back_end.ApointmentSytem;

import Medical.Appointment.System.Back_end.Constents;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentDeletionController {
    Constents constents = new Constents();

   // private static final String FILE_PATH = "C:\\Users\\User\\Documents\\OOP project\\Medical-Appointment-Scheduling-System-\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\ApointmentSytem\\appointments.txt";

    @DeleteMapping("/delete")
    public String deleteAppointment(@RequestParam String email, @RequestParam String appointmentDate) {
        try {
            Path path = Paths.get(constents.appointmentFilePath);
            if (!Files.exists(path)) {
                return "Appointment file not found.";
            }

            // Read the file content as a String
            String content = new String(Files.readAllBytes(path));

            // Split the content into individual appointments based on the "=== URGENCY LEVEL:" separator
            String[] entries = content.split("(?=== URGENCY LEVEL:)");

            // StringBuilder to store the updated content
            StringBuilder updatedContent = new StringBuilder();

            // Flag to track if we found the appointment to delete
            boolean appointmentFound = false;

            // Iterate through each appointment entry
            for (String entry : entries) {
                if (entry.contains("Email: " + email) && entry.contains("Date: " + appointmentDate)) {
                    // Skip this entry, effectively deleting it
                    appointmentFound = true;
                } else {
                    // Append the current appointment entry to the updated content
                    updatedContent.append("=== URGENCY LEVEL:").append(entry);
                }
            }

            // If no appointment was found to delete, return a message
            if (!appointmentFound) {
                return "Appointment not found.";
            }

            // Write the updated content back to the file
            Files.write(path, updatedContent.toString().getBytes());

            return "Appointment deleted successfully.";

        } catch (IOException e) {
            return "Error deleting appointment: " + e.getMessage();
        }
    }
}
