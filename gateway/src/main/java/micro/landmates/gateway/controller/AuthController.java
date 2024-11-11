package micro.landmates.gateway.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import micro.landmates.gateway.model.KeycloakUserRegistrationDTO;
import micro.landmates.gateway.model.KeycloakUserRegistrationDTO.Credential;
import micro.landmates.gateway.model.UserDTO;
import micro.landmates.gateway.model.UserRegistrationDTO;
import micro.landmates.gateway.service.KeycloakService;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final WebClient webClient;
    private final KeycloakService keycloakService;
    private final StreamBridge streamBridge;

    @Value("${spring.cloud.stream.bindings.sendMessage-out-0.destination:user-management}")
    private String userManagementTopic;

    @Value("${keycloak-url}")
    private String keycloakUrl;

    public AuthController(WebClient.Builder webClientBuilder, KeycloakService keycloakService,
            StreamBridge streamBridge) {
        this.webClient = webClientBuilder.build();
        this.keycloakService = keycloakService;
        this.streamBridge = streamBridge;
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<String>> registerUser(@RequestBody UserRegistrationDTO registration) {
        String accessToken = keycloakService.getAccessToken();

        KeycloakUserRegistrationDTO keycloakUser = KeycloakUserRegistrationDTO.builder()
                .username(registration.getUsername())
                .firstName(registration.getFirstName())
                .lastName(registration.getLastName())
                .email(registration.getEmail())
                .emailVerified(registration.isEmailVerified())
                .enabled(registration.isEnabled())
                .credentials(Collections.singletonList(new Credential(false, "password", registration.getPassword())))
                .build();

        return webClient
                .post()
                .uri(keycloakUrl + "/admin/realms/landmates/users")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .bodyValue(keycloakUser)
                .retrieve()
                .toEntity(String.class)
                .map(entity -> {
                    if (entity.getStatusCode().is2xxSuccessful()) {
                        String locationHeader = entity.getHeaders().getFirst(HttpHeaders.LOCATION);
                        String userId = null;
                        if (locationHeader != null) {
                            userId = locationHeader.substring(locationHeader.lastIndexOf('/') + 1);
                        }

                        UserDTO userDTO = UserDTO.builder()
                                .id(userId)
                                .name(registration.getFirstName() + " " + registration.getLastName())
                                .email(registration.getEmail())
                                .age(registration.getAge())
                                .bio(registration.getBio())
                                .build();
                        streamBridge.send(userManagementTopic, MessageBuilder.withPayload(userDTO).build());

                        return ResponseEntity.status(HttpStatus.CREATED).build();
                    } else if (entity.getStatusCode() == HttpStatus.CONFLICT) {
                        System.err.println("User already exists in Keycloak");
                        return ResponseEntity.status(HttpStatus.CONFLICT).build();
                    } else {
                        System.err.println("Registration failed: " + entity.getBody());
                        return ResponseEntity.status(entity.getStatusCode()).build();
                    }
                });
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<String>> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");

        // Construir el cuerpo de la solicitud con las credenciales proporcionadas
        String requestBody = "client_id=myapp&grant_type=password&username=" + username + "&password="
                + password;

        return webClient.post()
                .uri(keycloakUrl + "/realms/landmates/protocol/openid-connect/token")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .bodyValue(requestBody)
                .retrieve()
                .onStatus(HttpStatusCode::isError, response -> {
                    return response.bodyToMono(String.class)
                            .flatMap(errorBody -> {
                                if (response.statusCode().is4xxClientError()) {
                                    return Mono.error(new RuntimeException(
                                            "Login failed: Invalid credentials or incorrect request format. Please check your username and password."));
                                } else if (response.statusCode().is5xxServerError()) {
                                    return Mono.error(new RuntimeException(
                                            "Login failed: Server error. Please try again later."));
                                } else {
                                    return Mono.error(new RuntimeException(
                                            "Login failed: Unexpected error. Please contact support if the issue persists."));
                                }
                            });
                })
                .bodyToMono(String.class)
                .map(token -> ResponseEntity.ok().body(token))
                .onErrorResume(error -> {
                    String errorMessage = error.getMessage() != null ? error.getMessage()
                            : "Invalid credentials. Please try again.";
                    return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage));
                });
    }

}
