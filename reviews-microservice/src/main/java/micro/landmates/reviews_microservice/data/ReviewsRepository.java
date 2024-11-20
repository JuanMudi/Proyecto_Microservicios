package micro.landmates.reviews_microservice.data;

import micro.landmates.reviews_microservice.model.reviews.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReviewsRepository extends MongoRepository<Review, String> {
    public List<Review> getReviewByServiceItemId(String serviceItemId);
}
