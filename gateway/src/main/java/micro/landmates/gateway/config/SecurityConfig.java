package micro.landmates.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
        private final String[] freeResourceUrls = { "/swagger-ui.html", "/webjars/**", "/swagger-ui/**",
                        "/v3/api-docs/**", "/api-docs/**",
                        "/swagger-resources/**", "/aggregate/**" };

        @Bean
        SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
                return http
                                .cors(cors -> cors.configurationSource(
                                                request -> new CorsConfiguration().applyPermitDefaultValues()))
                                .csrf(csrf -> csrf.disable())
                                .authorizeExchange(exchange -> exchange
                                                // Allow access to login and registration endpoints
                                                .pathMatchers(HttpMethod.POST, "/auth/login").permitAll()
                                                .pathMatchers(HttpMethod.POST, "/auth/register").permitAll()

                                                // Allow access to free resource URLs
                                                .pathMatchers(freeResourceUrls).permitAll()

                                                // Allow access to Actuator endpoints
                                                .pathMatchers(HttpMethod.GET, "/actuator/**").permitAll()

                                                // Allow access to /marketplace/services and /marketplace/services/**
                                                // for GET requests
                                                .pathMatchers(HttpMethod.GET, "/marketplace/services").permitAll()
                                                .pathMatchers(HttpMethod.GET, "/marketplace/services/**").permitAll()

                                                // Any other request should be authenticated
                                                .anyExchange().authenticated())
                                .oauth2ResourceServer(oauth2 -> oauth2
                                                .jwt(jwt -> jwt.jwtAuthenticationConverter(
                                                                new KeycloakJwtAuthenticationConverter())))
                                .build();
        }
}
