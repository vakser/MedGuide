package org.example.medguide.repository;

import org.example.medguide.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    Disease findByName(String name);
}
