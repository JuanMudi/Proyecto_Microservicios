package micro.landmates.marketplace.model;

import org.springframework.data.annotation.Id;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "serviceItems")
public class ServiceItem {

  @Id
  private String id;
  private String title;
  private String description;
  private String category;
  private double price;
  private String priceUnit;
  private String country;
  private String city;
}
