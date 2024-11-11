package micro.landmates.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
  @Bean
  public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("user_microservice_route", r -> r
            .path("/users/**")
            .filters(f -> f
                .removeRequestHeader("Cookie")
                .rewritePath("/users/(?<segment>.*)", "/${segment}")
                .filter((exchange, chain) -> {
                  String authorizationHeader = exchange.getRequest().getHeaders().getFirst("Authorization");
                  if (authorizationHeader != null) {
                    exchange = exchange.mutate()
                        .request(req -> req.header("Authorization", authorizationHeader))
                        .build();
                  }
                  return chain.filter(exchange);
                }))
            .uri("lb://users-microservice"))
        .build();
  }
}
