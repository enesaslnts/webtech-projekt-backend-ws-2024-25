package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Erlaube CORS für alle Routen, Ursprünge, Header und Methoden
        registry.addMapping("/api/**")
        .allowedOrigins("http://localhost:8081/")
        // Erlaubt Anfragen von deinem Frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Erlaubt diese HTTP-Methoden
                .allowedHeaders("*"); // Erlaubt alle Header
    }
}
