package SmartPanel.panel.Reservation.SmartPanel.Model;




import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PanelUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 50,unique = true)
    private String username;
    @Column(nullable = false,length = 50)
    private String email;
    @Column(nullable = false,length = 50)
    private String password;

    public Set<PanelRoles> getRoles() {
        return roles;
    }

    public void setRoles(Set<PanelRoles> roles) {
        this.roles = roles;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "panel_user_roles",
            joinColumns = @JoinColumn(name = "panel_user_id"),
            inverseJoinColumns  = @JoinColumn(name = "panel_roles_id")
    )
    private Set<PanelRoles> roles = new HashSet<>();

    public void addRole(PanelRoles role) {
        this.roles.add(role);
    }
}
