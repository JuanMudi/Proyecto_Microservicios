package micro.landmates.marketplace;

import micro.landmates.marketplace.model.ServiceItem;
import micro.landmates.marketplace.service.MarketplaceService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;

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
        String ownerId = UUID.randomUUID().toString();
        List<ServiceItem> serviceItems = List.of(
                new ServiceItem("1", ownerId, "Service One", "Description One", "Category One", 100.0, "hour", "Country One",
                        "City One", null, null),
                new ServiceItem("2", ownerId, "Service Two", "Description Two", "Category Two", 200.0, "day", "Country Two",
                        "City Two", null, null));

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
        String ownerId = UUID.randomUUID().toString();
        List<ServiceItem> serviceItems = List.of(
                new ServiceItem("1", ownerId, "Service One", "Description One", category, 100.0, "hour", "Country One",
                        "City One", null, null));

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
        String ownerId = UUID.randomUUID().toString();
        ServiceItem serviceItem = new ServiceItem(serviceId, ownerId, "Service One", "Description One", "Category One", 100.0,
                "hour", "Country One", "City One", null, null);

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
        String ownerId = UUID.randomUUID().toString();
        List<ServiceItem> serviceItems = List.of(
                new ServiceItem("1", "Service One", ownerId, "Description One", "Category One", 100.0, "hour", "Country One",
                        city, null, null));

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
