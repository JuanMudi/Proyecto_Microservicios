package micro.landmates.reviews_microservice.service;

import micro.landmates.reviews_microservice.data.ReviewsRepository;
import micro.landmates.reviews_microservice.model.reviews.Review;
import micro.landmates.reviews_microservice.model.reviews.dto.PublishReviewDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewsService {
    private final ReviewsRepository reviewsRepository;

    public ReviewsService(ReviewsRepository reviewsRepository) {
        this.reviewsRepository = reviewsRepository;
    }

    public Review createReview(PublishReviewDto review, String userId) {
        Review reviewEntity = Review.builder()
                .serviceItemId(review.getServiceItemId())
                .rating(review.getRating())
                .userId(userId)
                .comment(review.getComment())
                .build();
        return reviewsRepository.save(reviewEntity);
    }

    public List<Review> getReviewsByServiceItemId(String serviceItemId) {
        return reviewsRepository.findByServiceItemId(serviceItemId);
    }
}
