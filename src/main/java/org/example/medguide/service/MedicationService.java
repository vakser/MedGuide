package org.example.medguide.service;

import lombok.RequiredArgsConstructor;
import org.example.medguide.model.Medication;
import org.example.medguide.repository.MedicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationService {
    private final MedicationRepository medicationRepository;

    public List<Medication> getMedications() {
        return medicationRepository.findAll();
    }

}
