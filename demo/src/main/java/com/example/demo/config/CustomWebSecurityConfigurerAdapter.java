package com.example.demo.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
        .withUser("admin")
        .password(passwordEncoder.encode("abc"))
        .roles("USER","ADMIN")
        .and()
        .withUser("root")
        .password(passwordEncoder.encode("example2020!@#"))
        .roles("USER")
        ;

        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
    }
}
