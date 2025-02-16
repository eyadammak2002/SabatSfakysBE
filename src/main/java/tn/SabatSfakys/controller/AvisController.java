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

import tn.SabatSfakys.model.Avis;
import tn.SabatSfakys.service.AvisService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class AvisController {

	@Autowired  
	AvisService as;  

	@GetMapping("/avis")
	private List<Avis> getAllAvis()   
	{  
		return as.getAllAvis();  
	}  

	@GetMapping("/avis/{id}")  
	private Avis getAvis(@PathVariable("id") int id)   
	{  
		return as.getAvisById(id);  
	}  
	
	


	@DeleteMapping("/avis/{id}")  
	private void deleteAvis(@PathVariable("id") int id)   
	{  
		as.delete(id);  
	} 

		@PostMapping("/avis")  
	private Long saveAvis(@RequestBody Avis a)   
	{  
		as.saveOrUpdate(a);  
		return a.getId();  
	} 

		@PutMapping("/avis")  
	private Avis update(@RequestBody  Avis a)   
	{  
		as.saveOrUpdate(a);  
		return a;  
	} 
	
}
