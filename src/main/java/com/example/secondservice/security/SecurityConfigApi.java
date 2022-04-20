package com.example.secondservice.security;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class SecurityConfigApi extends WebSecurityConfigurerAdapter {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http
                .requestMatchers().antMatchers("/api/**")
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().cors()
                .and().oauth2ResourceServer()
                .jwt();

    }


}