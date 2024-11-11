package micro.landmates.users_microservice.service;

import java.util.Optional;
import org.springframework.stereotype.Service;
import micro.landmates.users_microservice.data.UserRepository;
import micro.landmates.users_microservice.model.User;
import micro.landmates.users_microservice.model.UserDTO;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public Optional<UserDTO> getUserProfile(String userId) {
    return userRepository.findById(userId)
        .map(user -> UserDTO.builder()
            .id(user.getId())
            .name(user.getName())
            .email(user.getEmail())
            .age(user.getAge())
            .bio(user.getBio())
            .build());
  }

  public Optional<User> getById(String userId) {
    return userRepository.findById(userId);
  }

  public UserDTO updateUserProfile(String userId, UserDTO updatedUserDTO) {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

    user.setName(updatedUserDTO.getName());
    user.setEmail(updatedUserDTO.getEmail());
    user.setAge(updatedUserDTO.getAge());
    user.setBio(updatedUserDTO.getBio());

    User savedUser = userRepository.save(user);

    return UserDTO.builder()
        .id(savedUser.getId())
        .name(savedUser.getName())
        .email(savedUser.getEmail())
        .age(savedUser.getAge())
        .bio(savedUser.getBio())
        .build();
  }

  public User save(User user) {
    return userRepository.save(user);
  }

  public boolean deleteUser(String userId, String accessToken) {
    Optional<User> existingUser = userRepository.findById(userId);
    if (!existingUser.isPresent()) {
      return false;
    }

    User user = existingUser.get();
    user.setActive(false);
    userRepository.save(user);
    return true;
  }
}