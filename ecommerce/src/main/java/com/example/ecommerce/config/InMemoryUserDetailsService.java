package com.example.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class InMemoryUserDetailsService {

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        var user = User.withUsername("user")
                       .password("{noop}password")
                       .roles("USER")
                       .build();
        var admin = User.withUsername("admin")
                        .password("{noop}admin123")
                        .roles("ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(user, admin);
    }
}
