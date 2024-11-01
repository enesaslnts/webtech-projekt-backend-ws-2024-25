package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// Diese Klasse repräsentiert eine Übung, z.B. Bankdrücken
@Getter
@Setter 
@AllArgsConstructor 
public class Exercise {
    private String exercise;  // Name der Übung
    private int sets;  // Anzahl der Sätze
    private int reps;  // Anzahl der Wiederholungen
    private String time;  // Optional: Zeitangabe (z.B. für Übungen wie Plank)
}

