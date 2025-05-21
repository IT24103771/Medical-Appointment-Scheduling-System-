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
        String urgency = appointment.getUrgencyLevel().toLowerCase();
        String filePath = "src/main/java/Medical/Appointment/System/Back_end/ApointmentSytem/appointments.txt";

        String textMsg = String.format(
                "=== URGENCY LEVEL: %s ===%n" +
                        "Appointment for %s %s%nDepartment: %s%nDate: %s%nDoctor: %s%nEmail: %s%nPhone: %s%nNotes: %s%n%n",
                urgency.toUpperCase(),
                appointment.getFirstName(), appointment.getLastName(),
                appointment.getDepartment(), appointment.getAppointmentDate(),
                appointment.getDoctor(), appointment.getEmail(),
                appointment.getPhone(), appointment.getNotes()
        );

        // Custom linear queues
        QueueX emergencyQueue = new QueueX(100);
        QueueX urgentQueue = new QueueX(100);
        QueueX routineQueue = new QueueX(100);

        try {
            File file = new File(filePath);

            if (file.exists()) {
                String content = new String(Files.readAllBytes(file.toPath()));
                String[] entries = content.split("(?=== URGENCY LEVEL:)");

                for (String entry : entries) {
                    if (entry.toLowerCase().contains("emergency")) {
                        emergencyQueue.insert(entry);
                    } else if (entry.toLowerCase().contains("urgent")) {
                        urgentQueue.insert(entry);
                    } else if (entry.toLowerCase().contains("routine")) {
                        routineQueue.insert(entry);
                    }
                }
            }

            // Add new appointment
            switch (urgency) {
                case "emergency":
                    emergencyQueue.insert(textMsg);
                    break;
                case "urgent":
                    urgentQueue.insert(textMsg);
                    break;
                case "routine":
                default:
                    routineQueue.insert(textMsg);
                    break;
            }

            // Write back to file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                for (String entry : emergencyQueue.toArray()) writer.write(entry);
                for (String entry : urgentQueue.toArray()) writer.write(entry);
                for (String entry : routineQueue.toArray()) writer.write(entry);
            }

        } catch (IOException e) {
            return "Failed to save appointment: " + e.getMessage();
        }

        return "Appointment saved using linear queue urgency prioritization!";
    }
}
