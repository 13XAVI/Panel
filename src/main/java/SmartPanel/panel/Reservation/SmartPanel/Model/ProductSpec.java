package SmartPanel.panel.Reservation.SmartPanel.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bestPanel")
public class ProductSpec {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String maxpowerOutput;
    private String dimensions;
    private String weight;
    private String price;
    private String warrant;
}
