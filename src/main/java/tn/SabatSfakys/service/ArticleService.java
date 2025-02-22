package tn.SabatSfakys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.SabatSfakys.model.Article;
import tn.SabatSfakys.model.Article;
import tn.SabatSfakys.repository.ArticleRepository;

@Service 

public class ArticleService {
	@Autowired  
	ArticleRepository bR;  

	//getting all books record by using the method findaAll() of CrudRepository  
	public List<Article> getAllArticles()   
	{  
		List<Article> articles = new ArrayList<Article>();  
		bR.findAll().forEach(c -> articles.add(c));  
		return articles;  	
	}  

	//getting a specific record by using the method findById() of CrudRepository  
	public Article getArticleById(int id)   
	{  
		return bR.findById(id).get();  
	}  



	//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Article articles)   
	{  
		bR.save(articles);  
	} 

	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
		bR.deleteById(id);  
	} 
	
	public Article updateArticle(int id, Article updatedArticle) {
	    return bR.findById(id)
	        .map(article -> {
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
	            return (Article) bR.save(article);  // 🔹 Ajout du cast explicite
	        })
	        .orElseThrow(() -> new RuntimeException("Article non trouvé avec ID: " + id));
	}

	  
}


