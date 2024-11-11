package micro.landmates.users_microservice.controller;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import micro.landmates.users_microservice.model.UserDTO;
import micro.landmates.users_microservice.service.UserService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
            log.error("JWT is null - Authorization header might be missing or incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String userId = jwt.getClaim("sub");

        return userService.getUserProfile(userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping("/profile")
    public ResponseEntity<UserDTO> updateUserProfile(@AuthenticationPrincipal Jwt jwt,
            @RequestBody UserDTO updatedUserDTO) {
        if (jwt == null) {
            log.error("JWT is null - Authorization header might be missing or incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String userId = jwt.getClaim("sub");

        if (!userId.equals(updatedUserDTO.getId())) {
            log.error("User ID in token does not match ID in the request body");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        try {
            UserDTO userToReturn = userService.updateUserProfile(userId, updatedUserDTO);
            return ResponseEntity.ok(userToReturn);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/profile/{id}")
    public ResponseEntity<Void> deleteUser(@AuthenticationPrincipal Jwt jwt, @PathVariable String id) {
        if (jwt == null) {
            log.error("JWT is null - Authorization header might be missing or incorrect");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String userIdFromToken = jwt.getClaim("sub");
        if (!userIdFromToken.equals(id)) {
            log.error("User ID in token does not match ID in the request path");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        String accessToken = jwt.getTokenValue();

        userService.deleteUser(id, accessToken);
        return ResponseEntity.ok().build();
    }

}
