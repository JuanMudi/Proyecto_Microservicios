package micro.landmates.marketplace.resolver;

import graphql.kickstart.tools.GraphQLQueryResolver;
import micro.landmates.marketplace.model.ServiceItem;
import micro.landmates.marketplace.repository.ServiceItemRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {

    private final ServiceItemRepository serviceItemRepository;

    public Query(ServiceItemRepository serviceItemRepository) {
        this.serviceItemRepository = serviceItemRepository;
    }

    public List<ServiceItem> getServices() {
        return serviceItemRepository.findAll();
    }

    public ServiceItem getServiceById(String id) {
        return serviceItemRepository.findById(id).orElse(null);
    }
}
