//Die Klasse Plan, die die Struktur eines Trainingplans beschreibt.
package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import lombok.AllArgsConstructor; //Lombok-Annotation, für einen Konstruktor.
import lombok.Getter; //Lombok-Annotation, für Getter.
import lombok.Setter; //Lombok-Annotation, für Setter.

//Lombok-Annotation, um Getter und Setter automatisch zu generieren.
@Getter
@Setter
@AllArgsConstructor
//Die Klasse Plan repräsentiert einen Trainingsplan.
public class Plan {

    private long id;
    private String name;
    private String description;    
}

