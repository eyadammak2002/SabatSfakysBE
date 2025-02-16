package tn.SabatSfakys.service;
import java.util.ArrayList;  
import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Service;

import tn.SabatSfakys.model.Category;
import tn.SabatSfakys.repository.CategoryRepository;




//defining the business logic  
@Service 

public class CategoryService {
	@Autowired  
	CategoryRepository cR;  
	 

	//getting all articles record by using the method findaAll() of CrudRepository  
	public List<Category> getAllCategorys()   
	{  
		List<Category> categorys = new ArrayList<Category>();  
		cR.findAll().forEach(c -> categorys.add(c));  
		return categorys;  	
	}  

	//getting a specific record by using the method findById() of CrudRepository  
	public Category getCategorysById(int id)   
	{  
		return cR.findById(id).get();  
	}  


	//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Category c)   
	{  
		cR.save(c);  
	} 

	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
		cR.deleteById(id);  
	} 

}





	

