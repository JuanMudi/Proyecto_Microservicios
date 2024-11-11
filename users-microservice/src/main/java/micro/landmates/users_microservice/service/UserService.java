package micro.landmates.users_microservice.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import micro.landmates.users_microservice.data.UserRepository;
import micro.landmates.users_microservice.model.User;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<User> getById(String userId) {
    return userRepository.findById(userId);
  }
}
