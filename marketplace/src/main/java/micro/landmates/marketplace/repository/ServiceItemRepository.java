package micro.landmates.marketplace.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import micro.landmates.marketplace.model.ServiceItem;

@Repository
public interface ServiceItemRepository extends JpaRepository<ServiceItem, Long> {
  List<ServiceItem> findByCategory(String category);

  List<ServiceItem> findByCity(String city);
}
