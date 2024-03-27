package org.example.medguide.repository;

import org.example.medguide.model.MedicationInStorage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationInStorageRepository extends JpaRepository<MedicationInStorage, Long> {
    MedicationInStorage findByName(String name);

    MedicationInStorage findByNameAndActiveIngredient(String name, String activeIngredient);
}
