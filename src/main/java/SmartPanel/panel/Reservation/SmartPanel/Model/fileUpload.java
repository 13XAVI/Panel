package SmartPanel.panel.Reservation.SmartPanel.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestPart;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class fileUpload {
    private String filename;
    private String downloadUri;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long size;

}