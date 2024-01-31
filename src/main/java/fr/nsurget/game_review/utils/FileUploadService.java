package fr.nsurget.game_review.utils;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@AllArgsConstructor
public class FileUploadService {

    private static final String DIR_PATH = "src/main/webapp";
    private static final String PATH = "/resources/image/";

    public String uploadFile(MultipartFile file, String path, String slug) {
        try {
            File dir = new File(DIR_PATH + PATH + path);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath() + File.separator + slug + ".jpg");
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(file.getBytes());
            stream.close();
            return PATH + path + File.separator + serverFile.getName();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "Une erreur est survenue pendant le téléversement de votre image...";
        }
    }

}
