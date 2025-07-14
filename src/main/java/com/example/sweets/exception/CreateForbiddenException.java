package com.example.sweets.exception;

public class CreateForbiddenException extends RuntimeException {
  public CreateForbiddenException(String message) {
    super(message);
  }

  public CreateForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }
}
