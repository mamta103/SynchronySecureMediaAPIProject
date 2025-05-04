package com.synchrony.secure.media.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

/**
 * Configuration class for setting up Spring Security.
 * This class defines the security filter chain for the application,
 * specifying how requests are authorized and authenticated.
 */
@Configuration
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application.
     *
     * - Disables CSRF protection.
     * - Disables frame options in headers to allow H2 console access.
     * - Permits unauthenticated access to the H2 console and user registration endpoints.
     * - Requires authentication for all other requests.
     * - Configures the application to use JWT for OAuth2 resource server authentication.
     *
     * @param http The {@link HttpSecurity} object used to configure security settings.
     * @return A {@link SecurityFilterChain} instance with the configured security settings.
     * @throws Exception If an error occurs while building the security filter chain.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection
                .csrf(csrf -> csrf.disable())
                // Disable frame options to allow H2 console access
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))
                // Configure authorization rules
                .authorizeHttpRequests(auth -> auth
                        // Allow unauthenticated access to specific endpoints
                        .requestMatchers("/h2-console/**", "/api/users/register").permitAll()
                        // Require authentication for all other requests
                        .anyRequest().authenticated()
                )
                // Configure JWT-based authentication for OAuth2 resource server
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }
}