package Medical.Appointment.System.Back_end.ManageDoctors;

public class MangeDoctors {
    private String name;
    private String specialty;

    // Required: no-arg constructor
    public MangeDoctors() {}

    public MangeDoctors(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSpecialty() { return specialty; }
    public void setSpecialty(String specialty) { this.specialty = specialty; }

    // Save as a line in file
    @Override
    public String toString() {
        return name + "," + specialty;
    }

    // Parse from a line in file
    public static MangeDoctors fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length >= 2) {
            return new MangeDoctors(parts[0], parts[1]);
        }
        return new MangeDoctors("Unknown", "Unknown");
    }
}
