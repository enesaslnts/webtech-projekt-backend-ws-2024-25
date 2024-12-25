package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import org.springframework.beans.factory.annotation.Autowired; // Ermöglicht das automatische Injizieren von Abhängigkeiten in den Controller.
import org.springframework.web.bind.annotation.*; // Importiert alle benötigten Spring-Annotations.

import java.util.Collections;
import java.util.List; // Ermöglicht die Verwendung von Listen.
import java.util.ArrayList;
import java.util.Optional;

// Spring Controller, der HTTP-Anfragen verarbeitet und JSON-Daten zurückgibt.
@RestController
@RequestMapping("/api/plans") // Allgemeiner URL-Pfad für alle Endpunkte dieser Klasse.
public class PlanController {

    private final PlanRepository planRepository; // Repository für Plan-Datenbankzugriffe

    @Autowired
    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    // Abrufen aller Pläne
    @GetMapping
    public List<Plan> getAllPlans() {
        // return planRepository.findAll(); // Holt alle Pläne aus der Datenbank
        List<Plan> plans = planRepository.findAll();
        plans.forEach(plan -> {
            System.out.println("Plan: " + plan.getPlanName());
            plan.getWorkoutDays()
                    .forEach(day -> System.out
                            .println(" - Day: " + day.getWorkoutDayName() + ", RestDay: " + day.isRestDay()));
        });
        return plans;
    }

    // Hinzufügen eines neuen Plans
    @PostMapping
    public Plan addPlan(@RequestBody Plan newPlan) {
        try {
            if (newPlan.getPlanName() == null || newPlan.getPlanName().isEmpty()) {
                throw new IllegalArgumentException("Plan name is required.");
            }
            if (newPlan.getWorkoutDays() == null || newPlan.getWorkoutDays().isEmpty()) {
                throw new IllegalArgumentException("Plan must have at least one day.");
            }

            // Verknüpfen der WorkoutDays und Übungen mit dem Plan
            for (WorkoutDay day : newPlan.getWorkoutDays()) {
                day.setPlan(newPlan); // Setzt den Plan für den jeweiligen WorkoutDay
                if (day.getExercises() != null) {
                    for (Exercise exercise : day.getExercises()) {
                        exercise.setWorkoutDay(day); // Setzt den WorkoutDay für die jeweilige Übung
                    }
                }
            }
            Plan gespeicherterPlan = planRepository.save(newPlan);
            System.out.println("Gespeicherter Plan: " + gespeicherterPlan);
            return gespeicherterPlan;

        } catch (Exception e) {
            e.printStackTrace(); // Zeigt den vollständigen Fehler im Backend-Log
            throw new RuntimeException("Fehler beim Speichern des Plans: " + e.getMessage());
        }
    }

    // Löschen eines Plans anhand der ID
    @DeleteMapping("/{planId}")
    public void deletePlan(@PathVariable Long planId) {
        Optional<Plan> plan = planRepository.findById(planId); // Sucht den Plan anhand der ID
        if (plan.isPresent()) {
            planRepository.delete(plan.get()); // Löscht den Plan, falls gefunden
        } else {
            throw new IllegalArgumentException("Plan with ID " + planId + " not found.");
        }
    }
}