package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.util.Collections;
import java.util.List;

// Diese Klasse repräsentiert einen Trainingstag, z.B. Montag - Push & Legs
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Erforderlich für JPA, um eine Instanz ohne Parameter zu erstellen
@Entity // Markiert die Klasse als JPA-Entität, die in einer Datenbanktabelle
        // gespeichert werden kann

public class WorkoutDay {
    @Id // Markiert das Feld als Primärschlüssel
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatische Generierung der ID
    private Long workoutDayId; // ID des Trainingstags

    private String workoutDayName; // Name des Tags, z. B. Montag

    @Column(name = "is_rest_day") // Spaltenname in der Datenbank
    private boolean restDay; // Ob der Tag ein Ruhetag ist

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workoutDay")
    // Ein Trainingstag hat viele Übungen
    private List<Exercise> exercises; // Liste der Übungen an diesem Tag

    @ManyToOne
    @JsonBackReference // Verhindert Endlosschleife
    @JoinColumn(name = "plan_id") // Fremdschlüssel-Spalte für die Beziehung zu Plan

    private Plan plan; // Der Plan, zu dem dieser Trainingstag gehört

    
    
}
