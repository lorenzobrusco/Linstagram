package it.unical.linstagram.services;

import java.util.ArrayList;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

 
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService{
 
    @Autowired
    private UserService userService;
    
     
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        it.unical.linstagram.model.User user = userService.getUser(username);
        System.out.println("User : "+user);
        if(user==null){
            System.out.println("User not found");
            throw new UsernameNotFoundException("Username not found");
        }
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
            		true, true, true, true, getGrantedAuthorities(user));
    }
 
     
    private List<GrantedAuthority> getGrantedAuthorities(it.unical.linstagram.model.User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }
     
}