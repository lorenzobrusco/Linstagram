package it.unical.linstagram.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
		.antMatchers("/signUpAttempt","/forgotPassword","/forgotPasswordPage").permitAll()
		.antMatchers("/login","/resources/**").permitAll()
		.antMatchers("/**").access("hasRole('ROLE_USER')")
        .and()
        .formLogin()//enable form based authentication
        .loginPage("/login")//use a custom login URI
        .permitAll(true)
		.defaultSuccessUrl("/setUserSession",true)
        .and()
        .logout()//default logout handling
        .logoutSuccessUrl("/login?logout")//our new logout success url, we are not replacing other defaults.
        .permitAll().and().csrf().disable();//allow all as it will be accessed when user is not logged in anymore
	}
}