package org.example.medguide.repository;

import org.example.medguide.model.DispensedMedication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DispensedMedicationRepository extends JpaRepository<DispensedMedication, Long> {
}
