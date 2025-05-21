package Medical.Appointment.System.Back_end.ApointmentSytem;

public class Appointment {

    private String appointmentDate;
    private String department;
    private String dob;
    private String doctor;
    private String email;
    private String firstName;
    private String lastName;
    private String notes;
    private String phone;
    private String urgencyLevel;


    public Appointment(String appointmentDate,String department,String dob,String doctor,String email,String firstName,String lastName,String notes,String phone,String urgencyLevel){
        this.appointmentDate = appointmentDate;
        this.department = department;
        this.dob = dob;
        this.doctor = doctor;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.notes = notes;
        this.phone = phone;
        this.urgencyLevel = urgencyLevel;

    }
    public String getUrgencyLevel() {
        return urgencyLevel;
    }

    public void setUrgencyLevel(String urgencyLevel) {
        this.urgencyLevel = urgencyLevel;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
