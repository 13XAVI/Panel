package SmartPanel.panel.Reservation.SmartPanel.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestPart;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class fileUpload {
    private String filename;
    private String downloadUri;
    private long size;

}