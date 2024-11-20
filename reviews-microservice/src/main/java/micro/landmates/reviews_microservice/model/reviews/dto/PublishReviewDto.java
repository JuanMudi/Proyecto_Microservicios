package micro.landmates.reviews_microservice.model.reviews.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PublishReviewDto {
    private String serviceItemId;
    private int rating;
    private String comment;
}
