package micro.landmates.users_microservice.service;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import lombok.extern.slf4j.Slf4j;
import micro.landmates.users_microservice.data.UserRepository;
import micro.landmates.users_microservice.model.User;
import micro.landmates.users_microservice.model.UserDTO;

@Configuration
@Slf4j
public class UserConsumer {

  private final UserRepository userRepository;

  public UserConsumer(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Bean
  public Consumer<Message<UserDTO>> receiveMessage() {
    return message -> {
      UserDTO userDTO = message.getPayload();

      User user = new User();
      user.setId(userDTO.getId());
      user.setName(userDTO.getName());
      user.setEmail(userDTO.getEmail());
      user.setAge(userDTO.getAge());
      user.setBio(userDTO.getBio());

      userRepository.save(user);
      log.info("User saved: {}", user);
    };
  }
}
