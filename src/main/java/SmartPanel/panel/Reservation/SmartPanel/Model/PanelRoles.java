package SmartPanel.panel.Reservation.SmartPanel.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PanelRoles {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
   @Column(nullable = false,unique = true)
    private String name;

   public PanelRoles(Integer id){
       super();
       this.id=id;
   }
    public PanelRoles(String name){
        super();
        this.name=name;
    }
}
