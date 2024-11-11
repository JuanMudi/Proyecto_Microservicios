package micro.landmates.users_microservice.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import micro.landmates.users_microservice.model.User;
import micro.landmates.users_microservice.model.UserDTO;
import micro.landmates.users_microservice.service.UserService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("")
@Slf4j
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/profile")
  public ResponseEntity<UserDTO> getUserProfile(@AuthenticationPrincipal Jwt jwt) {
    if (jwt == null) {
      System.err.println("JWT is null - Authorization header might be missing or incorrect");
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    System.out.println("JWT Claims: " + jwt.getClaims());
    String userId = jwt.getClaim("sub");
    Optional<User> user = userService.getById(userId);

    if (!user.isPresent()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    UserDTO userToReturn = UserDTO.builder()
        .age(user.get().getAge())
        .bio(user.get().getBio())
        .email(user.get().getEmail())
        .name(user.get().getName())
        .build();

    return ResponseEntity.ok(userToReturn);
  }

}
