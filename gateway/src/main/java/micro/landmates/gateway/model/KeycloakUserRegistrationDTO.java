package micro.landmates.gateway.model;

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
@Builder
public class KeycloakUserRegistrationDTO {

  private List<Credential> credentials;
  private String username;
  private String firstName;
  private String lastName;
  private String email;
  private boolean emailVerified;
  private boolean enabled;

  @Getter
  @Setter
  @NoArgsConstructor
  @AllArgsConstructor
  @ToString
  public static class Credential {
    private boolean temporary;
    private String type;
    private String value;
  }
}
