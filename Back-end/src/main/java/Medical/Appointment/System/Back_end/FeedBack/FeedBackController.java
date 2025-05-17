package Medical.Appointment.System.Back_end.FeedBack;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.*;

@CrossOrigin(origins = "*",allowedHeaders = "*",methods = {RequestMethod.POST, RequestMethod.OPTIONS})
@RestController
@RequestMapping("/api")
public class FeedBackController {
    @Configuration
    public class CorsConfig implements WebMvcConfigurer{

        @Override
        public void addCorsMappings(CorsRegistry registry) {
            //WebMvcConfigurer.super.addCorsMappings(registry);
            registry.addMapping("/**")
                    .allowedOrigins("*")
                    .allowedMethods("*")
                    .allowedHeaders("*");
        }
    }

    @PostMapping("/feedback")
    public String feedBack(@RequestBody FeedBack feedBack){
        String textMsg = String.format(
                "New Feed Back :%n" +
                        "Full Name:%s%n" +
                        "Email Address:%s%n" +
                        "Overall Rating:%s%n"+
                        "Additional Comments:%s%n"+
                        "Registration Date: %s%n%n",


                feedBack.getFullName(),
                feedBack.getEmail(),
                feedBack.getRating(),
                feedBack.getMessage(),
                java.time.LocalDate.now()

        );
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(

                "src/main/java/Medical/Appointment/System/Back_end/FeedBack/FeedBack.txt",

                true))) {
            writer.write(textMsg);
            return "User registered successfully!";
        } catch (IOException e) {
            return "Failed to register user: " + e.getMessage();
        }
    }

}
