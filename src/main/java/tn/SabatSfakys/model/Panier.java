package tn.SabatSfakys.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Panier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignePanier> lignesPanier;

    public Panier() {}

    public Panier(Client client) {
        this.client = client;
    }

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LignePanier> getLignesPanier() {
        return lignesPanier;
    }

    public void setLignesPanier(List<LignePanier> lignesPanier) {
        this.lignesPanier = lignesPanier;
    }
    
    // 🟢 Calcul dynamique du total du panier
    public double getTotal() {
        return lignesPanier.stream()
                .mapToDouble(lp -> lp.getQuantite() * lp.getPrixUnitaire())
                .sum();
    }
}
