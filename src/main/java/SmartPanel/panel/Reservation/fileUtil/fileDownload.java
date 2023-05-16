package SmartPanel.panel.Reservation.fileUtil;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class fileDownload {
    private Path foundFile;
 public Resource getFileResource(String filecode) throws IOException {

     Path uploadDir = Paths.get("File-Upload");
     Files.list(uploadDir).forEach(file ->{
         if(file.getFileName().toString().startsWith(filecode)){
            foundFile = file;
            return;
         }
     });
     if(foundFile!=null){
         return new UrlResource(foundFile.toUri());

     }
     return null;
 }
}
