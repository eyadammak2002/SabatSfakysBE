package tn.SabatSfakys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.SabatSfakys.model.Client;
import tn.SabatSfakys.repository.ClientRepository;

@Service
public class ClientService {

	@Autowired  
	ClientRepository bR;  

	//getting all books record by using the method findaAll() of CrudRepository  
	public List<Client> getAllClients()   
	{  
		List<Client> clients = new ArrayList<Client>();  
		bR.findAll().forEach(c -> clients.add(c));  
		return clients;  	
	}  

	//getting a specific record by using the method findById() of CrudRepository  
	public Client getClientById(int id)   
	{  
		return bR.findById(id).get();  
	}  



	//saving a specific record by using the method save() of CrudRepository  
	public void saveOrUpdate(Client clients)   
	{  
		bR.save(clients);  
	} 

	//deleting a specific record by using the method deleteById() of CrudRepository  
	public void delete(int id)   
	{  
		bR.deleteById(id);  
	} 
}
