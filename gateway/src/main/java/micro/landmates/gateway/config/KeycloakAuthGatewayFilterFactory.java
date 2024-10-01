package micro.landmates.gateway.config;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import micro.landmates.gateway.service.KeycloakService;

@Component
public class KeycloakAuthGatewayFilterFactory
    extends AbstractGatewayFilterFactory<KeycloakAuthGatewayFilterFactory.Config> {
  private final KeycloakService keycloakService;

  public KeycloakAuthGatewayFilterFactory(KeycloakService keycloakService) {
    super(Config.class);
    this.keycloakService = keycloakService;
  }

  @Override
  public GatewayFilter apply(Config config) {
    return (exchange, chain) -> {
      // Fetch the token dynamically for each request
      String accessToken = keycloakService.getAccessToken();
      exchange.getRequest().mutate().header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken).build();
      return chain.filter(exchange);
    };
  }

  public static class Config {
  }
}
