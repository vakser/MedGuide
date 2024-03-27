package org.example.medguide.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.medguide.model.PrescribedMedication;
import org.example.medguide.model.Prescription;
import org.example.medguide.repository.PrescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;

    public List<Prescription> getPrescriptions() {
        return prescriptionRepository.findAll();
    }

    public void addPrescription(Prescription prescription) {
        prescriptionRepository.save(prescription);
    }

    public Prescription findPrescriptionById(Long prescriptionId) {
        return prescriptionRepository.findById(prescriptionId).orElseThrow(() -> new EntityNotFoundException("Prescription with id " + prescriptionId + " not found"));
    }

    public int getNumberOfPrescribedMedicationsNonDispensed(Prescription prescription) {
        List<PrescribedMedication> prescribedMedications = prescription.getPrescribedMedications();
        int count = 0;
        for (PrescribedMedication prescribedMedication : prescribedMedications) {
            if (!prescribedMedication.isDispensed()) {
                count++;
            }
        }
        return count;
    }

    public Prescription findByPrescribedMedicationId(Long id) {
        Prescription prescriptionWithPrescribedMedicationId = null;
        for (Prescription prescription : prescriptionRepository.findAll()) {
            for (PrescribedMedication prescribedMedication : prescription.getPrescribedMedications()) {
                if (Objects.equals(prescribedMedication.getId(), id)) {
                    prescriptionWithPrescribedMedicationId = prescription;
                    break;
                }
            }
        }
        return prescriptionWithPrescribedMedicationId;
    }

    public int getNumberOfPrescribedButNotDispensedMedicationsForPrescription(Prescription prescription) {
        List<PrescribedMedication> prescribedMedications = prescription.getPrescribedMedications();
        int countNonDispensedMedications = 0;
        for (PrescribedMedication prescribedMedication : prescribedMedications) {
            if (!prescribedMedication.isDispensed()) {
                countNonDispensedMedications++;
            }
        }
        return countNonDispensedMedications;
    }

}
