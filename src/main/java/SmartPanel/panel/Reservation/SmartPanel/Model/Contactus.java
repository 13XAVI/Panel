package SmartPanel.panel.Reservation.SmartPanel.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Contactus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false )
    private String first_name;
    private String last_name;
    @Column(nullable = false,unique = true)
    private String email;
    private String comments;
}
