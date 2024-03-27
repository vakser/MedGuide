package org.example.medguide.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "prescribed_medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrescribedMedication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String activeIngredient;
    @NotNull(message = "Amount must not be null")
    @Min(value = 0, message = "Value must be greater than or equal to {value}.")
    int amount;
    boolean dispensed;

    public PrescribedMedication(String name, String activeIngredient, int amount) {
        this.name = name;
        this.activeIngredient = activeIngredient;
        this.amount = amount;
    }
}
