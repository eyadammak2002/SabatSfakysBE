package tn.SabatSfakys.model;

import jakarta.persistence.*;

@Entity
public class LignePanier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "panier_id", nullable = false)
    private Panier panier;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private Article article;
    
    @ManyToOne
    @JoinColumn(name = "pack_id", nullable = false)
    private Pack pack;
    
    @ManyToOne
    @JoinColumn(name = "commande_id")  // Ajoute cette relation
    private Commande commande;

    private int quantite;
    private double prixUnitaire;

    public LignePanier() {}

    public LignePanier(Panier panier, Article article, int quantite, double prixUnitaire) {
        this.panier = panier;
        this.article = article;
        this.quantite = quantite;
        this.prixUnitaire = prixUnitaire;
    }

    public int getId() {
        return id;
    }

    public Panier getPanier() {
        return panier;
    }

    public void setPanier(Panier panier) {
        this.panier = panier;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }
    
    // 🟢 Calcul dynamique du prix total d'une ligne
    public double getPrixTotal() {
        return this.quantite * this.prixUnitaire;
    }
}
