package micro.landmates.users_microservice.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Builder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UserSummaryDTO implements Serializable {
  private String id;
  private String name;
  private String email;
}
