package Medical.Appointment.System.Back_end.TimeScheduling;


import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/timeslots/read")
public class TimeSchedulingReadController {

    private final String TIME_FILE = "src/main/java/Medical/Appointment/System/Back_end/TimeScheduling/TimeScheduling.txt";

    @GetMapping("/text")
    public String readTimeSlotsAsText() throws IOException {
        File file = new File(TIME_FILE);
        if (!file.exists()) return "No time slots found.";

        List<String> lines = Files.readAllLines(file.toPath());
        return String.join("\n", lines);
    }

    @GetMapping("/json")
    public List<TimeScheduling> readTimeSlotsAsJson() throws IOException {
        List<TimeScheduling> list = new java.util.ArrayList<>();
        File file = new File(TIME_FILE);
        if (!file.exists()) return list;

        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                list.add(TimeScheduling.fromString(line));
            }
        }
        return list;
    }
}