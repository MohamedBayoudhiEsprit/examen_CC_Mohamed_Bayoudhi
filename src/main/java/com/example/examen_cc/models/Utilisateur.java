package com.example.examen_cc.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String adresse;
    private String telephone;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "Victime")
    private Set<Declaration> declarationVictime;
    @OneToMany(mappedBy = "policier")
    private Set<Declaration> declarationPolicier;
}
