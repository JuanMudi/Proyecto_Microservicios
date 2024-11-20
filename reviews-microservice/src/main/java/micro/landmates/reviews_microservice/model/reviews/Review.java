package micro.landmates.reviews_microservice.model.reviews;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;
    private String serviceItemId;
    private String userId;
    private int rating;
    private String comment;
}
