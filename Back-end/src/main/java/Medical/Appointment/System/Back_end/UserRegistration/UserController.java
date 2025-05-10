package Medical.Appointment.System.Back_end.UserRegistration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.POST, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api")
public class UserController {
@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {

        // Format the user registration data
        String textMsg = String.format(
                "New User Registration:%n" +
                        "Username: %s%n" +
                        "Password: %s%n" +
                        "Full Name: %s%n" +
                        "Address: %s%n" +
                        "Phone Number: %s%n " +
                        "Age: %s%n "          +
                        "Gender: %s%n "+
                        "Email: %s%n"         +
                        "Registration Date: %s%n%n",


                        user.getUserName(),
                        user.getPassword(),
                        user.getFullName(),
                        user.getLocation(),
                        user.getPhoneNo(),
                        user.getAge(),
                        user.getGender(),
                        user.getEmailAdd(),
                        java.time.LocalDate.now()
        );

        System.out.println("New user registration: " +user.getUserName());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(

                "C:\\Users\\User\\Documents\\OOP project\\Medical-Appointment-Scheduling-System-\\Back-end\\src\\main\\java\\Medical\\Appointment\\System\\Back_end\\UserRegistration\\Registration.txt",

                true))) {
            writer.write(textMsg);
            return "User registered successfully!";
        } catch (IOException e) {
            return "Failed to register user: " + e.getMessage();
        }
    }

}