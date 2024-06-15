package com.example.examen_cc.repositories;

import com.example.examen_cc.models.Declaration;
import com.example.examen_cc.models.TypePropriete;
import com.example.examen_cc.models.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclarationRepository extends JpaRepository<Declaration, Long> {
    @Query("SELECT DISTINCT d.policier FROM Declaration d WHERE d.propriete.typePropriete = ?1")
    List<Utilisateur> findDistinctPolicierByProprieteTypePropriete(TypePropriete typePropriete);
}
