package org.example.medguide.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.medguide.model.PrescribedMedication;
import org.example.medguide.repository.PrescribedMedicationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrescribedMedicationService {
    private final PrescribedMedicationRepository prescribedMedicationRepository;

    public PrescribedMedication findPrescribedMedicationById(long id) {
        return prescribedMedicationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Prescribed medication with id " + id + " not found"));
    }

    public void createOrUpdatePrescribedMedication(PrescribedMedication medication) {
        prescribedMedicationRepository.save(medication);
    }
}
