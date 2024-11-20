package micro.landmates.reviews_microservice.service;

import java.util.List;

import micro.landmates.reviews_microservice.data.ReviewsRepository;
import micro.landmates.reviews_microservice.model.reviews.Review;
import micro.landmates.reviews_microservice.model.reviews.DTO.PublishReviewDTO;

public class ReviewsService {
    private final ReviewsRepository repository;

    public ReviewsService(ReviewsRepository repository) {
        this.repository = repository;
    }

    public Review saveReview(PublishReviewDTO review, String userId) {
        Review reviewToSave = Review.builder()
                .serviceItemId(review.getServiceItemId())
                .userId(userId)
                .rating(review.getRating())
                .comment(review.getComment())
                .build();

        reviewToSave = repository.save(reviewToSave);
        return reviewToSave;
    }

    public List<Review> getReviewsForService(String serviceItemId) {
        return repository.findByServiceItemId(serviceItemId);
    }
}
