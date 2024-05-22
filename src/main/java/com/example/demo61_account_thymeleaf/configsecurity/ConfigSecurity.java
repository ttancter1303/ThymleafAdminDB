package com.example.demo61_account_thymeleaf.configsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class ConfigSecurity {
    private String[] arrPath = {"/","/product","/webjars/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
// những path này sẽ được qua hết
        httpSecurity.authorizeRequests().requestMatchers(arrPath).permitAll();
        httpSecurity.authorizeRequests().requestMatchers("/admin/**").authenticated();
        // muốn vào path admin cần có role admin
            httpSecurity.authorizeRequests().requestMatchers("/admin/**").hasRole("ADMIN");
        //tất cả request đều phải đăng nhập
        httpSecurity.authorizeRequests().anyRequest().authenticated();
//        httpSecurity.authorizeRequests().anyRequest().authenticated();

        //set đường link login
        httpSecurity.formLogin();
//                .loginPage("/login")
//                .permitAll();
        return httpSecurity.build();
    }
}
