package org.example.medguide.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "signs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Sign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    @JoinTable(name = "disease_sign", joinColumns = @JoinColumn(name = "sign_id"), inverseJoinColumns = @JoinColumn(name = "disease_id"))
    private List<Disease> diseases;

    public Sign(String name) {
        this.name = name;
    }
}
