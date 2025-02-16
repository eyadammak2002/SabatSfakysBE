package tn.SabatSfakys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.SabatSfakys.model.Admin;
import tn.SabatSfakys.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired  
	AdminRepository bR;  

	//getting all books record by using the method findaAll() of CrudRepository  
	public List<Admin> getAllAdmins()   
	{  
		List<Admin> admins = new ArrayList<Admin>();  
		bR.findAll().forEach(c -> admins.add(c));  
		return admins;  	
	}  

	//getting a specific record by using the method findById() of CrudRepository  
	public Admin getAdminById(int id)   
	{  
		return bR.findById(id).get();  
	}  



	//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Admin admins)   
	{  
		bR.save(admins);  
	} 

	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
		bR.deleteById(id);  
	} 
}
