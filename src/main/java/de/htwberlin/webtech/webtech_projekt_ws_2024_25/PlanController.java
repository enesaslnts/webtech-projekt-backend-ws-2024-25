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

    private final List<Plan> plans = new ArrayList<>();

    @GetMapping
    public List<Plan> getAllPlans() {
        return plans;
    }

    @PostMapping
    public Plan addPlan(@RequestBody Plan newPlan) {
    if (newPlan.getName() == null || newPlan.getName().isEmpty()) {
        throw new IllegalArgumentException("Plan name is required.");
    }
    if (newPlan.getDays() == null) {
        throw new IllegalArgumentException("Plan must have days.");
    }

    newPlan.setId(plans.stream().mapToLong(Plan::getId).max().orElse(0) + 1);
    long dayId = 1;
    for (WorkoutDay day : newPlan.getDays()) {
        day.setId(dayId++);
        System.out.println("Day: " + day.getDay() + ", RestDay: " + day.isRestDay());
    }
    plans.add(newPlan);
    System.out.println("Neuer Plan hinzugefügt: " + newPlan.getName());
    return newPlan;
}



    @DeleteMapping("/{id}")
    public void deletePlan(@PathVariable Long id) {
        plans.removeIf(plan -> plan.getId().equals(id));
    }
}