package org.example.medguide.service;

import lombok.RequiredArgsConstructor;
import org.example.medguide.model.DispensedMedication;
import org.example.medguide.repository.DispensedMedicationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DispensedMedicationService {
    private final DispensedMedicationRepository dispensedMedicationRepository;

    public void createDispensedMedication(DispensedMedication medication) {
        dispensedMedicationRepository.save(medication);
    }
}
