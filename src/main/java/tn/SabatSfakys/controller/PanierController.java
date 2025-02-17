package tn.SabatSfakys.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.SabatSfakys.model.Panier;
import tn.SabatSfakys.service.PanierService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/panier")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @GetMapping
    public List<Panier> getAllPaniers() {
        return panierService.getAllPaniers();
    }

    @GetMapping("/{id}")
    public Panier getPanier(@PathVariable("id") int id) {
        return panierService.getPanierById(id);
    }

    @PostMapping("/{clientId}")
    public Panier creerPanier(@PathVariable("clientId") int clientId) {
        return panierService.creerPanier(clientId);
    }

    @DeleteMapping("/{id}")
    public void deletePanier(@PathVariable("id") int id) {
        panierService.deletePanier(id);
    }
    
    // 🔹 Endpoint pour récupérer le total du panier
    @GetMapping("/{id}/total")
    public double getTotalPanier(@PathVariable("id") int id) {
        return panierService.getTotalPanier(id);
    }
}
