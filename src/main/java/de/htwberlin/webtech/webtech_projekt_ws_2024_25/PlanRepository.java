package de.htwberlin.webtech.webtech_projekt_ws_2024_25;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository-Interface für die Plan-Entitäten.
@Repository
public interface PlanRepository extends JpaRepository<Plan, Long> {
    // JpaRepository bietet grundlegende CRUD-Operationen, sodass keine zusätzliche
    // Methode erforderlich ist.
}
