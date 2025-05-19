package Medical.Appointment.System.Back_end.TimeScheduling;

import org.springframework.web.bind.annotation.*;
import Medical.Appointment.System.Back_end.ManageDoctors.MangeDoctors;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/timeslots")
public class TimeSchedulingController {

    private final String TIME_FILE = "src/main/java/Medical/Appointment/System/Back_end/TimeScheduling/TimeScheduling.txt";
    private final String DOCTORS_FILE = "src/main/java/Medical/Appointment/System/Back_end/ManageDoctors/manageDoctors.txt";

    @GetMapping
    public List<TimeScheduling> getAllTimeSlots() throws IOException {
        List<TimeScheduling> list = new ArrayList<>();
        if (!new File(TIME_FILE).exists()) return list;

        List<String> lines = Files.readAllLines(Paths.get(TIME_FILE));
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                list.add(TimeScheduling.fromString(line));
            }
        }
        return list;
    }

    @PostMapping("/save")
    public String saveTimeSlot(@RequestBody TimeScheduling timeSlot) throws IOException {
        // Append new time slot to file
        try (FileWriter writer = new FileWriter(TIME_FILE, true)) {
            writer.write(timeSlot.toString() + "\n");
        }
        return "Time slot saved.";
    }

    @PostMapping("/delete")
    public String deleteTimeSlot(@RequestBody TimeScheduling timeSlot) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(TIME_FILE));
        List<String> updated = new ArrayList<>();
        for (String line : lines) {
            if (!line.equals(timeSlot.toString())) {
                updated.add(line);
            }
        }
        Files.write(Paths.get(TIME_FILE), updated);
        return "Time slot deleted.";
    }

    // New endpoint to update a time slot: delete old slot and add new one
    @PostMapping("/update")
    public String updateTimeSlot(@RequestBody UpdateRequest request) throws IOException {
        // Delete old slot
        List<String> lines = Files.readAllLines(Paths.get(TIME_FILE));
        List<String> updated = new ArrayList<>();
        for (String line : lines) {
            if (!line.equals(request.oldSlot.toString())) {
                updated.add(line);
            }
        }
        // Add new slot
        updated.add(request.newSlot.toString());
        Files.write(Paths.get(TIME_FILE), updated);
        return "Time slot updated.";
    }

    @GetMapping("/doctors")
    public List<MangeDoctors> getDoctors() throws IOException {
        List<MangeDoctors> doctors = new ArrayList<>();
        File file = new File(DOCTORS_FILE);
        if (!file.exists()) return doctors;

        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                doctors.add(MangeDoctors.fromString(line));
            }
        }
        return doctors;
    }

    // Helper class for update request payload
    public static class UpdateRequest {
        public TimeScheduling oldSlot;
        public TimeScheduling newSlot;
    }
}
