package org.example.medguide;

import org.example.medguide.model.DiagnosticProcedure;
import org.example.medguide.model.Disease;
import org.example.medguide.model.Medication;
import org.example.medguide.model.Sign;
import org.example.medguide.repository.DiagnosticProcedureRepository;
import org.example.medguide.repository.DiseaseRepository;
import org.example.medguide.repository.MedicationRepository;
import org.example.medguide.repository.SignRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestPropertySource("/application-test.properties")
@SpringBootTest
public class MedGuideApplicationDiseasesTests {
    @Autowired
    private DiseaseRepository diseaseRepository;
    @Autowired
    private SignRepository signRepository;
    @Autowired
    private DiagnosticProcedureRepository diagnosticProcedureRepository;
    @Autowired
    private MedicationRepository medicationRepository;

    @Test
    public void testCreateDisease() {
        Disease disease = new Disease("Myocardial infarction");
        Sign sign1 = new Sign("Chest pain");
        signRepository.save(sign1);
        Sign sign2 = new Sign("Shortness of breath");
        signRepository.save(sign2);
        disease.setSigns(List.of(sign1, sign2));
        DiagnosticProcedure diagnosticProcedure1 = new DiagnosticProcedure("ECG");
        diagnosticProcedureRepository.save(diagnosticProcedure1);
        DiagnosticProcedure diagnosticProcedure2 = new DiagnosticProcedure("Cardiac enzymes");
        diagnosticProcedureRepository.save(diagnosticProcedure2);
        disease.setDiagnosticProcedures(List.of(diagnosticProcedure1, diagnosticProcedure2));
        Medication medication1 = new Medication("Actilyse", "Alteplase");
        medicationRepository.save(medication1);
        Medication medication2 = new Medication("Fentanyl", "Fentanyl");
        medicationRepository.save(medication2);
        disease.setMedications(List.of(medication1, medication2));
        Disease savedDisease = diseaseRepository.save(disease);
        assertThat(savedDisease.getId()).isGreaterThan(0);
    }

    @Test
    public void testDeleteDisease() {
        Disease disease = diseaseRepository.findByName("Myocardial infarction");
        if (disease != null) {
            diseaseRepository.delete(disease);
        } else {
            System.out.println("Disease not found!");
        }
        assertThat(diseaseRepository.findByName("Myocardial infarction")).isNull();
    }
}
