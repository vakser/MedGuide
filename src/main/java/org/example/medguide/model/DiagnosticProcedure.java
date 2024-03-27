package org.example.medguide.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "diagnostic_procedures")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticProcedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "disease_procedure", joinColumns = @JoinColumn(name = "procedure_id"), inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private List<Disease> diseases;

    public DiagnosticProcedure(String name) {
        this.name = name;
    }
}
