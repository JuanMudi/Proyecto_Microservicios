package micro.landmates.marketplace.resolver;

import graphql.kickstart.tools.GraphQLMutationResolver;
import micro.landmates.marketplace.model.ServiceItem;
import micro.landmates.marketplace.repository.ServiceItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Mutation implements GraphQLMutationResolver {

    @Autowired
    private ServiceItemRepository serviceItemRepository;

    public ServiceItem createService(String title, String description, String category, Double price) {
        ServiceItem serviceItem = ServiceItem.builder()
                .title(title)
                .description(description)
                .category(category)
                .price(price)
                .build();

        return serviceItemRepository.save(serviceItem);
    }
}
