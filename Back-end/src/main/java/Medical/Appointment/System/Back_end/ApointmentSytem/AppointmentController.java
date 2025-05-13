package Medical.Appointment.System.Back_end.ApointmentSytem;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @PostMapping("/save")
    public String saveAppointment(@RequestBody Appointment appointment) {
        String urgency = appointment.getUrgencyLevel().toLowerCase();  // normalize
        String filePath = "C:\\Users\\User\\Documents\\OOP project\\Medical-Appointment-Scheduling-System-\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\ApointmentSytem\\appointments.txt";


        String textMsg = String.format(
                "=== URGENCY LEVEL: %s ===%n" +
                        "Appointment for %s %s%nDepartment: %s%nDate: %s%nDoctor: %s%nEmail: %s%nPhone: %s%nNotes: %s%n%n",
                urgency.toUpperCase(),
                appointment.getFirstName(), appointment.getLastName(),
                appointment.getDepartment(), appointment.getAppointmentDate(),
                appointment.getDoctor(), appointment.getEmail(),
                appointment.getPhone(), appointment.getNotes()
        );

        try {
            File file = new File(filePath);
            StringBuilder emergency = new StringBuilder();
            StringBuilder urgent = new StringBuilder();
            StringBuilder routine = new StringBuilder();

            // Read current content and split based on urgency
            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                String[] entries = content.split("(?=== URGENCY LEVEL:)");

                for (String entry : entries) {
                    if (entry.toLowerCase().contains("emergency")) {
                        emergency.append(entry);
                    } else if (entry.toLowerCase().contains("urgent")) {
                        urgent.append(entry);
                    } else if (entry.toLowerCase().contains("routine")) {
                        routine.append(entry);
                    }
                }
            }

            // Place the new appointment in the correct category
            switch (urgency) {
                case "emergency":
                    emergency.insert(0, textMsg);  // new emergency goes on top
                    break;
                case "urgent":
                    urgent.append(textMsg);
                    break;
                case "routine":
                default:
                    routine.append(textMsg);
                    break;
            }

            // Write everything back in order: Emergency -> Urgent -> Routine
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write(emergency.toString());
                writer.write(urgent.toString());
                writer.write(routine.toString());
            }

        } catch (IOException e) {
            return "Failed to save appointment: " + e.getMessage();
        }

        return "Appointment saved with urgency prioritized!";
    }
}