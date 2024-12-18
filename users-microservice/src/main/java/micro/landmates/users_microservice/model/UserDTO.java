package micro.landmates.users_microservice.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class UserDTO implements Serializable {
  private String id;
  private String name;
  private String email;
  private int age;
  private String bio;
  private String phoneNumber;
  private String webPage;
  private List<String> socials;
}
