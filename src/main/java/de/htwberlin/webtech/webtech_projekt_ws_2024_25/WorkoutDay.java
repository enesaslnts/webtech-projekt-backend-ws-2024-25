package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collections;
import java.util.List;

// Diese Klasse repräsentiert einen Trainingstag, z.B. Montag - Push & Legs
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor // Erforderlich für JPA, um eine Instanz ohne Parameter zu erstellen
@Entity // Markiert die Klasse als JPA-Entität, die in einer Datenbanktabelle gespeichert werden kann

public class WorkoutDay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Automatische ID-Generierung durch die Datenbank
    private Long id; // ID des Trainingstags
    private String day; // Name des Tags, z. B. Montag
    private boolean restDay; // Ob der Tag ein Ruhetag ist

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true) // Eine-zu-viele-Beziehung zu Excersise
    private List<Exercise> workout; // Liste der Übungen an diesem Tag

}
