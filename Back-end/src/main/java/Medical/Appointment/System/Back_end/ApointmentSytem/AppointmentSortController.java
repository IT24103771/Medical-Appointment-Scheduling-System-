package Medical.Appointment.System.Back_end.ApointmentSytem;


import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/sorted-appointments")
public class AppointmentSortController {

    private static final String FILE_PATH = "src/main/java/Medical/Appointment/System/Back_end/ApointmentSytem/TimeSort.txt";

    @PostMapping("/save")
    public String saveSortedAppointment(@RequestBody Appointment appointment) {
        // Format the new appointment
        String newAppointment = String.format(
                "Appointment:%n" +
                        "Full Name: %s %s%n" +
                        "Department: %s%n" +
                        "Date: %s%n" +
                        "Doctor: %s%n" +
                        "Email: %s%n" +
                        "Phone: %s%n" +
                        "Urgency Level: %s%n" +
                        "Notes: %s%n" +
                        "Saved On: %s%n%n",
                appointment.getFirstName(),
                appointment.getLastName(),
                appointment.getDepartment(),
                appointment.getAppointmentDate(),
                appointment.getDoctor(),
                appointment.getEmail(),
                appointment.getPhone(),
                appointment.getUrgencyLevel(),
                appointment.getNotes(),
                java.time.LocalDateTime.now()
        );

        List<String> appointmentList = new ArrayList<>();

        // Read existing appointments
        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
                String[] entries = content.split("(?=Appointment:)");
                appointmentList.addAll(Arrays.asList(entries));
            }
        } catch (IOException e) {
            return "Failed to read existing appointments: " + e.getMessage();
        }

        // Add new appointment
        appointmentList.add(newAppointment);

        // Bubble sort: push newest to top based on timestamp in "Saved On" field
        bubbleSortByTimestamp(appointmentList);

        // Save back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String entry : appointmentList) {
                writer.write(entry);
            }
        } catch (IOException e) {
            return "Failed to save appointment: " + e.getMessage();
        }

        return "Appointment saved and sorted by newest first!";
    }

    private void bubbleSortByTimestamp(List<String> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                String date1 = extractTimestamp(list.get(j));
                String date2 = extractTimestamp(list.get(j + 1));

                if (date1 != null && date2 != null && date1.compareTo(date2) < 0) {
                    // Swap so newer comes first
                    String temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private String extractTimestamp(String entry) {
        try {
            for (String line : entry.split("\n")) {
                if (line.startsWith("Saved On: ")) {
                    return line.replace("Saved On: ", "").trim();
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
