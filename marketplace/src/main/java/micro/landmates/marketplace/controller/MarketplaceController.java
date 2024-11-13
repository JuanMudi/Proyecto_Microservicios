package micro.landmates.marketplace.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import micro.landmates.marketplace.model.ServiceItem;
import micro.landmates.marketplace.service.MarketplaceService;

@RestController
@RequestMapping("")
public class MarketplaceController {

  private final MarketplaceService service;

  public MarketplaceController(MarketplaceService service) {
    this.service = service;
  }

  @GetMapping("/services")
  public ResponseEntity<List<ServiceItem>> getAllServices() {
    return ResponseEntity.ok().body(service.getAllServices());
  }

  @GetMapping("/services/category/{category}")
  public ResponseEntity<List<ServiceItem>> getServicesByCategory(@PathVariable String category) {
    return ResponseEntity.ok().body(service.getServicesByCategory(category));
  }

  @GetMapping("/services/{id}")
  public ResponseEntity<ServiceItem> getServiceById(@PathVariable String id) {
    ServiceItem serviceItem = service.getServiceById(id);
    if (serviceItem == null)
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    return ResponseEntity.ok().body(serviceItem);
  }

  @GetMapping("/services/search")
  public ResponseEntity<List<ServiceItem>> searchServices(@RequestParam String city) {
    return ResponseEntity.ok().body(service.searchServicesByCity(city));
  }
}
