package org.example.medguide.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "diseases")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Disease {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Disease name must not be blank")
    private String name;
    @Size(min = 1, message = "At least one checkbox must be checked")
    @ManyToMany
    @JoinTable(name = "disease_sign", joinColumns = @JoinColumn(name = "disease_id"), inverseJoinColumns = @JoinColumn(name = "sign_id"))
    private List<Sign> signs;
    @Size(min = 1, message = "At least one checkbox must be checked")
    @ManyToMany
    @JoinTable(name = "disease_procedure", joinColumns = @JoinColumn(name = "disease_id"), inverseJoinColumns = @JoinColumn(name = "procedure_id"))
    private List<DiagnosticProcedure> diagnosticProcedures;
    @Size(min = 1, message = "At least one checkbox must be checked")
    @ManyToMany
    @JoinTable(name = "disease_medication", joinColumns = @JoinColumn(name = "disease_id"), inverseJoinColumns = @JoinColumn(name = "medication_id"))
    private List<Medication> medications;

    public Disease(String name) {
        this.name = name;
    }
}
