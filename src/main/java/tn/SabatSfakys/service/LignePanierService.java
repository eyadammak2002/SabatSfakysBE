package tn.SabatSfakys.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.SabatSfakys.model.LignePanier;
import tn.SabatSfakys.model.Panier;
import tn.SabatSfakys.model.Article;
import tn.SabatSfakys.repository.LignePanierRepository;
import tn.SabatSfakys.repository.PanierRepository;
import tn.SabatSfakys.repository.ArticleRepository;

@Service
public class LignePanierService {

    @Autowired
    private LignePanierRepository lignePanierRepository;

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private ArticleRepository articleRepository;

    // Récupérer toutes les lignes de panier
    public List<LignePanier> getAllLignesPanier() {
        return lignePanierRepository.findAll();
    }

    // Récupérer une ligne de panier par ID
    public LignePanier getLignePanierById(int id) {
        return lignePanierRepository.findById(id).orElse(null);
    }

    // Ajouter un article à un panier
    public LignePanier ajouterArticleAuPanier(int panierId, int articleId, int quantite) {
        Panier panier = panierRepository.findById(panierId).orElseThrow(() -> new RuntimeException("Panier introuvable"));
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article introuvable"));

        LignePanier lignePanier = new LignePanier(panier, article, quantite, article.getPrixVente());
        return lignePanierRepository.save(lignePanier);
    }

    // Mettre à jour une ligne de panier
    public void updateLignePanier(LignePanier lignePanier) {
        lignePanierRepository.save(lignePanier);
    }

    // Supprimer une ligne de panier
    public void deleteLignePanier(int id) {
        lignePanierRepository.deleteById(id);
    }
    
 // 🔹 Récupérer le prix total d'une ligne
    public double getPrixTotalLignePanier(int id) {
        LignePanier lignePanier = lignePanierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LignePanier introuvable"));
        return lignePanier.getPrixTotal();
    }
}
