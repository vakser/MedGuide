package org.example.medguide.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String activeIngredient;
    @ManyToMany
    @JoinTable(name = "disease_medication", joinColumns = @JoinColumn(name = "medication_id"), inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private List<Disease> diseases;

    public Medication(String name, String activeIngredient) {
        this.name = name;
        this.activeIngredient = activeIngredient;
    }
}
