package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Backend!";
    }
}
