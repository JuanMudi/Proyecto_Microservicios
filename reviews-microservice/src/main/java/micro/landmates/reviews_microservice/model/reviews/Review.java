package micro.landmates.reviews_microservice.model.reviews;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
@Document(collection = "reviews")
public class Review {
    private String id;
    private String serviceItemId;
    private String userId;
    private int rating;
    private String comment;
}
