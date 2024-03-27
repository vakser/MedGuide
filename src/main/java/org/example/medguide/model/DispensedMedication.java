package org.example.medguide.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dispensed_medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DispensedMedication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToOne
    @JoinColumn(name = "prescribed_medication_id")
    private PrescribedMedication prescribedMedication;

}
