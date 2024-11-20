package micro.landmates.users_microservice;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;

import micro.landmates.users_microservice.model.UserDTO;
import micro.landmates.users_microservice.model.UserSummaryDTO;
import micro.landmates.users_microservice.service.UserService;

@SpringBootTest(classes = UsersMicroserviceApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@DirtiesContext
public class UserControllerIntegrationTest {

  @Autowired
  private WebTestClient webTestClient;

  @MockBean
  private UserService userService;

  @Test
  public void testGetUserProfile_Unauthorized() {
    webTestClient.get()
        .uri("/profile")
        .exchange()
        .expectStatus()
        .isUnauthorized();
  }

  @Test
  public void testGetUserProfile_NotFound() {
    when(userService.getUserProfile("12345")).thenReturn(Optional.empty());

    webTestClient
        .mutateWith(SecurityMockServerConfigurers.mockJwt().jwt(jwt -> {
          jwt.claim("sub", "12345");
          jwt.claim("roles", List.of("user"));
        }))
        .get()
        .uri("/profile")
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  public void testUpdateUserProfile_Unauthorized() {
    UserDTO updatedUser = new UserDTO("12345", "Updated Name", "updated@example.com", 25, "Updated bio", "3003898475",
        "page.com", List.of("instagram"));

    webTestClient
        .put()
        .uri("/profile")
        .bodyValue(updatedUser)
        .exchange()
        .expectStatus()
        .isUnauthorized();
  }

  @Test
  public void testUpdateUserProfile_Forbidden() {
    UserDTO updatedUser = new UserDTO("12345", "Updated Name", "updated@example.com", 25, "Updated bio", "3003898475",
        "page.com", List.of("instagram"));

    webTestClient
        .mutateWith(SecurityMockServerConfigurers.mockJwt().jwt(jwt -> {
          jwt.claim("sub", "67890");
          jwt.claim("roles", List.of("user"));
        }))
        .put()
        .uri("/profile")
        .bodyValue(updatedUser)
        .exchange()
        .expectStatus()
        .isForbidden();
  }

  @Test
  public void testUpdateUserProfile_Success() {
    // WebTestClient webTestClient =
    // WebTestClient.bindToApplicationContext(applicationContext).build();

    UserDTO updatedUser = new UserDTO("12345", "Updated Name", "updated@example.com", 25, "Updated bio", "3003898475",
        "page.com", List.of("instagram"));

    when(userService.updateUserProfile(eq("67890"), any(UserDTO.class))).thenReturn(updatedUser);

    webTestClient
        .mutateWith(SecurityMockServerConfigurers.mockJwt().jwt(jwt -> {
          jwt.claim("sub", "67890");
          jwt.claim("roles", List.of("user"));
        }))
        .put()
        .uri("/profile")
        .bodyValue(updatedUser)
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(UserDTO.class)
        .isEqualTo(updatedUser);
  }

  @Test
  public void testDeleteUser_Unauthorized() {
    webTestClient.delete()
        .uri("/profile/12345")
        .exchange()
        .expectStatus()
        .isUnauthorized();
  }

  @Test
  public void testDeleteUser_Forbidden() {
    webTestClient
        .mutateWith(SecurityMockServerConfigurers.mockJwt().jwt(jwt -> {
          jwt.claim("sub", "67890");
          jwt.claim("roles", List.of("user"));
        }))
        .delete()
        .uri("/profile/12345")
        .exchange()
        .expectStatus()
        .isForbidden();
  }

  @Test
  public void testDeleteUser_Success() {
    webTestClient
        .mutateWith(SecurityMockServerConfigurers.mockJwt().jwt(jwt -> {
          jwt.claim("sub", "12345");
          jwt.claim("roles", List.of("user"));
        }))
        .delete()
        .uri("/profile/12345")
        .exchange()
        .expectStatus()
        .isOk();
  }

  @Test
  public void testGetAllUsers_Success() {
    List<UserSummaryDTO> userSummaries = List.of(
        new UserSummaryDTO("12345", "User One", "user1@example.com"),
        new UserSummaryDTO("67890", "User Two", "user2@example.com"));

    when(userService.getAllUsers()).thenReturn(userSummaries);

    webTestClient.get()
        .uri("/")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBodyList(UserSummaryDTO.class)
        .isEqualTo(userSummaries);
  }

  @Test
  public void testGetUserById_NotFound() {
    when(userService.getUserById("12345")).thenReturn(Optional.empty());

    webTestClient.get()
        .uri("/12345")
        .exchange()
        .expectStatus()
        .isNotFound();
  }

  @Test
  public void testGetUserById_Success() {
    UserSummaryDTO userSummary = new UserSummaryDTO("12345", "User One", "user1@example.com");

    when(userService.getUserById("12345")).thenReturn(Optional.of(userSummary));

    webTestClient.get()
        .uri("/12345")
        .exchange()
        .expectStatus()
        .isOk()
        .expectBody(UserSummaryDTO.class)
        .isEqualTo(userSummary);
  }
}
