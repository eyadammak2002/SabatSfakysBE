package tn.SabatSfakys.service;

import tn.SabatSfakys.exception.FileStorageException;
import tn.SabatSfakys.exception.MyFileNotFoundException;
import tn.SabatSfakys.model.Photo;
import tn.SabatSfakys.property.FileStorageProperties;
import tn.SabatSfakys.repository.PhotoRepository;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import ch.qos.logback.classic.Logger;
import jakarta.transaction.Transactional;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileStorageService {
    private static final Logger logger = (Logger) LoggerFactory.getLogger(FileStorageService.class);

    private final Path fileStorageLocation;
    @Autowired
    private PhotoRepository photoRepository; // Inject your Photo repository here

    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }
    

    public String storeFile(MultipartFile file) {
        // Normalize the file name by replacing spaces and special characters
        //String fileName = StringUtils.cleanPath(file.getOriginalFilename()).replaceAll("[^a-zA-Z0-9\\.\\-()é'àè&@ ]", "_");
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        
        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            // Return the normalized URL of the stored file without encoding
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.fileStorageLocation, 1)
                    .filter(path -> !path.equals(this.fileStorageLocation))
                    .map(this.fileStorageLocation::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!", e);
        }
    }

    public Resource load(String filename) {
        try {
            Path file = this.fileStorageLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage(), e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
    

    @Transactional
    public void deleteFile(String fileName) {
        try {
            // Delete file from the file system
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Files.delete(filePath);
            logger.info("File deleted from the file system: {}", fileName);

            // Find photo by name and get its ID
            Photo photo = photoRepository.findByName(fileName)
                    .orElseThrow(() -> new RuntimeException("Photo not found with name " + fileName));
            logger.info("id = : {}", photo.getId());

            // Delete file record from the database using ID
            photoRepository.deleteById(photo.getId());
            logger.info("File record deleted from the database: {}", fileName);
        } catch (IOException ex) {
            logger.error("Could not delete the file " + fileName, ex);
            throw new RuntimeException("Could not delete the file " + fileName, ex);
        } catch (Exception e) {
            logger.error("Error occurred while deleting the photo record: {}", e.getMessage());
            throw new RuntimeException("Could not delete the photo record with name " + fileName, e);
        }
    }


}
