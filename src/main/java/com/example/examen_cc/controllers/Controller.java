package com.example.examen_cc.controllers;

import com.example.examen_cc.models.Declaration;
import com.example.examen_cc.models.Utilisateur;
import com.example.examen_cc.services.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private IService service;

    @PostMapping("/ajouterVictime")
    public Utilisateur ajouterVictime(@RequestBody Utilisateur victime) {
        return service.ajouterVictime(victime);
    }

    @PostMapping("/ajouterPoliciers")
    public String ajouterPoliciers(@RequestBody List<Utilisateur> policiers) {
        return service.ajouterPoliciers(policiers);
    }

    @PostMapping("/ajouterDeclarationEtAffecterAVictime")
    public String ajouterDeclarationEtAffecterAVictime(@RequestBody Declaration declaration, @RequestParam String telephone) {
        return service.ajouterDeclarationEtAffecterAVictime(declaration, telephone);
    }

    @PostMapping("/affecterPolicierADeclarataion")
    public void affecterPolicierADeclarataion(@RequestParam long idUtilisateur, @RequestParam long idDeclaration) {
        service.affecterPolicierADeclarataion(idUtilisateur, idDeclaration);
    }

    @PostMapping("/traiterDeclarationAutomatiquement")
    public void traiterDeclarationAutomatiquement() {
        service.traiterDeclarationAutomatiquement();
    }

    @GetMapping("/afficherDeclarationsTraitees")
    public List<Utilisateur> afficherDeclarationsTraitees() {
        return service.afficherDeclarationsTraitees();
    }
}
