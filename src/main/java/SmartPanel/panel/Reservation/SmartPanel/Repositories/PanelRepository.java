package SmartPanel.panel.Reservation.SmartPanel.Repositories;

import SmartPanel.panel.Reservation.SmartPanel.Model.ProductSpec;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface  PanelRepository extends PagingAndSortingRepository<ProductSpec,Long>,JpaRepository<ProductSpec,Long> {
    @Query(value = "SELECT p FROM ProductSpec  WHERE p.type LIKE %?1%" +
            " OR p.maxpower_output LIKE %?1%" +
            "OR  p.dimensions LIKE %?1%" +
            "OR p.weight LIKE %?1% " +
            " OR p.price LIKE %?1%" +
            "OR p.warrant LIKE %?1%",nativeQuery = true)
    public List<ProductSpec> findAllPanelById( String Keyword);

}
