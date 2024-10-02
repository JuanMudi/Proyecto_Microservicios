package micro.landmates.marketplace.service;

import org.springframework.stereotype.Service;

import micro.landmates.marketplace.model.ServiceItem;
import micro.landmates.marketplace.repository.ServiceItemRepository;
import java.util.List;

@Service
public class MarketplaceService {

  private final ServiceItemRepository repository;

  MarketplaceService(ServiceItemRepository serviceItemRepository) {
    this.repository = serviceItemRepository;
  }

  public List<ServiceItem> getAllServices() {
    return repository.findAll();
  }

  public List<ServiceItem> getServicesByCategory(String category) {
    return repository.findByCategory(category);
  }

  public ServiceItem getServiceById(Long id) {
    return repository.findById(id).orElse(null);
  }

  public List<ServiceItem> searchServicesByCity(String city) {
    return repository.findByCity(city);
  }

}
