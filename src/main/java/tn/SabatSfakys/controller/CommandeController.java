package tn.SabatSfakys.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.SabatSfakys.model.Commande;
import tn.SabatSfakys.service.CommandeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/commande")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public Commande getCommande(@PathVariable("id") int id) {
        return commandeService.getCommandeById(id);
    }

    @PostMapping("/{panierId}")
    public Commande creerCommande(@PathVariable("panierId") int panierId) {
        return commandeService.creerCommande(panierId);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable("id") int id) {
        commandeService.deleteCommande(id);
    }
}
