package micro.landmates.reviews_microservice.controller;

import micro.landmates.reviews_microservice.model.reviews.Review;
import micro.landmates.reviews_microservice.model.reviews.dto.PublishReviewDto;
import micro.landmates.reviews_microservice.service.ReviewsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
public class ReviewsController {
    private final ReviewsService reviewsService;

    public ReviewsController(ReviewsService reviewsService) {
        this.reviewsService = reviewsService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody PublishReviewDto review, Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userId = jwt.getSubject();
        return new ResponseEntity<Review>(this.reviewsService.createReview(review, userId), HttpStatus.CREATED);
    }

    @GetMapping("/{serviceItemId}")
    public ResponseEntity<List<Review>> getReviewsByServiceItemId(@PathVariable String serviceItemId) {
        List<Review> reviews = reviewsService.getReviewsByServiceItemId(serviceItemId);
        return ResponseEntity.ok(reviews);
    }
}
