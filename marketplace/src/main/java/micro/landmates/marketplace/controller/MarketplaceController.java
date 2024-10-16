package micro.landmates.marketplace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private MarketplaceService service;

  @GetMapping("/services")
  public List<ServiceItem> getAllServices() {
    return service.getAllServices();
  }

  @GetMapping("/services/category/{category}")
  public List<ServiceItem> getServicesByCategory(@PathVariable String category) {
    return service.getServicesByCategory(category);
  }

  @GetMapping("/services/{id}")
  public ServiceItem getServiceById(@PathVariable String id) {
    return service.getServiceById(id);
  }

  @GetMapping("/services/search")
  public List<ServiceItem> searchServices(@RequestParam String city) {
    return service.searchServicesByCity(city);
  }
}
