package tn.SabatSfakys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.SabatSfakys.model.Reclamation;
import tn.SabatSfakys.repository.ReclamationRepository;

@Service 

public class ReclamationService {
	@Autowired  
	ReclamationRepository bR;  

	//getting all books record by using the method findaAll() of CrudRepository  
	public List<Reclamation> getAllBlogs()   
	{  
		List<Reclamation> blogs = new ArrayList<Reclamation>();  
		bR.findAll().forEach(c -> blogs.add(c));  
		return blogs;  	
	}  

	//getting a specific record by using the method findById() of CrudRepository  
	public Reclamation getBlogById(int id)   
	{  
		return bR.findById(id).get();  
	}  



	//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Reclamation blogs)   
	{  
		bR.save(blogs);  
	} 

	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
		bR.deleteById(id);  
	} 
}


