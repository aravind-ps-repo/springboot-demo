package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.demo.username:demo}")
    private String username;

    @Value("${app.demo.password:demo123}")
    private String password;

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        String encoded = passwordEncoder().encode(password);
        org.springframework.security.core.userdetails.UserDetails user =
            org.springframework.security.core.userdetails.User.withUsername(username)
                .password(encoded)
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public org.springframework.security.web.SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .authorizeRequests()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/api/**").authenticated()
            .and()
          .httpBasic();

        http.headers().frameOptions().disable();
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
