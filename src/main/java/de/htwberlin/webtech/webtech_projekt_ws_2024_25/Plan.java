package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import lombok.AllArgsConstructor; // Lombok-Annotation für Konstruktor mit allen Feldern.
import lombok.Builder;
import lombok.Getter; // Lombok-Annotation für Getter-Methoden.
import lombok.NoArgsConstructor; // Lombok-Annotation für leeren Konstruktor.
import lombok.Setter; // Lombok-Annotation für Setter-Methoden.

import jakarta.persistence.*; // Importiert JPA-Annotationen.
import java.util.List; // Ermöglicht die Verwendung von Listen in der Klasse.

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // Markiert die Klasse als JPA-Entität für die Datenbank.
public class Plan {

    @Id // Markiert das Feld als Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatische Generierung der ID
    private Long planId; // ID des Plans

    private String planName; // Name des Plans

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    // Beziehung: Ein Plan hat viele Trainingstage
    @JsonManagedReference // Markiert die "Hauptseite" der Beziehung
    private List<WorkoutDay> workoutDays; // Liste der Trainingstage

    /*
     * @OneToMany(mappedBy = "parentPlan", cascade = CascadeType.ALL, orphanRemoval
     * = true, fetch = FetchType.EAGER)
     * // Eine-zu-viele-Beziehung zu WorkoutDay.
     * private List<WorkoutDay> workoutDays = new ArrayList<>();// Liste der
     * Trainingstage
     */

    
}