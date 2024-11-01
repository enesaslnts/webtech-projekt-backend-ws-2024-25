//Die Klasse Plan, die die Struktur eines Trainingplans beschreibt.
package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import lombok.AllArgsConstructor; //Lombok-Annotation, für einen Konstruktor.
import lombok.Getter; //Lombok-Annotation, für Getter.
import lombok.Setter; //Lombok-Annotation, für Setter.
import java.util.List; //List wird benötigt, um mehrere Übungen zu speichern.

//Lombok-Annotation, um Getter und Setter automatisch zu generieren.
@Getter
@Setter
@AllArgsConstructor
//Die Klasse Plan repräsentiert einen Trainingsplan.
public class Plan {

    private long id;
    private String name;
    private String description; 
    private List<WorkoutDay> days; //Liste von Trainingstagen, die den Plan ausmachen.   
}

