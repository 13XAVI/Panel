package SmartPanel.panel.Reservation.WebConf;

import SmartPanel.panel.Reservation.SmartPanel.Service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailService();
    }
    @Bean public BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/AllPanels","/new","/edit/**","/Admin/*","/AboutUs","/Panels","/panelCost").authenticated()
                .antMatchers("/new").hasAnyAuthority("ADMIN","DISTRIBUTOR")
                .antMatchers("/delete/**").hasAnyAuthority("ADMIN")
                .antMatchers("/new/*").hasAnyAuthority("ADMIN","DISTRIBUTOR")
                .antMatchers("/Admin/*").hasAnyAuthority("ADMIN")
                .antMatchers("/edit/**").hasAnyAuthority("ADMIN","DISTRIBUTOR")
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .usernameParameter("email")
                .defaultSuccessUrl("/HomePage")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .csrf().disable();
    }


}
