package com.griotold.springboot_sns.controller;

import com.griotold.springboot_sns.controller.dto.UserResponse;
import com.griotold.springboot_sns.controller.dto.UserSignupRequest;
import com.griotold.springboot_sns.controller.dto.UserUpdateRequest;
import com.griotold.springboot_sns.domain.user.User;
import com.griotold.springboot_sns.domain.user.UserService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/api/users/signup")
  public ResponseEntity<UserResponse> signup(@Valid @RequestBody UserSignupRequest request) {
    User user = userService.signup(request.toEntity());
    return ResponseEntity.status(HttpStatus.CREATED).body(UserResponse.from(user));
  }

  @GetMapping("/api/users/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable Long id) {
    User user = userService.getUser(id);
    return ResponseEntity.ok(UserResponse.from(user));
  }

  @GetMapping("/api/users")
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    List<UserResponse> users = userService.getAllUsers().stream().map(UserResponse::from).toList();
    return ResponseEntity.ok(users);
  }

  @PutMapping("/api/users/{id}")
  public ResponseEntity<UserResponse> updateUser(
      @PathVariable Long id, @Valid @RequestBody UserUpdateRequest request) {
    User user = userService.updateUser(id, request.nickname(), request.password());
    return ResponseEntity.ok(UserResponse.from(user));
  }

  @DeleteMapping("/api/users/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
