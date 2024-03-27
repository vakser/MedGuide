package org.example.medguide.service;

import lombok.RequiredArgsConstructor;
import org.example.medguide.model.DiagnosticProcedure;
import org.example.medguide.repository.DiagnosticProcedureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiagnosticProcedureService {
    private final DiagnosticProcedureRepository diagnosticProcedureRepository;

    public List<DiagnosticProcedure> getDiagnosticProcedures() {
        return diagnosticProcedureRepository.findAll();
    }
}
