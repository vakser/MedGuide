package org.example.medguide.repository;

import org.example.medguide.model.PrescribedMedication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescribedMedicationRepository extends JpaRepository<PrescribedMedication, Long> {
}
