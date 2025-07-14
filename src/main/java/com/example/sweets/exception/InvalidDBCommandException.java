package com.example.sweets.exception;

public class InvalidDBCommandException extends RuntimeException {
  public InvalidDBCommandException(String message) {
    super(message);
  }

  public InvalidDBCommandException(String message, Throwable cause) {
    super(message, cause);
  }
}
