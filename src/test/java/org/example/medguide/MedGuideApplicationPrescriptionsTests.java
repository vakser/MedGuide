package org.example.medguide;

import org.example.medguide.model.Disease;
import org.example.medguide.model.PrescribedMedication;
import org.example.medguide.model.Prescription;
import org.example.medguide.repository.PrescribedMedicationRepository;
import org.example.medguide.repository.PrescriptionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class MedGuideApplicationPrescriptionsTests {
    @Autowired
    private PrescriptionRepository prescriptionRepository;
    @Autowired
    private PrescribedMedicationRepository prescribedMedicationRepository;

    @Test
    public void testAddPrescription() {
        Prescription prescription = new Prescription("Knee osteoarthritis");
        PrescribedMedication prescribedMedication1 = new PrescribedMedication("Brufen", "Ibuprofen", 3);
        PrescribedMedication prescribedMedication2 = new PrescribedMedication("Cymbalta", "Duloxetine", 3);
        prescription.setPrescribedMedications(List.of(prescribedMedication1, prescribedMedication2));
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        assertThat(savedPrescription.getId()).isGreaterThan(0);
        assertThat(prescriptionRepository.findAll().size()).isEqualTo(1);
    }

    @Test
    public void deletePrescription() {
        Prescription prescription = prescriptionRepository.findByDiagnosis("Knee osteoarthritis");
        if (prescription != null) {
            prescriptionRepository.delete(prescription);
        } else {
            System.out.println("Disease not found!");
        }
        assertThat(prescriptionRepository.findByDiagnosis("Knee osteoarthritis")).isNull();
    }
}
