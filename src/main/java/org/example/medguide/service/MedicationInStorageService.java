package org.example.medguide.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.medguide.model.Disease;
import org.example.medguide.model.MedicationInStorage;
import org.example.medguide.repository.MedicationInStorageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationInStorageService {
    private final MedicationInStorageRepository medicationInStorageRepository;

    public List<MedicationInStorage> getMedicationsInStorage() {
        return medicationInStorageRepository.findAll();
    }

    public void createOrUpdateMedicationInStorage(MedicationInStorage medication) {
        medicationInStorageRepository.save(medication);
    }

    public MedicationInStorage findMedicationInStorageById(Long medicationId) {
        return medicationInStorageRepository.findById(medicationId).orElseThrow(() -> new EntityNotFoundException("Medication with id " + medicationId + " not found"));
    }

    public MedicationInStorage findMedicationInStorageByName(String name) {
        return medicationInStorageRepository.findByName(name);
    }

    public void deleteMedicationFromStorage(Long id) {
        medicationInStorageRepository.deleteById(id);
    }

    public boolean areNameAndActiveIngredientUnique(String name, String activeIngredient) {
        MedicationInStorage medicationInStorageByNameAndActiveIngredient = medicationInStorageRepository.findByNameAndActiveIngredient(name, activeIngredient);
        return medicationInStorageByNameAndActiveIngredient == null;
    }
}
