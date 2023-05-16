package SmartPanel.panel.Reservation.SmartPanel.Repositories;



import SmartPanel.panel.Reservation.SmartPanel.Model.PanelUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository <PanelUser,Long>, CrudRepository<PanelUser,Long > {
    @Query("SELECT u FROM PanelUser u WHERE u.username = :username")
    PanelUser findByUsername(@Param("username") String username);
}
