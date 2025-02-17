package tn.SabatSfakys.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.SabatSfakys.model.LignePanier;
import tn.SabatSfakys.service.LignePanierService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/lignePanier")
public class LignePanierController {

    @Autowired
    private LignePanierService lignePanierService;

    // Récupérer toutes les lignes du panier
    @GetMapping
    public List<LignePanier> getAllLignesPanier() {
        return lignePanierService.getAllLignesPanier();
    }

    // Récupérer une ligne du panier par ID
    @GetMapping("/{id}")
    public LignePanier getLignePanier(@PathVariable("id") int id) {
        return lignePanierService.getLignePanierById(id);
    }

    // Ajouter un article au panier
    @PostMapping("/{panierId}/{articleId}/{quantite}")
    public LignePanier ajouterArticleAuPanier(@PathVariable("panierId") int panierId, @PathVariable("articleId") int articleId, @PathVariable("quantite") int quantite) {
        return lignePanierService.ajouterArticleAuPanier(panierId, articleId, quantite);
    }

    // Mettre à jour une ligne du panier
    @PutMapping
    public void updateLignePanier(@RequestBody LignePanier lignePanier) {
        lignePanierService.updateLignePanier(lignePanier);
    }

    // Supprimer une ligne du panier
    @DeleteMapping("/{id}")
    public void deleteLignePanier(@PathVariable("id") int id) {
        lignePanierService.deleteLignePanier(id);
    }
    
 // 🔹 Endpoint pour récupérer le prix total d'une ligne de panier
    @GetMapping("/{id}/total")
    public double getPrixTotalLignePanier(@PathVariable("id") int id) {
        return lignePanierService.getPrixTotalLignePanier(id);
    }
}
