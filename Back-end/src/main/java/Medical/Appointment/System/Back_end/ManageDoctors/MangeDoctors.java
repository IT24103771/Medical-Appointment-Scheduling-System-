package Medical.Appointment.System.Back_end.ManageDoctors;

public class MangeDoctors {
    private String name;
    private String specialty;
    private String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MangeDoctors() {}

    public MangeDoctors(String name, String specialty, String image) {
        this.name = name;
        this.specialty = specialty;
        this.image = image;
    }
    @Override
    public String toString() {
        return name + ";" + specialty + ";" + image;
    }

    public static MangeDoctors fromString(String line) {
        String[] parts = line.split(";");
        return new MangeDoctors(parts[0], parts[1], parts[2]);
    }


}
