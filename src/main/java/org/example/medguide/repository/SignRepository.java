package org.example.medguide.repository;

import org.example.medguide.model.Sign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignRepository extends JpaRepository<Sign, Long> {
}
