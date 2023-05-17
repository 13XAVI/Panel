package SmartPanel.panel.Reservation.SmartPanel.Repositories;

import SmartPanel.panel.Reservation.SmartPanel.Model.PanelRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<PanelRoles,Integer> {

    Optional<PanelRoles> findById(Long roleId);
}
