package com.example.sweets.exception;

public class DeleteForbiddenException extends RuntimeException {
  public DeleteForbiddenException(String message) {
    super(message);
  }

  public DeleteForbiddenException(String message, Throwable cause) {
    super(message, cause);
  }
}
