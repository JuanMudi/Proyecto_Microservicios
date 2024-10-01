package micro.landmates.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder,
      KeycloakAuthGatewayFilterFactory keycloakAuthFilter) {
    return builder.routes()
        // Specific route for user registration (with token filter)
        .route("keycloak_user_registration_route", r -> r
            .path("/keycloak-server/admin/realms/landmates/users")
            .filters(f -> f
                .rewritePath("/keycloak-server/(?<segment>.*)", "/${segment}")
                .filter(keycloakAuthFilter.apply(new KeycloakAuthGatewayFilterFactory.Config())))
            .uri("http://localhost:9000"))

        // General route without token filter for all other Keycloak paths
        .route("keycloak_general_route", r -> r
            .path("/keycloak-server/**")
            .filters(f -> f.rewritePath("/keycloak-server/(?<segment>.*)", "/${segment}"))
            .uri("http://localhost:9000"))
        .build();
  }
}
