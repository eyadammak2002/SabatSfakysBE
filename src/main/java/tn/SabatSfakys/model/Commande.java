package tn.SabatSfakys.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Date dateCommande;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LignePanier> articlesCommandes;

    public Commande() {}

    public Commande(Client client) {
        this.client = client;
        this.dateCommande = new Date();
    }

    public int getId() {
        return id;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<LignePanier> getArticlesCommandes() {
        return articlesCommandes;
    }

    public void setArticlesCommandes(List<LignePanier> articlesCommandes) {
        this.articlesCommandes = articlesCommandes;
    }
}
