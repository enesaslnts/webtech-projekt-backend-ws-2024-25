package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import org.springframework.web.bind.annotation.RestController; //Kennzeichnet die Klasse als Controller.
import org.springframework.web.bind.annotation.RequestMapping; //Festlegung eines URL-Pfades.
import org.springframework.web.bind.annotation.GetMapping; //Umgang mit GET-Anfragen.

// Importierte Klassen für Meilenstein 2 Plan, WorkoutDay, und Exercise
import de.htwberlin.webtech.webtech_projekt_ws_2024_25.Plan;
import de.htwberlin.webtech.webtech_projekt_ws_2024_25.WorkoutDay;
import de.htwberlin.webtech.webtech_projekt_ws_2024_25.Exercise;

import java.util.List; //Verwendung von Listen ermöglichen
import java.util.Arrays; //Erstellen von Arrays ermöglichen
import java.util.ArrayList; //Erstellen von Array-Listen ermöglichen

// @RestController kennzeichnet diese Klasse als Spring-Controller, der JSON-Daten zurückgibt.
@RestController
// @RequestMapping legt den allgm. URL-Pfad fest, unter dem die Anfragen
// bearbeitet werden.
@RequestMapping("/api/plans")

public class PlanController {

    // Diese Methode wird aufgerufen, wenn eine GET-Anfrage an den Pfad /api/plans
    // schickt.
    @GetMapping
    public List<Plan> getAllPlans() {
        // Traingsplan Liste erstellen
        Plan muscleBuilding = new Plan(1, "Muskelaufbau", "Plan für Muskelaufbau", List.of(
                new WorkoutDay("Montag", List.of(
                        new Exercise("Bankdrücken", 4, 8, null),
                        new Exercise("Kniebeugen", 4, 10, null)), false),
                new WorkoutDay("Dienstag", List.of(
                        new Exercise("Schulterdrücken", 4, 8, null),
                        new Exercise("Kreuzheben", 4, 10, null)), false),
                new WorkoutDay("Mittwoch", null, true), // Rest-Day
                new WorkoutDay("Donnerstag", List.of(
                        new Exercise("Bizeps-Curls", 4, 12, null),
                        new Exercise("Trizeps-Dips", 4, 10, null)), false),
                new WorkoutDay("Freitag", List.of(
                        new Exercise("Beinpresse", 4, 10, null),
                        new Exercise("Rudern", 4, 8, null)), false),
                new WorkoutDay("Samstag", null, true), // Rest-Day
                new WorkoutDay("Sonntag", null, true) // Rest-Day
        ));

        // HIIT Plan
        Plan hiit = new Plan(2, "HIIT", "High-Intensity Interval Training", List.of(
                new WorkoutDay("Montag", List.of(
                        new Exercise("Burpees", 3, 15, null),
                        new Exercise("Mountain Climbers", 3, 20, null)), false),
                new WorkoutDay("Dienstag", List.of(
                        new Exercise("Jumping Jacks", 4, 20, null),
                        new Exercise("Plank", 3, 60, "Sekunden")), false),
                new WorkoutDay("Mittwoch", null, true), // Rest-Day
                new WorkoutDay("Donnerstag", List.of(
                        new Exercise("Sprints", 5, 100, "Meter"),
                        new Exercise("High Knees", 4, 30, "Sekunden")), false),
                new WorkoutDay("Freitag", List.of(
                        new Exercise("Box Jumps", 4, 15, null),
                        new Exercise("Russian Twists", 3, 20, null)), false),
                new WorkoutDay("Samstag", null, true), // Rest-Day
                new WorkoutDay("Sonntag", null, true) // Rest-Day
        ));

        // Athletik Performance Plan
        Plan athletic = new Plan(3, "Athletik-Performance", "Der perfekte Plan für Athleten", List.of(
                new WorkoutDay("Montag", List.of(
                        new Exercise("Sprints", 5, 100, "Meter"),
                        new Exercise("Plyometrische Liegestütze", 4, 12, null)), false),
                new WorkoutDay("Dienstag", List.of(
                        new Exercise("Box Jumps", 4, 10, null),
                        new Exercise("Einbeinige Kniebeugen", 3, 12, null)), false),
                new WorkoutDay("Mittwoch", null, true), // Rest-Day
                new WorkoutDay("Donnerstag", List.of(
                        new Exercise("Weitsprünge", 4, 8, null),
                        new Exercise("High Knees", 3, 30, "Sekunden")), false),
                new WorkoutDay("Freitag", List.of(
                        new Exercise("Sprint-Intervall", 6, 200, "Meter"),
                        new Exercise("Burpees", 4, 15, null)), false),
                new WorkoutDay("Samstag", null, true), // Rest-Day
                new WorkoutDay("Sonntag", null, true) // Rest-Day
        ));

        // Rückgabe aller Pläne
        return List.of(muscleBuilding, hiit, athletic);
    }
}
