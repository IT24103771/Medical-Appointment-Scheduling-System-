package Medical.Appointment.System.Back_end.TimeScheduling;



public class TimeScheduling {
    private String doctor;
    private String time;

    public TimeScheduling() {}

    public TimeScheduling(String doctor, String time) {
        this.doctor = doctor;
        this.time = time;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getTime() {
        return time;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return doctor + ";" + time;
    }

    public static TimeScheduling fromString(String line) {
        String[] parts = line.split(";");
        return new TimeScheduling(parts[0], parts[1]);
    }
}
