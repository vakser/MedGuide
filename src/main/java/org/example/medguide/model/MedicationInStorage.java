package org.example.medguide.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Table(name = "medications_in_storage")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationInStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name must not be blank")
    private String name;
    @NotBlank(message = "Active ingredient must not be blank")
    private String activeIngredient;
    @NotNull(message = "Amount must not be null")
    @Min(value = 0, message = "Value must be greater than or equal to {value}.")
    private int amount;

    public MedicationInStorage(String name, String activeIngredient, int amount) {
        this.name = name;
        this.activeIngredient = activeIngredient;
        this.amount = amount;
    }
}
