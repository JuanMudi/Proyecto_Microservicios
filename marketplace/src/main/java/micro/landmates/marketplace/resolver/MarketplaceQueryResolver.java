package micro.landmates.marketplace.resolver;

import micro.landmates.marketplace.model.ServiceItem;
import micro.landmates.marketplace.service.MarketplaceService;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class MarketplaceQueryResolver {

    private final MarketplaceService marketplaceService;

    public MarketplaceQueryResolver(MarketplaceService marketplaceService) {
        this.marketplaceService = marketplaceService;
    }

    @QueryMapping
    public List<ServiceItem> getServices() {
        List<ServiceItem> services = marketplaceService.getAllServices();
        return services;
    }

    @QueryMapping
    public ServiceItem getServiceById(@Argument String id) {
        return marketplaceService.getServiceById(id);
    }
}
