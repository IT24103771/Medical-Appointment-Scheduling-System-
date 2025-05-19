package Medical.Appointment.System.Back_end.TimeScheduling;

public class TimeScheduling {
    private String doctor;
    private String time;  // ISO datetime string e.g. "2025-05-20T14:00"

    public TimeScheduling() {}

    public TimeScheduling(String doctor, String time) {
        this.doctor = doctor;
        this.time = time;
    }

    public String getDoctor() { return doctor; }
    public void setDoctor(String doctor) { this.doctor = doctor; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    // Serialize as "doctor,time"
    @Override
    public String toString() {
        return doctor + "," + time;
    }

    // Deserialize from "doctor,time"
    public static TimeScheduling fromString(String line) {
        String[] parts = line.split(",", 2);
        if (parts.length < 2) return new TimeScheduling();
        return new TimeScheduling(parts[0], parts[1]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimeScheduling)) return false;
        TimeScheduling ts = (TimeScheduling) o;
        return doctor.equals(ts.doctor) && time.equals(ts.time);
    }

    @Override
    public int hashCode() {
        return doctor.hashCode() + time.hashCode();
    }
}
