package com.banking.registrationprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    //UserDetailsService object
    @Autowired
    private UserDetailsService userDetailsService;

    //create authentication provider and inside authentication provider
    //we have to use Doa authentication provider which we can get the data from
    //the database it self and proceed with authentication
    //DAO = Data Access Object(data database eken gnna nisa)
    //in DaoAuthenticationProvider() we have to give user detail service(UserDetailsService)
    //UserDetailsService is default interface to spring security it helps to
    //do authentication it self
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider
                = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        //to set pw encorder (kmati ekak pluwn) https://bcrypt-generator.com/
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return  provider;
    }

    //hasAuthority("USER") means we can give only one Aythority
    //hasAnyAuthority menas it provide multiple authority
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //for home page
                .antMatchers("/")
                //permitAll() not ask any password
                .permitAll()
                // "/home" ynna authority tienne userslta
                .antMatchers("/home")
                .hasAuthority("USER")
                // "/admin" ynna authority tienne adminslta witarai
                .antMatchers("/admin")
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
