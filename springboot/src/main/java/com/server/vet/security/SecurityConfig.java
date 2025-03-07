package com.server.vet.security;

import jakarta.ws.rs.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtClaimsConverter jwtAuthConverter;

    public SecurityConfig(JwtClaimsConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/keycloak/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/owner").permitAll() // Allow public access to the POST /api/owner
                .requestMatchers("/api/owner/**").authenticated() // Αν θέλεις να αφήσεις κάποια endpoints ελεύθερα
                .anyRequest().authenticated()
        );
        http.oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter))
        );
        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
