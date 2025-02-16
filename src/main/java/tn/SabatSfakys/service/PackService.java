package tn.SabatSfakys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.SabatSfakys.model.Pack;
import tn.SabatSfakys.repository.PackRepository;

@Service 

public class PackService {
	@Autowired  
	PackRepository bR;  

	public List<Pack> getAllPacks()   
	{  
		List<Pack> packs = new ArrayList<Pack>();  
		bR.findAll().forEach(c -> packs.add(c));  
		return packs;  	
	}  

	//getting a specific record by using the method findById() of CrudRepository  
	public Pack getPackById(int id)   
	{  
		return bR.findById(id).get();  
	}  



	//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Pack packs)   
	{  
		bR.save(packs);  
	} 

	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
		bR.deleteById(id);  
	} 
}


