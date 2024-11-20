package micro.landmates.marketplace.model;

import com.mongodb.lang.Nullable;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class PublishServiceItemDto {
    private String title;
    private String description;
    private String category;
    private double price;
    private String priceUnit;
    private String country;
    private String city;
    @Nullable
    private String latitude;
    @Nullable
    private String longitude;
}
