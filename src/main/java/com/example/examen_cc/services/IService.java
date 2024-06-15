package com.example.examen_cc.services;

import com.example.examen_cc.models.Declaration;
import com.example.examen_cc.models.Utilisateur;

import java.util.List;

public interface IService {
    Utilisateur ajouterVictime(Utilisateur victime);
    String ajouterPoliciers(List<Utilisateur> policiers);
    String ajouterDeclarationEtAffecterAVictime(Declaration declaration, String telephone);
    void affecterPolicierADeclarataion(long idUtilisateur, long idDeclaration);
    void traiterDeclarationAutomatiquement();
    List<Utilisateur> afficherDeclarationsTraitees();
}
