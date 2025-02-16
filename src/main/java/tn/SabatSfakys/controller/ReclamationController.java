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

import tn.SabatSfakys.model.Reclamation;
import tn.SabatSfakys.service.ReclamationService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class ReclamationController {

	@Autowired  
	ReclamationService as;  

	//creating a get mapping that retrieves all the Blog detail from the database   
	@GetMapping("/blog")
	private List<Reclamation> getAllBlogs()   
	{  
		return as.getAllBlogs();  
	}  

	//creating a get mapping that retrieves the detail of a specific blog  
	@GetMapping("/blog/{id}")  
	private Reclamation getBlog(@PathVariable("id") int id)   
	{  
		return as.getBlogById(id);  
	}  
	

	

	//creating a delete mapping that deletes a specified blog  
	@DeleteMapping("/blog/{id}")  
	private void deleteBlog(@PathVariable("id") int id)   
	{  
		as.delete(id);  
	} 

	//create new blog
	@PostMapping("/blog")  
	private Long saveBlog(@RequestBody Reclamation a)   
	{  
		as.saveOrUpdate(a);  
		return a.getId();  
	} 

	//creating put mapping that updates the blog detail
	@PutMapping("/blog")  
	private Reclamation update(@RequestBody  Reclamation a)   
	{  
		as.saveOrUpdate(a);  
		return a;  
	} 
	
}
