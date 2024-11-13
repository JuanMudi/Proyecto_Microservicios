package micro.landmates.marketplace;

import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import micro.landmates.marketplace.model.ServiceItem;
import micro.landmates.marketplace.service.MarketplaceService;

@SpringBootTest(classes = MarketplaceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@DirtiesContext
public class MarketplaceControllerIntegrationTest {
  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private MarketplaceService marketplaceService;

  @Test
  public void testGetAllServices_Success() {
    List<ServiceItem> serviceItems = List.of(
        new ServiceItem("1", "Service One", "Description One", "Category One", 100.0, "hour", "Country One",
            "City One"),
        new ServiceItem("2", "Service Two", "Description Two", "Category Two", 200.0, "day", "Country Two",
            "City Two"));

    when(marketplaceService.getAllServices()).thenReturn(serviceItems);

    webTestClient.get()
        .uri("/services")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(ServiceItem.class)
        .isEqualTo(serviceItems);
  }

  @Test
  public void testGetServicesByCategory_Success() {
    String category = "Category One";
    List<ServiceItem> serviceItems = List.of(
        new ServiceItem("1", "Service One", "Description One", category, 100.0, "hour", "Country One", "City One"));

    when(marketplaceService.getServicesByCategory(category)).thenReturn(serviceItems);

    webTestClient.get()
        .uri("/services/category/" + category)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(ServiceItem.class)
        .isEqualTo(serviceItems);
  }

  @Test
  public void testGetServiceById_Success() {
    String serviceId = "1";
    ServiceItem serviceItem = new ServiceItem(serviceId, "Service One", "Description One", "Category One", 100.0,
        "hour", "Country One", "City One");

    when(marketplaceService.getServiceById(serviceId)).thenReturn(serviceItem);

    webTestClient.get()
        .uri("/services/" + serviceId)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(ServiceItem.class)
        .isEqualTo(serviceItem);
  }

  @Test
  public void testGetServiceById_NotFound() {
    String serviceId = "nonexistent";
    when(marketplaceService.getServiceById(serviceId)).thenReturn(null);

    webTestClient.get()
        .uri("/services/" + serviceId)
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  public void testSearchServices_Success() {
    String city = "City One";
    List<ServiceItem> serviceItems = List.of(
        new ServiceItem("1", "Service One", "Description One", "Category One", 100.0, "hour", "Country One", city));

    when(marketplaceService.searchServicesByCity(city)).thenReturn(serviceItems);

    webTestClient.get()
        .uri(uriBuilder -> uriBuilder.path("/services/search").queryParam("city", city).build())
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(ServiceItem.class)
        .isEqualTo(serviceItems);
  }
}
