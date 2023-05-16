package SmartPanel.panel.Reservation.SmartPanel.Service;

import SmartPanel.panel.Reservation.SmartPanel.Model.PanelUser;
import SmartPanel.panel.Reservation.SmartPanel.Repositories.UserRepository;
import SmartPanel.panel.Reservation.SmartPanel.Service.CustomerUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PanelUser user = repository.findByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException("user Not Found");
        }
        return new CustomerUserDetails(user);
    }
}
