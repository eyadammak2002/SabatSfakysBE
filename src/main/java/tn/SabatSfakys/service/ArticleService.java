
	package tn.SabatSfakys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import tn.SabatSfakys.model.Article;
import tn.SabatSfakys.model.Photo;
import tn.SabatSfakys.repository.ArticleRepository;
import tn.SabatSfakys.repository.PhotoRepository;

@Service 

public class ArticleService {
	@Autowired
	private ArticleRepository articleRepository;  // Repository pour Article

	@Autowired
	private PhotoRepository photoRepository;  // Repository pour Photo

	    
	    @Autowired  
	    EmailService emailService;  // Le service pour l'envoi des emails

	    // Récupère tous les articles
	    public List<Article> getAllArticles() {
	        List<Article> articles = new ArrayList<>();
	        articleRepository.findAll().forEach(article -> articles.add(article));
	        return articles;
	    }

	    // Récupère un article spécifique par ID
	    public Article getArticleById(int id) {
	        return articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article non trouvé avec ID: " + id));
	    }

	    public void saveOrUpdate(Article article) {  
	        // Vérifie si l'article contient des photos
	        if (article.getPhotos() != null) {
	            List<Photo> managedPhotos = new ArrayList<>();
	            for (Photo photo : article.getPhotos()) {
	                // Récupérer la photo existante au lieu de la persister directement
	                Photo existingPhoto = photoRepository.findById(photo.getId()).orElseThrow(
	                    () -> new RuntimeException("Photo avec ID " + photo.getId() + " non trouvée")
	                );
	                managedPhotos.add(existingPhoto);
	            }
	            article.setPhotos(managedPhotos);
	        }
	        
	        articleRepository.save(article);  
	    }


	    // Mise à jour d'un article
	    public Article updateArticle(int id, Article updatedArticle) throws MessagingException {
	        Article articleSaved = articleRepository.findById(id).map(article -> {
	            // Mise à jour des données
	        	  article.setRef(updatedArticle.getRef());
		            article.setName(updatedArticle.getName());
		            article.setDescription(updatedArticle.getDescription());
		            article.setQte(updatedArticle.getQte());
		            article.setPrixFournisseur(updatedArticle.getPrixFournisseur());
		            article.setPrixVente(updatedArticle.getPrixVente());
		            article.setCouleur(updatedArticle.getCouleur());
		            article.setGenre(updatedArticle.getGenre());
		            article.setTissu(updatedArticle.getTissu());
		            article.setStatut(updatedArticle.getStatut());
		            article.setCategory(updatedArticle.getCategory());
		            article.setPhotos(updatedArticle.getPhotos());
	            return articleRepository.save(article);  // Sauvegarde le article mis à jour
	        }).orElseThrow(() -> new RuntimeException("Article non trouvé avec ID: " + id));

	        // Envoi de l'email après mise à jour
	        //emailService.sendAuthenticationEmail(articleSaved.getEmail(), articleSaved.getNom());
	        /*String statut = articleSaved.getStatut().name();
	        if ("ACCEPTE".equalsIgnoreCase(statut)) {
	            emailService.sendAuthenticationEmail(articleSaved.getEmail(), articleSaved.getNom());
	        } else if ("REFUSE".equalsIgnoreCase(statut)) {
	            emailService.sendRefusedAuthenticationEmail(articleSaved.getEmail(), articleSaved.getNom());
	        } else {
	            System.out.println("Statut non reconnu : '" + statut + "'");
	        }*/
	        return articleSaved;
	    }
	    

	    // Suppression d'un article
	    public void delete(int id) {
	        articleRepository.deleteById(id);
	    }
	}

	

	  

