package SmartPanel.panel.Reservation.SmartPanel.Repositories;

import SmartPanel.panel.Reservation.SmartPanel.Model.ProductSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  PanelRepository extends JpaRepository<ProductSpec,Long> {
    @Query(value = "SELECT p FROM ProductSpec p WHERE p.type LIKE %:keyword%" +
            " OR p.maxpowerOutput LIKE %:keyword%" +
            " OR p.dimensions LIKE %:keyword%" +
            " OR p.weight LIKE %:keyword%" +
            " OR p.price LIKE %:keyword%" +
            " OR p.warrant LIKE %:keyword%", nativeQuery = false)
    List<ProductSpec> findAllPanelById(@Param("keyword") String keyword);


}
