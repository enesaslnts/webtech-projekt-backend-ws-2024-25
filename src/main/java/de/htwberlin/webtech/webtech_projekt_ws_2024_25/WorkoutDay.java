package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List; // List wird benötigt, um mehrere Übungen zu speichern

// Diese Klasse repräsentiert einen Trainingstag, z.B. Montag - Push & Legs
@Getter 
@Setter 
@AllArgsConstructor 
public class WorkoutDay {
    private String day;  // Name des Trainingstags (z.B. Montag)
    private List<Exercise> workout;  // Liste von Übungen, die an diesem Tag gemacht werden
    private boolean isRestDay;  // True, wenn der Tag ein Ruhetag ist
}


