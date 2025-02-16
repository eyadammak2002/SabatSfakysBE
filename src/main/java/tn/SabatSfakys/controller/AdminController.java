package tn.SabatSfakys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import tn.SabatSfakys.model.Admin;
import tn.SabatSfakys.service.AdminService;

@RestController
@CrossOrigin(origins = "*")

public class AdminController {

    @Autowired
    AdminService as;
  

	//creating a get mapping that retrieves all the Admin detail from the database   
	@GetMapping("/admin")
	private List<Admin> getAllAdmins()   
	{  
		return as.getAllAdmins();  
	}  

	//creating a get mapping that retrieves the detail of a specific admin  
	@GetMapping("/admin/{id}")  
	private Admin getAdmin(@PathVariable("id") int id)   
	{  
		return as.getAdminById(id);  
	}  
	
	


	//creating a delete mapping that deletes a specified admin  
	@DeleteMapping("/admin/{id}")  
	private void deleteAdmin(@PathVariable("id") int id)   
	{  
		as.delete(id);  
	} 

	//create new admin
	@PostMapping("/admin")  
	private int saveAdmin(@RequestBody Admin a)   
	{  
		as.saveOrUpdate(a);  
		return a.getId();  
	} 

	//creating put mapping that updates the admin detail
	@PutMapping("/admin")  
	private Admin update(@RequestBody  Admin a)   
	{  
		as.saveOrUpdate(a);  
		return a;  
	} 
}
