package Medical.Appointment.System.Back_end.UserRegistration;

import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class UserController {

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        System.out.println("Im getting the request");
        System.out.println("Received user: " + user.getEmailAdd());

        return "User registered successfully!";
    }
}
