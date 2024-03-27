package org.example.medguide.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.medguide.model.PrescribedMedication;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PrescriptionCreationDto {
    private String diagnosis;
    private List<PrescribedMedication> medications = new ArrayList<>();

    public void addPrescribedMedication(PrescribedMedication prescribedMedication) {
        this.medications.add(prescribedMedication);
    }
}
