package Medical.Appointment.System.Back_end.ManageDoctors;

import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/doctors/read")
public class DoctorReadController {

    private final String DOCTOR_FILE = "src/main/java/Medical/Appointment/System/Back_end/ManageDoctors/manageDoctors.txt";

    @GetMapping("/text")
    public String readDoctorsAsText() throws IOException {
        File file = new File(DOCTOR_FILE);
        if (!file.exists()) return "No doctors found.";

        List<String> lines = Files.readAllLines(file.toPath());
        return String.join("\n", lines);
    }

    @GetMapping("/json")
    public List<MangeDoctors> readDoctorsAsJson() throws IOException {
        List<MangeDoctors> list = new ArrayList<>();
        File file = new File(DOCTOR_FILE);
        if (!file.exists()) return list;

        List<String> lines = Files.readAllLines(file.toPath());
        for (String line : lines) {
            if (!line.trim().isEmpty()) {
                list.add(MangeDoctors.fromString(line));
            }
        }
        return list;
    }
}
