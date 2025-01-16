package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.springframework.http.MediaType;
import static org.mockito.ArgumentMatchers.any; // Stelle sicher, dass dieser Import vorhanden ist
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles("test")
public class PlanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanRepository planRepository;

    @BeforeEach
    void setUpMockRepository() {
        Plan testPlan = new Plan(1L, "Test Plan", List.of());

        // Mock für findById
        when(planRepository.findById(1L)).thenReturn(Optional.of(testPlan));

        // Mock für save
        when(planRepository.save(any(Plan.class))).thenAnswer(invocation -> {
            Plan plan = invocation.getArgument(0); // Erhalte das gespeicherte Plan-Objekt
            plan.setPlanId(1L); // Setze eine ID, um das Verhalten der Datenbank zu simulieren
            return plan;
        });

        // Mock für delete
        doNothing().when(planRepository).delete(any(Plan.class));
    }

    @Test
    public void testAddPlan() throws Exception {
        String jsonContent = """
                {
                    "planName": "Test Plan",
                    "workoutDays": [
                        {
                            "workoutDayName": "Monday",
                            "restDay": false,
                            "exercises": [
                                {
                                    "exerciseName": "Push-Ups",
                                    "exerciseSets": 3,
                                    "exerciseReps": 10
                                }
                            ]
                        }
                    ]
                }
                """;

        mockMvc.perform(post("/api/plans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonContent))
                .andDo(print()) // Gibt die Antwort in der Konsole aus
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.planName").value("Test Plan"));
    }

    @Test
    public void testDeletePlan() throws Exception {
        mockMvc.perform(delete("/api/plans/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPlanRelationships() {
        Plan plan = new Plan(1L, "Sample Plan", List.of());
        WorkoutDay workoutDay = new WorkoutDay(1L, "Monday", false, List.of(), plan);
        Exercise exercise = new Exercise(1L, "Push-Ups", 3, 10, workoutDay);

        workoutDay.setExercises(List.of(exercise));
        plan.setWorkoutDays(List.of(workoutDay));

        Assertions.assertNotNull(plan.getWorkoutDays());
        Assertions.assertEquals("Monday", plan.getWorkoutDays().get(0).getWorkoutDayName());
    }

    @Test
    public void testGetAllPlansWithData() throws Exception {
        // Mock-Datenbank mit mehreren Plänen initialisieren
        Plan plan1 = new Plan(1L, "Plan 1", List.of());
        Plan plan2 = new Plan(2L, "Plan 2", List.of());
        when(planRepository.findAll()).thenReturn(List.of(plan1, plan2));

        // API-Request ausführen und die Antwort überprüfen
        mockMvc.perform(get("/api/plans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))) // Überprüfen, dass zwei Pläne zurückgegeben werden
                .andExpect(jsonPath("$[0].planName").value("Plan 1")) // Überprüfen des ersten Plans
                .andExpect(jsonPath("$[1].planName").value("Plan 2")); // Überprüfen des zweiten Plans
    }

    @Test
    public void testGetPlansWithSpecificNumberOfWorkoutDays() throws Exception {
        // Setup: Erstellen von Mock-Daten
        WorkoutDay day1 = new WorkoutDay(1L, "Monday", false, List.of(), null);
        WorkoutDay day2 = new WorkoutDay(2L, "Tuesday", false, List.of(), null);
        Plan plan1 = new Plan(1L, "Plan with 1 Day", List.of(day1));
        Plan plan2 = new Plan(2L, "Plan with 2 Days", List.of(day1, day2));
        when(planRepository.findAll()).thenReturn(List.of(plan1, plan2));

        // API-Request: Abrufen aller Pläne
        mockMvc.perform(get("/api/plans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2))) // Überprüfen, dass zwei Pläne zurückgegeben werden
                .andExpect(jsonPath("$[0].planName").value("Plan with 1 Day")) // Überprüfen des Namens des ersten Plans
                .andExpect(jsonPath("$[0].workoutDays", hasSize(1))) // Überprüfen, dass der erste Plan 1 Tag hat
                .andExpect(jsonPath("$[1].planName").value("Plan with 2 Days")) // Überprüfen des Namens des zweiten
                                                                                // Plans
                .andExpect(jsonPath("$[1].workoutDays", hasSize(2))); // Überprüfen, dass der zweite Plan 2 Tage hat
    }

    @Test
    public void testGetPlansWithSpecificNumberOfExercisesOnMonday() throws Exception {
        // Setup: Erstellen von Mock-Daten
        Exercise exercise1 = Exercise.builder()
                .exerciseId(1L)
                .exerciseName("Push Ups")
                .exerciseSets(3)
                .exerciseReps(15)
                .build();

        Exercise exercise2 = Exercise.builder()
                .exerciseId(2L)
                .exerciseName("Pull Ups")
                .exerciseSets(3)
                .exerciseReps(10)
                .build();

        WorkoutDay monday = new WorkoutDay(
                1L,
                "Monday",
                false,
                List.of(exercise1, exercise2),
                null);

        Plan plan = new Plan(
                1L,
                "Plan with Exercises on Monday",
                List.of(monday));

        when(planRepository.findAll()).thenReturn(List.of(plan));

        // API-Request: Abrufen aller Pläne
        mockMvc.perform(get("/api/plans"))
                .andExpect(status().isOk()) // Überprüfen, dass die Antwort erfolgreich ist
                .andExpect(jsonPath("$", hasSize(1))) // Überprüfen, dass ein Plan zurückgegeben wird
                .andExpect(jsonPath("$[0].planName").value("Plan with Exercises on Monday")) // Überprüfen des
                                                                                             // Plan-Namens
                .andExpect(jsonPath("$[0].workoutDays[0].workoutDayName").value("Monday")) // Überprüfen des Tagesnamens
                .andExpect(jsonPath("$[0].workoutDays[0].exercises", hasSize(2))) // Überprüfen, dass es 2 Übungen gibt
                .andExpect(jsonPath("$[0].workoutDays[0].exercises[0].exerciseName").value("Push Ups")) // Überprüfen
                                                                                                        // der ersten
                                                                                                        // Übung
                .andExpect(jsonPath("$[0].workoutDays[0].exercises[1].exerciseName").value("Pull Ups")); // Überprüfen
                                                                                                         // der zweiten
                                                                                                         // Übung
    }

    @Test
    public void testGetAllPlansWhenEmpty() throws Exception {
        // Mock für eine leere Datenbank
        when(planRepository.findAll()).thenReturn(List.of());

        // API-Aufruf ausführen und überprüfen
        mockMvc.perform(get("/api/plans"))
                .andExpect(status().isOk()) // Status 200 erwarten
                .andExpect(jsonPath("$", hasSize(0))); // Leeres Array erwarten
    }

}