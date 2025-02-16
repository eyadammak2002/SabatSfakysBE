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

import tn.SabatSfakys.model.Fournisseur;

import tn.SabatSfakys.service.FournisseurService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class FournisseurController {

	@Autowired  
	FournisseurService as;  

	//creating a get mapping that retrieves all the Fournisseur detail from the database   
	@GetMapping("/fournisseur")
	private List<Fournisseur> getAllFournisseurs()   
	{  
		return as.getAllFournisseurs();  
	}  

	//creating a get mapping that retrieves the detail of a specific fournisseur  
	@GetMapping("/fournisseur/{id}")  
	private Fournisseur getFournisseur(@PathVariable("id") int id)   
	{  
		return as.getFournisseurById(id);  
	}  
	
	


	//creating a delete mapping that deletes a specified fournisseur  
	@DeleteMapping("/fournisseur/{id}")  
	private void deleteFournisseur(@PathVariable("id") int id)   
	{  
		as.delete(id);  
	} 

	//create new fournisseur
	@PostMapping("/fournisseur")  
	private Long saveFournisseur(@RequestBody Fournisseur a)   
	{  
		as.saveOrUpdate(a);  
		return a.getId();  
	} 

	//creating put mapping that updates the fournisseur detail
	@PutMapping("/fournisseur")  
	private Fournisseur update(@RequestBody  Fournisseur a)   
	{  
		as.saveOrUpdate(a);  
		return a;  
	} 
	
}
