package tn.SabatSfakys.controller;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import tn.SabatSfakys.model.FileUploadUtil;
import tn.SabatSfakys.model.Photo;
import tn.SabatSfakys.repository.PhotoRepository;
import tn.SabatSfakys.service.FileStorageService;
import tn.SabatSfakys.service.PhotoService;

@RestController 
@CrossOrigin(origins = "*")


public class PhotoController {

	@Autowired  
	PhotoService as;  
	
	@Autowired  
	PhotoRepository photoRepository; 
	
	
	
	
	
	    public PhotoController(PhotoService as, FileStorageService fileStorageService) {
	        this.as = as;
	    }
	    
	//creating a get mapping that retrieves all the Photo detail from the database   
	@GetMapping("/photo")
	private List<Photo> getAllPhotos()   
	{  
		return as.getAllPhotos();  
	}  

	//creating a get mapping that retrieves the detail of a specific photo  
	@GetMapping("/photo/{id}")  
	private Photo getPhoto(@PathVariable("id") int id)   
	{  
		return as.getPhotoById(id);  
	}  

 

	//create new photo
	@PostMapping("/photos")  
	private int savePhoto(@RequestBody Photo a)   
	{  
		as.saveOrUpdate(a);  
		return a.getId();  
	} 

	//creating put mapping that updates the photo detail
	@PutMapping("/photo")  
	private Photo update(@RequestBody  Photo a)   
	{  
		as.saveOrUpdate(a);  
		return a;  
	} 
	
    // Endpoint for uploading a file
	@PostMapping("/upload")
	public ResponseEntity<Map<String, String>> uploadPhoto(@RequestParam("file") MultipartFile file) throws IOException {
	    if (file.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "No file uploaded"));
	    }

	    // Save the file to the server and get the URL
	    String fileName = file.getOriginalFilename();
	    String filePath = FileUploadUtil.saveFile(fileName, file);

	    Map<String, String> response = new HashMap<>();
	    response.put("url", filePath);
	    response.put("name", fileName);

	    return ResponseEntity.ok(response);
	}

	@PostMapping("/uploadMultiple")
	public ResponseEntity<List<Map<String, String>>> uploadMultiplePhotos(@RequestParam("files") MultipartFile[] files) throws IOException {
	    List<Map<String, String>> responses = new ArrayList<>();

	    for (MultipartFile file : files) {
	        if (!file.isEmpty()) {
	            String fileName = file.getOriginalFilename();
	            String filePath = FileUploadUtil.saveFile(fileName, file);
	            Map<String, String> response = new HashMap<>();
	            response.put("url", filePath);
	            response.put("name", fileName);
	            responses.add(response);
	        }
	    }

	    return ResponseEntity.ok(responses);
	}
	

    // Endpoint for creating a photo
    @PostMapping("/photo")
    public ResponseEntity<Photo> createPhoto(@RequestBody Photo photo) {
        Photo savedPhoto = as.saveOrUpdate(photo);
        return ResponseEntity.ok(savedPhoto);
    }
    
    @PostMapping("/photo/multiple")
    public ResponseEntity<List<Photo>> createMultiplePhotos(@RequestBody List<Photo> photos) {
        List<Photo> savedPhotos = new ArrayList<>();
        for (Photo photo : photos) {
            savedPhotos.add(as.saveOrUpdate(photo));
        }
        return ResponseEntity.ok(savedPhotos);
    }


  }