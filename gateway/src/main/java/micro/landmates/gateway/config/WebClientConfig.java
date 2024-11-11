package micro.landmates.gateway.config;

import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
  @Bean
  public WebClient.Builder loadBalancedWebClientBuilder(
      LoadBalancedExchangeFilterFunction loadBalancedExchangeFilterFunction) {
    return WebClient.builder().filter(loadBalancedExchangeFilterFunction);
  }

  @Bean
  public WebClient.Builder webClientBuilder() {
    return WebClient.builder();
  }
}
