package com.griotold.springboot_sns.domain.user;

public class UserException extends RuntimeException {

  public UserException(String message) {
    super(message);
  }

  public static class EmailAlreadyExists extends UserException {
    public EmailAlreadyExists(String email) {
      super("이미 존재하는 이메일입니다: " + email);
    }
  }

  public static class UserNotFound extends UserException {
    public UserNotFound(Long id) {
      super("사용자를 찾을 수 없습니다: " + id);
    }
  }
}
