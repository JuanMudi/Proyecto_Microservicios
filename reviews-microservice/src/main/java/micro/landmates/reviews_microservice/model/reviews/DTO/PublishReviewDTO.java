package micro.landmates.reviews_microservice.model.reviews.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PublishReviewDTO {
    private String serviceItemId;
    private int rating;
    private String comment;
}
