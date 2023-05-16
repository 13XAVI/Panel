package SmartPanel.panel.Reservation.SmartPanel.Repositories;

import SmartPanel.panel.Reservation.SmartPanel.Model.Contactus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactusRepository extends JpaRepository<Contactus,Long> {

}
