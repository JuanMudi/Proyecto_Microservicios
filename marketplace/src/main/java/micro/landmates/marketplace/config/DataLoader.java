package micro.landmates.marketplace.config;

import java.io.InputStream;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import micro.landmates.marketplace.model.ServiceItem;
import micro.landmates.marketplace.repository.ServiceItemRepository;

@Configuration
public class DataLoader {

  private final ServiceItemRepository repository;

  DataLoader(ServiceItemRepository repository) {
    this.repository = repository;
  }

  @Bean
  CommandLineRunner runner() {
    return args -> {
      ObjectMapper mapper = new ObjectMapper();
      TypeReference<List<ServiceItem>> typeReference = new TypeReference<List<ServiceItem>>() {
      };
      InputStream inputStream = TypeReference.class.getResourceAsStream("/data.json");
      List<ServiceItem> services = mapper.readValue(inputStream, typeReference);
      repository.saveAll(services);
    };
  }
}
