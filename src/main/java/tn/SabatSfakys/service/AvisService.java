package tn.SabatSfakys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.SabatSfakys.model.Avis;
import tn.SabatSfakys.repository.AvisRepository;

@Service 

public class AvisService {
	@Autowired  
	AvisRepository bR;  

	//getting all books record by using the method findaAll() of CrudRepository  
	public List<Avis> getAllAvis()   
	{  
		List<Avis> avis = new ArrayList<Avis>();  
		bR.findAll().forEach(c -> avis.add(c));  
		return avis;  	
	}  

	//getting a specific record by using the method findById() of CrudRepository  
	public Avis getAvisById(int id)   
	{  
		return bR.findById(id).get();  
	}  

	

	//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Avis avis)   
	{  
		bR.save(avis);  
	} 

	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
		bR.deleteById(id);  
	} 
}


