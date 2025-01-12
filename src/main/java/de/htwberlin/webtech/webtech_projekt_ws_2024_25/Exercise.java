package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import lombok.AllArgsConstructor; // Lombok-Annotation für Konstruktor mit allen Feldern.
import lombok.Getter; // Lombok-Annotation für Getter-Methoden.
import lombok.NoArgsConstructor; // Lombok-Annotation für leeren Konstruktor.
import lombok.Setter; // Lombok-Annotation für Setter-Methoden.

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*; // Importiert JPA-Annotationen.

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // Markiert die Klasse als JPA-Entität für die Datenbank.

public class Exercise {

   @Id // Markiert das Feld als Primärschlüssel
   @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatische Generierung der ID
   private Long exerciseId; // ID der Übung

   private String exerciseName; // Name der Übung

   private int exerciseSets; // Anzahl der Sätze

   private int exerciseReps; // Anzahl der Wiederholungen

   @ManyToOne
   @JsonBackReference // Verhindert Endlosschleife
   @JoinColumn(name = "workout_day_id") // Fremdschlüssel-Spalte für die Beziehung zu WorkoutDay

   private WorkoutDay workoutDay; // Der Trainingstag, zu dem diese Übung gehört

   
  
}
