package SmartPanel.panel.Reservation.SmartPanel.Service;

import SmartPanel.panel.Reservation.SmartPanel.Model.ProductSpec;
import SmartPanel.panel.Reservation.SmartPanel.Repositories.PanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanelService {
    @Autowired
    private PanelRepository panelRepo;
    public Page<ProductSpec> listAll(int pageNumber){

        Sort sort = Sort.by("type").ascending();

        Pageable pageable = PageRequest.of(pageNumber-1,7,sort);
        return panelRepo.findAll(pageable);
    }
    public List<ProductSpec> list(String keyword){
        if(keyword != null){
            return panelRepo.findAllPanelById(keyword);
        }
        return panelRepo.findAll();
    }

    public ProductSpec save(ProductSpec productSpec){
        panelRepo.save(productSpec);
        return productSpec;
    }
    public ProductSpec get(Long id){
        return  panelRepo.findById(id).get();
    }
    public void delete(Long id){
        panelRepo.deleteById(id);
    }

}
