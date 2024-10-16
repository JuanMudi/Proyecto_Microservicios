package micro.landmates.marketplace.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import micro.landmates.marketplace.model.ServiceItem;

public interface ServiceItemRepository extends MongoRepository<ServiceItem, String>{
  List<ServiceItem> findByCategory(String category);

  List<ServiceItem> findByCity(String city);
}
