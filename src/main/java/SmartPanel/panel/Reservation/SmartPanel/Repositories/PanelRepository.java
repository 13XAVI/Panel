package SmartPanel.panel.Reservation.SmartPanel.Repositories;

import SmartPanel.panel.Reservation.SmartPanel.Model.ProductSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface  PanelRepository extends PagingAndSortingRepository<ProductSpec,Long>,JpaRepository<ProductSpec,Long> {
    @Query("SELECT p FROM ProductSpec p WHERE p.type LIKE %?1%")
    public List<ProductSpec> findAllById(String Keyword);
}
