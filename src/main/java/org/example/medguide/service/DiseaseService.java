package org.example.medguide.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.medguide.model.Disease;
import org.example.medguide.repository.DiseaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiseaseService {
    private final DiseaseRepository diseaseRepository;

    public List<Disease> getDiseases() {
        return diseaseRepository.findAll();
    }

    public void addDisease(Disease disease) {
        diseaseRepository.save(disease);
    }

    public Disease getDiseaseById(Long diseaseId) {
        return diseaseRepository.findById(diseaseId).orElseThrow(() -> new EntityNotFoundException("Disease with id " + diseaseId + " not found"));
    }

    public void deleteDisease(Long id) {
        diseaseRepository.deleteById(id);
    }

    public boolean isNameUnique(String name) {
        Disease diseaseByName = diseaseRepository.findByName(name);
        return diseaseByName == null;
    }
}
