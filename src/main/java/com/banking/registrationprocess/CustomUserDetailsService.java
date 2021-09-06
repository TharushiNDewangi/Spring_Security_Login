package com.banking.registrationprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        //if user is null it gives exception and othervise we want to convert user to CustomUserDetails class
        if(user ==null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        //convert user to CustomUserDetails class
        return new CustomUserDetails(user);
    }

}