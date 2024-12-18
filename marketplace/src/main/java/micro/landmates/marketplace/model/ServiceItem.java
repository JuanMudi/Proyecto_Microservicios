package micro.landmates.marketplace.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.Nullable;

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
@Document(collection = "serviceItems")
public class ServiceItem {

  @Id
  private String id;
  private String ownerId;
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
