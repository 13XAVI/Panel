package SmartPanel.panel.Reservation.SmartPanel.Service;

import SmartPanel.panel.Reservation.SmartPanel.Model.PanelRoles;
import SmartPanel.panel.Reservation.SmartPanel.Model.PanelUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
public class CustomerUserDetails implements UserDetails {
    public CustomerUserDetails(PanelUser user) {
        this.user = user;
    }

    private PanelUser user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<PanelRoles>roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for ( PanelRoles role: roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName(){
        return user.getUsername();
    }


}
