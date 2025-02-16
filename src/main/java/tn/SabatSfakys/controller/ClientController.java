package tn.SabatSfakys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import tn.SabatSfakys.model.Client;
import tn.SabatSfakys.service.ClientService;

@RestController
@CrossOrigin(origins = "*")
public class ClientController {

	@Autowired  
	ClientService as;  

	//creating a get mapping that retrieves all the Client detail from the database   
	@GetMapping("/client")
	private List<Client> getAllClients()   
	{  
		return as.getAllClients();  
	}  

	//creating a get mapping that retrieves the detail of a specific client  
	@GetMapping("/client/{id}")  
	private Client getClient(@PathVariable("id") int id)   
	{  
		return as.getClientById(id);  
	}  
	
	


	//creating a delete mapping that deletes a specified client  
	@DeleteMapping("/client/{id}")  
	private void deleteClient(@PathVariable("id") int id)   
	{  
		as.delete(id);  
	} 

	//create new client
	@PostMapping("/client")  
	private Long saveClient(@RequestBody Client a)   
	{  
		as.saveOrUpdate(a);  
		return a.getId();  
	} 

	//creating put mapping that updates the client detail
	@PutMapping("/client")  
	private Client update(@RequestBody  Client a)   
	{  
		as.saveOrUpdate(a);  
		return a;  
	} 
}
