package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import org.springframework.web.bind.annotation.RestController; //Kennzeichnet die Klasse als Controller.
import org.springframework.web.bind.annotation.RequestMapping; //Festlegung eines URL-Pfades.
import org.springframework.web.bind.annotation.GetMapping; //Umgang mit GET-Anfragen.

import java.util.List; //Verwendung von Listen ermöglichen
import java.util.Arrays; //Erstellen von Arrays ermöglichen

// @RestController kennzeichnet diese Klasse als Spring-Controller, der JSON-Daten zurückgibt.
@RestController
//@RequestMapping legt den allgm. URL-Pfad fest, unter dem die Anfragen bearbeitet werden.
@RequestMapping("/api/plans")

public class PlanController {
    
    //Diese Methode wird aufgerufen, wenn eine GET-Anfrage an den Pfad /api/plans schickt.
    @GetMapping
    public List<Plan> getAllPlans() {
        //Traingsplan Liste erstellen
        return Arrays.asList(
            new Plan(1L, "Plan 1", "Dies ist der erste Trainingsplan."),
            new Plan(2L, "Plan 2", "Dies ist der zweite Trainingsplan.")
        );
    }
    
}
