package micro.landmates.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class SwaggerRouteConfig {

  @Bean
  public RouterFunction<ServerResponse> usersServiceRoute(WebClient.Builder loadBalancedWebClientBuilder) {
    WebClient webClient = loadBalancedWebClientBuilder.baseUrl("lb://users-microservice").build();

    return RouterFunctions.route()
        .GET("/aggregate/users/v3/api-docs", request -> webClient.get()
            .uri("/api-docs")
            .retrieve()
            .toEntity(String.class)
            .flatMap(responseEntity -> {
              String body = responseEntity.getBody();
              if (body == null) {
                return ServerResponse.status(responseEntity.getStatusCode())
                    .bodyValue("No content available");
              }
              return ServerResponse.status(responseEntity.getStatusCode())
                  .headers(headers -> headers.addAll(responseEntity.getHeaders()))
                  .bodyValue(body);
            }))
        .build();
  }

  @Bean
  public RouterFunction<ServerResponse> marketplaceServiceRoute(WebClient.Builder loadBalancedWebClientBuilder) {
    WebClient webClient = loadBalancedWebClientBuilder.baseUrl("lb://marketplace").build();

    return RouterFunctions.route()
        .GET("/aggregate/marketplace/v3/api-docs", request -> webClient.get()
            .uri("/api-docs")
            .retrieve()
            .toEntity(String.class)
            .flatMap(responseEntity -> {
              String body = responseEntity.getBody();
              if (body == null) {
                return ServerResponse.status(responseEntity.getStatusCode())
                    .bodyValue("No content available");
              }
              return ServerResponse.status(responseEntity.getStatusCode())
                  .headers(headers -> headers.addAll(responseEntity.getHeaders()))
                  .bodyValue(body);
            }))
        .build();
  }
}
