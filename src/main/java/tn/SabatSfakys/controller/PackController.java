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

import tn.SabatSfakys.model.Pack;

import tn.SabatSfakys.service.PackService;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

public class PackController {

	@Autowired  
	PackService as;  

	//creating a get mapping that retrieves all the Pack detail from the database   
	@GetMapping("/pack")
	private List<Pack> getAllPacks()   
	{  
		return as.getAllPacks();  
	}  

	//creating a get mapping that retrieves the detail of a specific pack  
	@GetMapping("/pack/{id}")  
	private Pack getPack(@PathVariable("id") int id)   
	{  
		return as.getPackById(id);  
	}  
	
	


	//creating a delete mapping that deletes a specified pack  
	@DeleteMapping("/pack/{id}")  
	private void deletePack(@PathVariable("id") int id)   
	{  
		as.delete(id);  
	} 

	//create new pack
	@PostMapping("/pack")  
	private int savePack(@RequestBody Pack a)   
	{  
		as.saveOrUpdate(a);  
		return a.getId();  
	} 

	//creating put mapping that updates the pack detail
	@PutMapping("/pack")  
	private Pack update(@RequestBody  Pack a)   
	{  
		as.saveOrUpdate(a);  
		return a;  
	} 
	
}
