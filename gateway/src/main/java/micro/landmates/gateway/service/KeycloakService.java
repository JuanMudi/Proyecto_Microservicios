package micro.landmates.gateway.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.annotation.PostConstruct;

import org.springframework.http.*;

import java.time.Instant;

import org.json.JSONObject;

@Service
public class KeycloakService {
  @Value("${keycloak.token-url}")
  private String tokenUrl; // URL para obtener el token

  @Value("${keycloak.client-id}")
  private String clientId;

  @Value("${keycloak.client-secret}")
  private String clientSecret;

  private final RestTemplate restTemplate;
  private String accessToken;
  private Instant tokenExpirationTime;

  public KeycloakService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @PostConstruct
  public void init() {
    obtainAccessToken();
  }

  private void obtainAccessToken() {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    String body = "grant_type=client_credentials&client_id=" + clientId + "&client_secret=" + clientSecret;

    HttpEntity<String> request = new HttpEntity<>(body, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, request, String.class);

    if (response.getStatusCode() == HttpStatus.OK) {
      JSONObject jsonObject = new JSONObject(response.getBody());
      accessToken = jsonObject.getString("access_token");
      int expiresIn = jsonObject.getInt("expires_in");
      tokenExpirationTime = Instant.now().plusSeconds(expiresIn);
    } else {
      throw new RuntimeException("Failed to get access token: " + response.getStatusCode());
    }
  }

  public String getAccessToken() {
    // Verificar si el token necesita ser renovado antes de devolverlo
    if (isTokenExpired()) {
      obtainAccessToken();
    }
    return accessToken;
  }

  private boolean isTokenExpired() {
    return Instant.now().isAfter(tokenExpirationTime);
  }
}
