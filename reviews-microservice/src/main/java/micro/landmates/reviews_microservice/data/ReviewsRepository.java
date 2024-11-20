package micro.landmates.reviews_microservice.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import micro.landmates.reviews_microservice.model.reviews.Review;

public interface ReviewsRepository extends MongoRepository<Review, String> {
    List<Review> findByServiceItemId(String serviceItemId);
}
