package org.example.medguide.repository;

import org.example.medguide.model.DiagnosticProcedure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticProcedureRepository extends JpaRepository<DiagnosticProcedure, Long> {
}
