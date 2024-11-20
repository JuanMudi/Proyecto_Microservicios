package micro.landmates.reviews_microservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import micro.landmates.reviews_microservice.model.reviews.Review;
import micro.landmates.reviews_microservice.model.reviews.DTO.PublishReviewDTO;
import micro.landmates.reviews_microservice.service.ReviewsService;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("")
public class ReviewsController {
    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping
    public ResponseEntity<Review> publishReview(@Valid @RequestBody PublishReviewDTO review,
                                                Authentication authentication) {

        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = jwt.getSubject();

        Review savedReview = reviewsService.saveReview(review, userId);
        return ResponseEntity.ok(savedReview);
    }

    @GetMapping("/{serviceItemId}")
    public ResponseEntity<List<Review>> getReviewsByServiceItemId(@PathVariable String serviceItemId) {
        List<Review> reviews = reviewsService.getReviewsForService(serviceItemId);
        return ResponseEntity.ok(reviews);
    }

}
