package com.example.examen_cc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Declaration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDeclaration;
    private boolean estTraitee;
    private LocalDate dateTraitement;
    private String description;
    @ManyToOne
    private Utilisateur Victime;
    @ManyToOne
    private Utilisateur policier;
    @OneToOne(mappedBy = "declaration")
    private Propriete propriete;
}
