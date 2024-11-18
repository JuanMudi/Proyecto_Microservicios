package micro.landmates.marketplace;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.core.io.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import micro.landmates.marketplace.model.ServiceItem;
import micro.landmates.marketplace.service.MarketplaceService;

@SpringBootTest(classes = MarketplaceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext
@AutoConfigureWebTestClient
public class MarketplaceGraphqlIntegrationTest {

  @MockBean
  private MarketplaceService marketplaceService;

  @Autowired
  private WebTestClient webTestClient;

  @Test
  public void testGetServices_Success() throws IOException {

    Resource resource = new DefaultResourceLoader().getResource("classpath:graphql/get-services.graphql");
    Path path = Paths.get(resource.getURI());
    String query = Files.readString(path);

    query = query.replace("\n", " ").replaceAll("\\s+", " ").trim();

    List<ServiceItem> serviceItems = List.of(
        new ServiceItem("1", "Service One", "Description One", "Category One", 100.0, "hour", "Country One",
            "City One", null, null),
        new ServiceItem("2", "Service Two", "Description Two", "Category Two", 200.0, "day", "Country Two",
            "City Two", null, null));

    when(marketplaceService.getAllServices()).thenReturn(serviceItems);

    webTestClient.post()
        .uri("/graphql")
        .header("Content-Type", "application/json")
        .body(BodyInserters.fromValue("{\"query\":\"" + query + "\"}"))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.data.getServices").isNotEmpty();
  }

  @Test
  public void testGetServiceById_Success() throws IOException {
    String serviceId = "1";
    ServiceItem serviceItem = new ServiceItem(
        serviceId, "Service One", "Description One", "Category One", 100.0, "hour", "Country One", "City One", null,
        null);

    when(marketplaceService.getServiceById(serviceId)).thenReturn(serviceItem);

    String query = "query { getServiceById(id: \\\"{id}\\\") { id description category price priceUnit country city } }";
    query = query.replace("{id}", serviceId);

    webTestClient.post()
        .uri("/graphql")
        .header("Content-Type", "application/json")
        .body(BodyInserters.fromValue("{\"query\": \"" + query + "\"}"))
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.data.getServiceById").isNotEmpty()
        .jsonPath("$.data.getServiceById.id").isEqualTo("1")
        .jsonPath("$.data.getServiceById.description").isEqualTo("Description One")
        .jsonPath("$.data.getServiceById.price").isEqualTo(100.0);
  }

}
