package tn.SabatSfakys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.SabatSfakys.model.Panier;
import tn.SabatSfakys.model.Client;
import tn.SabatSfakys.repository.ClientRepository;
import tn.SabatSfakys.repository.PanierRepository;

@Service
public class PanierService {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<Panier> getAllPaniers() {
        return panierRepository.findAll();
    }

    public Panier getPanierById(int id) {
        return panierRepository.findById(id).orElse(null);
    }

    public Panier creerPanier(int clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Client introuvable"));
        Panier panier = new Panier(client);
        return panierRepository.save(panier);
    }

    public void deletePanier(int id) {
        panierRepository.deleteById(id);
    }
    // 🔹 Récupérer le total d'un panier
    public double getTotalPanier(int panierId) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier introuvable"));
        return panier.getTotal();
    }
}
