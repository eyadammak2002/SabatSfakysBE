package tn.SabatSfakys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.SabatSfakys.model.Fournisseur;
import tn.SabatSfakys.repository.FournisseurRepository;

@Service 

public class FournisseurService {
	@Autowired  
	FournisseurRepository bR;  

	//getting all books record by using the method findaAll() of CrudRepository  
	public List<Fournisseur> getAllFournisseurs()   
	{  
		List<Fournisseur> fournisseurs = new ArrayList<Fournisseur>();  
		bR.findAll().forEach(c -> fournisseurs.add(c));  
		return fournisseurs;  	
	}  

	//getting a specific record by using the method findById() of CrudRepository  
	public Fournisseur getFournisseurById(int id)   
	{  
		return bR.findById(id).get();  
	}  



	//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Fournisseur fournisseurs)   
	{  
		bR.save(fournisseurs);  
	} 

	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
		bR.deleteById(id);  
	} 
}


