package tn.SabatSfakys.model;



import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {
    public static String saveFile(String fileName, MultipartFile multipartFile) throws IOException {
        String uploadDir = "uploads/";

        File uploadDirFile = new File(uploadDir);
        if (!uploadDirFile.exists()) {
            uploadDirFile.mkdir();
        }

        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(multipartFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString(); // Ou utilisez un chemin relatif/URL adapté
    }
}