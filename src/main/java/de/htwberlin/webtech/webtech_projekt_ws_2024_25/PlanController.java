package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import org.springframework.beans.factory.annotation.Autowired; // Ermöglicht das automatische Injizieren von Abhängigkeiten in den Controller.
import org.springframework.web.bind.annotation.*; // Importiert alle benötigten Spring-Annotations.

import java.util.Collections;
import java.util.List; // Ermöglicht die Verwendung von Listen.
import java.util.ArrayList;

// Spring Controller, der HTTP-Anfragen verarbeitet und JSON-Daten zurückgibt.
@RestController
@RequestMapping("/api/plans") // Allgemeiner URL-Pfad für alle Endpunkte dieser Klasse.
public class PlanController {

    private final PlanRepository planRepository;

    @Autowired
    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @GetMapping
    public List<Plan> getAllPlans() {
        return planRepository.findAll(); // Alle Pläne aus der Datenbank abrufen
    }

    @PostMapping
    public Plan addPlan(@RequestBody Plan newPlan) {
        if (newPlan.getName() == null || newPlan.getName().isEmpty()) {
            throw new IllegalArgumentException("Planname darf nicht leer sein.");
        }
        if (newPlan.getDays() == null || newPlan.getDays().isEmpty()) {
            throw new IllegalArgumentException("Ein Plan muss mindestens einen Tag enthalten.");
        }

        return planRepository.save(newPlan); // Speichern in der Datenbank
    }

    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Long id) {
        planRepository.deleteById(id); // Löschen nach ID
    }

}