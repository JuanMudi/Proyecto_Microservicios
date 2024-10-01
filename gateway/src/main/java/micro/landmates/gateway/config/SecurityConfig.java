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
  @Bean
  SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
    return http
        .cors(cors -> cors.configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()))
        .csrf(csrf -> csrf.disable())
        .authorizeExchange(exchange -> exchange
            // Allow authentication methods
            .pathMatchers(HttpMethod.POST, "/keycloak-server/realms/landmates/protocol/openid-connect/token")
            .permitAll()

            // Allow user registration
            .pathMatchers(HttpMethod.POST, "/keycloak-server/admin/realms/landmates/users")
            .permitAll()

            // Requier authentication for any other endpoints
            .anyExchange().authenticated())
        .oauth2ResourceServer(
            oauth2 -> oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter())))
        .build();
  }
}
