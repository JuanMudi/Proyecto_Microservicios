package micro.landmates.marketplace.service;

import micro.landmates.marketplace.model.PublishServiceItemDto;
import org.springframework.stereotype.Service;

import micro.landmates.marketplace.model.ServiceItem;
import micro.landmates.marketplace.repository.ServiceItemRepository;
import java.util.List;

@Service
public class MarketplaceService {

  private final ServiceItemRepository repository;

  public MarketplaceService(ServiceItemRepository serviceItemRepository) {
    this.repository = serviceItemRepository;
  }

  public List<ServiceItem> getAllServices() {
    return repository.findAll();
  }

  public List<ServiceItem> getServicesByCategory(String category) {
    return repository.findByCategory(category);
  }

  public ServiceItem getServiceById(String id) {
    return repository.findById(id).orElse(null);
  }

  public List<ServiceItem> searchServicesByCity(String city) {
    return repository.findByCity(city);
  }

  public ServiceItem createServiceItem(PublishServiceItemDto serviceItem, String userId) {
    ServiceItem serviceItemEntity = ServiceItem.builder()
            .ownerId(userId)
            .price(serviceItem.getPrice())
            .category(serviceItem.getCategory())
            .city(serviceItem.getCity())
            .country(serviceItem.getCountry())
            .description(serviceItem.getDescription())
            .title(serviceItem.getTitle())
            .latitude(serviceItem.getLatitude())
            .longitude(serviceItem.getLongitude())
            .priceUnit(serviceItem.getPriceUnit())
            .build();
    return repository.save(serviceItemEntity);
  }
}