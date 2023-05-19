package SmartPanel.panel.Reservation.SmartPanel.Repositories;

import SmartPanel.panel.Reservation.SmartPanel.Model.fileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<fileUpload,Long> {
}
