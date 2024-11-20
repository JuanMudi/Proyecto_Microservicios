package micro.landmates.gateway.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
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
