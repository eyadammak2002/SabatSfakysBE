package tn.SabatSfakys.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.SabatSfakys.model.Photo;
import tn.SabatSfakys.repository.PhotoRepository;

@Service 

public class PhotoService {
	@Autowired  
	PhotoRepository iM;

	//getting all Photos record by using the method findaAll() of CrudRepository  
	public List<Photo> getAllPhotos()   
	{  
		List<Photo> photos = new ArrayList<Photo>();  
		iM.findAll().forEach(i -> photos.add(i));  
		return photos;  	
	}  

	//getting a specific record by using the method findById() of CrudRepository  
	public Photo getPhotoById(int id)   
	{  
		return iM.findById(id).get();  
	}  


	//saving a specific record by using the method save() of CrudRepository  
	 public Photo saveOrUpdate(Photo photo) {
	        return iM.save(photo);
	    }

	 
	
}
