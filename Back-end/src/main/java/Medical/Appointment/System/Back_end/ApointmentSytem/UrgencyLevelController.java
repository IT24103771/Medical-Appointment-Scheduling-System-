//package Medical.Appointment.System.Back_end.ApointmentSytem;
//import org.springframework.web.bind.annotation.*;
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.PriorityQueue;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.PriorityQueue;
//@CrossOrigin(origins = "*")
//@RestController
//@RequestMapping("/api/appointments")
//public class UrgencyLevelController {
//    private final PriorityQueue<Appointment> appointmentQueue = new PriorityQueue<>();
//
//    @PostMapping("/queue")
//    public String addToQueue(@RequestBody Appointment appointment) {
//        if (appointment.getUrgencyLevel() == null) {
//            return "Urgency level is required.";
//        }
//
//        appointmentQueue.add(appointment);
//        return "Appointment queued based on urgency level: " + appointment.getUrgencyLevel();
//    }
//
//    @GetMapping("/next")
//    public Appointment getNextAppointment() {
//        return appointmentQueue.poll(); // returns and removes the highest priority appointment
//    }
//
//    @GetMapping("/all")
//    public PriorityQueue<Appointment> getAllAppointments() {
//        return new PriorityQueue<>(appointmentQueue); // return a copy
//    }
//
//    @PostMapping("/save")
//
//    public String saveAppointment(@RequestBody Appointment appointment) {
//        System.out.println("Saving appointment for: " + appointment.getFirstName());
//        appointmentQueue.add(appointment);
//
//
//    }
//
//}
