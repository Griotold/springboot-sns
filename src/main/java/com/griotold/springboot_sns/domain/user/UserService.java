package com.griotold.springboot_sns.domain.user;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User signup(User user) {
    if (userRepository.existsByEmail(user.getEmail())) {
      throw new UserException.EmailAlreadyExists(user.getEmail());
    }
    return userRepository.save(user);
  }

  public User getUser(Long id) {
    return userRepository.findById(id).orElseThrow(() -> new UserException.UserNotFound(id));
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }

  @Transactional
  public User updateUser(Long id, String nickname, String password) {
    User user = userRepository.findById(id).orElseThrow(() -> new UserException.UserNotFound(id));
    user.update(nickname, password);
    return user;
  }

  public void deleteUser(Long id) {
    if (!userRepository.existsById(id)) {
      throw new UserException.UserNotFound(id);
    }
    userRepository.deleteById(id);
  }
}
