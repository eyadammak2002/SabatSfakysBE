package tn.SabatSfakys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;
import tn.SabatSfakys.model.Article;
import tn.SabatSfakys.service.ArticleService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/article")
public class ArticleController {

	@Autowired  
    ArticleService articleService;

    // Récupérer tous les articles
    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    // Récupérer un article par ID
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable int id) {
        try {
            Article article = articleService.getArticleById(id);
            return ResponseEntity.ok(article);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Créer ou mettre à jour un article
    @PostMapping
    public ResponseEntity<String> createArticle(@RequestBody Article article) {
        articleService.saveOrUpdate(article);
        return ResponseEntity.status(HttpStatus.CREATED).body("Article créé avec succès");
    }

    // Mettre à jour un article existant
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable int id, @RequestBody Article updatedArticle) {
        try {
            Article article = articleService.updateArticle(id, updatedArticle);
            return ResponseEntity.ok(article);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    // Supprimer un article
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable int id) {
        try {
            articleService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Article supprimé avec succès");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la suppression du article");
        }
    }
}
