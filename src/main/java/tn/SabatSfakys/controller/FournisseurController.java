package tn.SabatSfakys.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.SabatSfakys.model.Fournisseur;
import tn.SabatSfakys.service.FournisseurService;
import jakarta.mail.MessagingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/fournisseur")
public class FournisseurController {

    @Autowired  
    FournisseurService fournisseurService;

    // Récupérer tous les fournisseurs
    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurService.getAllFournisseurs();
    }

    // Récupérer un fournisseur par ID
    @GetMapping("/{id}")
    public ResponseEntity<Fournisseur> getFournisseur(@PathVariable int id) {
        try {
            Fournisseur fournisseur = fournisseurService.getFournisseurById(id);
            return ResponseEntity.ok(fournisseur);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Créer ou mettre à jour un fournisseur
    @PostMapping
    public ResponseEntity<String> createFournisseur(@RequestBody Fournisseur fournisseur) {
        fournisseurService.saveOrUpdate(fournisseur);
        return ResponseEntity.status(HttpStatus.CREATED).body("Fournisseur créé avec succès");
    }

    // Mettre à jour un fournisseur existant
    @PutMapping("/{id}")
    public ResponseEntity<Fournisseur> updateFournisseur(@PathVariable int id, @RequestBody Fournisseur updatedFournisseur) {
        try {
            Fournisseur fournisseur = fournisseurService.updateFournisseur(id, updatedFournisseur);
            return ResponseEntity.ok(fournisseur);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Supprimer un fournisseur
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFournisseur(@PathVariable int id) {
        try {
            fournisseurService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Fournisseur supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du fournisseur");
        }
    }
}
