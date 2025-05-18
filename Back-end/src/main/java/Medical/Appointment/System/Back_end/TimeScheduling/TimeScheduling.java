package Medical.Appointment.System.Back_end.TimeScheduling;


public class TimeScheduling {
    private String doctorName;
    private String date;
    private String time;
    private String duration;

    public TimeScheduling(String doctorName, String date, String time, String duration) {
        this.doctorName = doctorName;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    public static TimeScheduling fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length < 4) return new TimeScheduling("", "", "", "");
        return new TimeScheduling(parts[0], parts[1], parts[2], parts[3]);
    }

    public String getDoctorName() { return doctorName; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getDuration() { return duration; }

    @Override
    public String toString() {
        return doctorName + "," + date + "," + time + "," + duration;
    }
}
