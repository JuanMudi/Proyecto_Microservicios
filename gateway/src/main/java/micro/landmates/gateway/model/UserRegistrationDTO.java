package micro.landmates.gateway.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegistrationDTO {
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private boolean emailVerified;
  private boolean enabled;
  private String password;
  private int age;
  private String bio;
  private String phoneNumber;
  private String webPage;
  private List<String> socials;
}
