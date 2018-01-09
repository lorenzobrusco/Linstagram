package it.unical.linstagram.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import it.unical.linstagram.helper.CustomPasswordEncoder;

@Configuration
@EnableWebSecurity
public class LoginSecurityConfig extends WebSecurityConfigurerAdapter {

	
    @Autowired
    @Qualifier("customUserDetailsService")
    UserDetailsService userDetailsService;
    
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationMgr) throws Exception {
    	authenticationMgr.userDetailsService(userDetailsService).passwordEncoder(new CustomPasswordEncoder() );
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/index").access("hasRole('ROLE_USER')")
		.and()
		.formLogin().loginPage("/Listagram/").loginProcessingUrl("/Listagram/login").permitAll()
		.usernameParameter("username").passwordParameter("password")
		.defaultSuccessUrl("/Listagram/index")
		.failureUrl("/")				
		.and()
		.logout().logoutUrl("/logout").permitAll(); 

	}
}