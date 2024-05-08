package com.example.demo61_account_thymeleaf.configsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();

//        httpSecurity.authorizeRequests().requestMatchers("/").permitAll();
        httpSecurity.authorizeRequests().requestMatchers("/admin/**").authenticated();
//        httpSecurity.authorizeRequests().anyRequest().authenticated();

        httpSecurity.formLogin();
        return httpSecurity.build();
    }
}
