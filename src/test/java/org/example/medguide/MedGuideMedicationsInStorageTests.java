package org.example.medguide;

import org.example.medguide.model.MedicationInStorage;
import org.example.medguide.repository.MedicationInStorageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class MedGuideMedicationsInStorageTests {
    @Autowired
    private MedicationInStorageRepository medicationInStorageRepository;

    @Test
    public void testCreateMedication() {
        MedicationInStorage medicationInStorage = new MedicationInStorage("Cardura", "Doxazosin", 2);
        MedicationInStorage savedMedicationInstorage = medicationInStorageRepository.save(medicationInStorage);
        assertThat(savedMedicationInstorage.getId()).isGreaterThan(0);
        List<MedicationInStorage> medicationsInStorage =  medicationInStorageRepository.findAll();
        assertThat(medicationsInStorage.size()).isEqualTo(1);
    }

    @Test
    public void testUpdateAmountOfMedication() {
        MedicationInStorage medicationInStorage = medicationInStorageRepository.findByNameAndActiveIngredient("Cardura", "Doxazosin");
        if (medicationInStorage != null) {
            assertThat(medicationInStorage.getAmount()).isEqualTo(2);
            medicationInStorage.setAmount(5);
            medicationInStorageRepository.save(medicationInStorage);
            assertThat(medicationInStorage.getAmount()).isEqualTo(5);
        } else {
            System.out.println("Medication does not exist in the storage");
        }
    }

    @Test
    public void testDeleteMedication() {
        MedicationInStorage medicationInStorage = medicationInStorageRepository.findByNameAndActiveIngredient("Cardura", "Doxazosin");
        if (medicationInStorage != null) {
            medicationInStorageRepository.delete(medicationInStorage);
        } else {
            System.out.println("Medication not found!");
        }
        assertThat(medicationInStorageRepository.findByNameAndActiveIngredient("Cardura", "Doxazosin")).isNull();
    }
}
