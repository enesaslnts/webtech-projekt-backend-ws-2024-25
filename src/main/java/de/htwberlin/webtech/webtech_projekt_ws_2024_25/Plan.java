package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import lombok.AllArgsConstructor; // Lombok-Annotation für Konstruktor mit allen Feldern.
import lombok.Getter; // Lombok-Annotation für Getter-Methoden.
import lombok.NoArgsConstructor; // Lombok-Annotation für leeren Konstruktor.
import lombok.Setter; // Lombok-Annotation für Setter-Methoden.

import java.util.List; // Ermöglicht die Verwendung von Listen in der Klasse.
import jakarta.persistence.*; // Importiert JPA-Annotation für Entität.

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
@Entity // Markiert die Klasse als JPA-Entität für die Datenbank.

public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Automatische ID-Generierung durch die Datenbank.
    private Long id;              // ID des Plans
    private String name;          // Name des Plans

    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true) // Eine-zu-viele-Beziehung zu WorkoutDay.
    private List<WorkoutDay> days; // Liste der Trainingstage
}
