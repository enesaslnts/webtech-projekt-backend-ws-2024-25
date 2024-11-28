package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import lombok.AllArgsConstructor; // Lombok-Annotation für Konstruktor mit allen Feldern.
import lombok.Getter; // Lombok-Annotation für Getter-Methoden.
import lombok.NoArgsConstructor; // Lombok-Annotation für leeren Konstruktor.
import lombok.Setter; // Lombok-Annotation für Setter-Methoden.
//import jakarta.persistence.*; // Importiert JPA-Annotationen.

@Getter
@Setter
// @AllArgsConstructor
// @NoArgsConstructor
// @Entity // Markiert die Klasse als JPA-Entität für die Datenbank.

public class Exercise {

   private String exercise; // Name der Übung
   private int sets; // Anzahl der Sätze
   private int reps; // Anzahl der Wiederholungen
}
