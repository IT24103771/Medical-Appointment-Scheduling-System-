package Medical.Appointment.System.Back_end.ManageDoctors;


import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/doctors")
public class ManageDoctorsController {

    private final String filePath = "src/main/java/Medical/Appointment/System/Back_end/ManageDoctors/manageDoctors.txt";

    @PostMapping("/save")
    public String saveDoctor(@RequestBody MangeDoctors doctor) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(doctor.toString());
            writer.newLine();
        } catch (IOException e) {
            return "Failed to save doctor: " + e.getMessage();
        }
        return "Doctor saved successfully.";
    }

    @GetMapping("/all")
    public List<MangeDoctors> getAllDoctors() {
        List<MangeDoctors> doctors = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                if (!line.trim().isEmpty()) {
                    doctors.add(MangeDoctors.fromString(line));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doctors;
    }

    @DeleteMapping("/delete/{index}")
    public String deleteDoctor(@PathVariable int index) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (index >= 0 && index < lines.size()) {
                lines.remove(index);
                Files.write(Paths.get(filePath), lines);
                return "Doctor deleted.";
            } else {
                return "Invalid index.";
            }
        } catch (IOException e) {
            return "Error deleting doctor: " + e.getMessage();
        }
    }
}
