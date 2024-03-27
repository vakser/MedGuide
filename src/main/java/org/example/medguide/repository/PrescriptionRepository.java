package org.example.medguide.repository;

import org.example.medguide.model.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Prescription findByDiagnosis(String diagnosis);
}
