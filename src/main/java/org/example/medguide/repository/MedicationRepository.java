package org.example.medguide.repository;

import org.example.medguide.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    Medication findByName(String name);
    List<Medication> findByActiveIngredient(String activeIngredient);
}
