package Medical.Appointment.System.Back_end.Database;

import java.io.FileWriter;
import java.io.IOException;

public class database {


    private static final String FILE_PATH = "C:\\Users\\User\\Documents\\Projects\\Medical Appointment System\\Back-end\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\database.csv";

    public static void writeToCsv(String[] data) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.append(String.join(",", data));
            writer.append("\n");
        }
    }
}
