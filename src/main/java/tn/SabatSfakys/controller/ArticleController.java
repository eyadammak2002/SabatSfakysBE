package tn.SabatSfakys.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.SabatSfakys.model.Article;
import tn.SabatSfakys.model.Article;
import tn.SabatSfakys.service.ArticleService;
@CrossOrigin(origins = "*")
@RestController

public class ArticleController {

	@Autowired  
	ArticleService as;  

	//creating a get mapping that retrieves all the Article detail from the database   
	@GetMapping("/article")
	private List<Article> getAllArticles()   
	{  
		return as.getAllArticles();  
	}  

	//creating a get mapping that retrieves the detail of a specific article  
	@GetMapping("/article/{id}")  
	private Article getArticle(@PathVariable("id") int id)   
	{  
		return as.getArticleById(id);  
	}  
	
	


	//creating a delete mapping that deletes a specified article  
	@DeleteMapping("/article/{id}")  
	private void deleteArticle(@PathVariable("id") int id)   
	{  
		as.delete(id);  
	} 

	//create new article
	@PostMapping("/article")  
	private int saveArticle(@RequestBody Article a)   
	{  
		as.saveOrUpdate(a);  
		return a.getId();  
	} 


	//creating put mapping that updates the article detail
	@PutMapping("/article/{id}")  
	public Article update(@PathVariable int id, @RequestBody Article updatedArticle) {  
	    return as.updateArticle(id, updatedArticle);
	}

}
