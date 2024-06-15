package com.example.examen_cc.services;

import com.example.examen_cc.models.Declaration;
import com.example.examen_cc.models.Role;
import com.example.examen_cc.models.TypePropriete;
import com.example.examen_cc.models.Utilisateur;
import com.example.examen_cc.repositories.DeclarationRepository;
import com.example.examen_cc.repositories.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ServiceImpl implements IService {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private DeclarationRepository declarationRepository;

    @Override
    public Utilisateur ajouterVictime(Utilisateur victime) {
        if (victime.getRole() == Role.POLICIER) {
            return new Utilisateur(); // Return an empty object if the role is POLICIER
        }
        return utilisateurRepository.save(victime);
    }

    @Override
    public String ajouterPoliciers(List<Utilisateur> policiers) {
        long count = policiers.stream()
                .filter(policier -> policier.getRole() == Role.POLICIER)
                .count();
        utilisateurRepository.saveAll(policiers);
        return count + " policiers sont ajoutés avec succès!";
    }

    @Override
    public String ajouterDeclarationEtAffecterAVictime(Declaration declaration, String telephone) {
        Utilisateur victime = utilisateurRepository.findByTelephone(telephone);
        if (victime != null) {
            declaration.setVictime(victime);
            declarationRepository.save(declaration);
            return "Déclaration ajoutée et affectée à la victime " + victime.getNom();
        }
        return "Victime non trouvée";
    }

    @Override
    public void affecterPolicierADeclarataion(long idUtilisateur, long idDeclaration) {
        Utilisateur policier = utilisateurRepository.findById(idUtilisateur).orElse(null);
        Declaration declaration = declarationRepository.findById(idDeclaration).orElse(null);
        if (policier != null && declaration != null) {
            declaration.setPolicier(policier);
            declarationRepository.save(declaration);
        }
    }

    @Override
    public void traiterDeclarationAutomatiquement() {
        List<Declaration> declarations = declarationRepository.findAll();
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        declarations.stream()
                .filter(declaration -> !declaration.isEstTraitee() && declaration.getDateDeclaration().isBefore(oneMonthAgo))
                .forEach(declaration -> {
                    declaration.setEstTraitee(true);
                    declaration.setDateTraitement(LocalDate.now());
                    declarationRepository.save(declaration);
                });
    }

    @Override
    public List<Utilisateur> afficherDeclarationsTraitees() {
        return declarationRepository.findDistinctPolicierByProprieteTypePropriete(TypePropriete.VEHICULE);
    }
}
