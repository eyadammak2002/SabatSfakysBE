
package tn.SabatSfakys.controller;

import tn.SabatSfakys.model.FileInfo;
import tn.SabatSfakys.model.Photo;
import tn.SabatSfakys.payload.UploadFileResponse;
import tn.SabatSfakys.service.FileStorageService;
import tn.SabatSfakys.service.PhotoService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
public class FileController {

	   private static final Logger logger = LoggerFactory.getLogger(FileController.class);

	    @Autowired
	    private FileStorageService fileStorageService;

	    @Autowired
	    private PhotoService photoService;

	    @PostMapping("/uploadFile")
	    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
	        // Store the file and get its URL
	        String fileDownloadUri = fileStorageService.storeFile(file);

	        // Create a new Photo entity and save it in the database
	        Photo photo = new Photo();
	        photo.setName(file.getOriginalFilename());
	        photo.setUrl(fileDownloadUri);
	        photoService.saveOrUpdate(photo);

	        return new UploadFileResponse(photo.getName(), photo.getUrl(), file.getContentType(), file.getSize());
	    }

	    @PostMapping("/uploadMultipleFiles")
	    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
	        return Arrays.asList(files)
	                .stream()
	                .map(file -> uploadFile(file))
	                .collect(Collectors.toList());
	    }

	    @GetMapping("/files")
	    public ResponseEntity<List<FileInfo>> getListFiles() {
	        List<FileInfo> fileInfos = fileStorageService.loadAll().map(path -> {
	            String filename = path.getFileName().toString();
	            String url = MvcUriComponentsBuilder
	                    .fromMethodName(FileController.class, "getFile", path.getFileName().toString()).build().toString();

	            return new FileInfo(filename, url);
	        }).collect(Collectors.toList());

	        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
	    }

	    @GetMapping("/files/{filename:.+}")
	    @ResponseBody
	    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
	        Resource file = fileStorageService.load(filename);
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
	                .body(file);
	    }


	    @GetMapping("/downloadFile/{fileName:.+}")
	    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
	        // Load file as Resource
	        Resource resource = fileStorageService.loadFileAsResource(fileName);

	        // Try to determine file's content type
	        String contentType = null;
	        try {
	            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
	        } catch (IOException ex) {
	            logger.info("Could not determine file type.");
	        }

	        // Fallback to the default content type if type could not be determined
	        if (contentType == null) {
	            contentType = "application/octet-stream";
	        }

	        String encodedFileName;
	        try {
	            // URL-encode the filename
	            encodedFileName = URLEncoder.encode(resource.getFilename(), StandardCharsets.UTF_8.toString());
	        } catch (UnsupportedEncodingException ex) {
	            // If encoding fails, log the error and use a fallback filename
	            logger.error("Failed to encode filename", ex);
	            encodedFileName = "file";
	        }

	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedFileName + "\"")
	                .body(resource);
	    }

    
    @DeleteMapping("/deleteFile/{filename:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable String filename) {
        try {
            fileStorageService.deleteFile(filename);
            return ResponseEntity.ok("File deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Could not delete the file.");
        }
    }

}