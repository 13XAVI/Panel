package SmartPanel.panel.Reservation.fileUtil;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
public class uploadUtil {
    public static  String saveFile(String fileName, MultipartFile multipartFile) throws IOException {

        String filecode= RandomStringUtils.randomAlphanumeric(8);
        Path uploadDir = Paths.get("File-Upload");
        try(InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadDir.resolve(filecode+"-"+fileName);
            Files.copy(inputStream,filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioException) {
            throw new IOException("Error while saving"+fileName, ioException);
        }
        return  filecode;
    }
}
