package tn.esprit.pidev.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.entities.Utilisateur;
import tn.esprit.pidev.services.api.UtilisateurService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;
    @GetMapping("/getaAllUtilisateurs")
    @ResponseBody
    public List<Utilisateur> getUsers() {

        return utilisateurService.findAll();
    }
    @GetMapping("/retrieveUser/{id}")
    @ResponseBody
    public Optional<Utilisateur> retrieveUtilisateur(@PathVariable("id") Long id) {
        return utilisateurService.findOne(id);
    }
    @PostMapping("/addUtilisateur")
    @ResponseBody
    public Utilisateur addUtilisateur(@RequestBody Utilisateur utilisateur)
    {

        return  utilisateurService.save(utilisateur);
    }
    @DeleteMapping("/removeUtilisateur/{id}")
    @ResponseBody
    public void supprimerUtilisateur(@PathVariable("id") Long id){
        utilisateurService.delete(id);
    }
    @PutMapping("/modifyUtilisateur")
    @ResponseBody
    public Utilisateur modifierUtilisateur(@RequestBody Utilisateur utilisateur){
        return  utilisateurService.update(utilisateur);
    }
}
