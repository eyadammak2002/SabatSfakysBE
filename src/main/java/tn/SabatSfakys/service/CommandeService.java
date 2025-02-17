package tn.SabatSfakys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.SabatSfakys.model.Commande;
import tn.SabatSfakys.model.Panier;
import tn.SabatSfakys.model.Client;
import tn.SabatSfakys.repository.CommandeRepository;
import tn.SabatSfakys.repository.PanierRepository;
import tn.SabatSfakys.repository.ClientRepository;

@Service
public class CommandeService {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    public Commande getCommandeById(int id) {
        return commandeRepository.findById(id).orElse(null);
    }

    public Commande creerCommande(int panierId) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new RuntimeException("Panier introuvable"));
        Commande commande = new Commande(panier.getClient());

        // Ajouter les articles du panier à la commande
        commande.setArticlesCommandes(panier.getLignesPanier());

        // Sauvegarder la commande
        Commande savedCommande = commandeRepository.save(commande);

        // Supprimer le panier après la commande
        panierRepository.deleteById(panierId);

        return savedCommande;
    }

    public void deleteCommande(int id) {
        commandeRepository.deleteById(id);
    }
}
