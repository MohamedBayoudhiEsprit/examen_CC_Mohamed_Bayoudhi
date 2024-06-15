package com.example.examen_cc.repositories;

import com.example.examen_cc.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long>{
    Utilisateur findByTelephone(String telephone);
}
