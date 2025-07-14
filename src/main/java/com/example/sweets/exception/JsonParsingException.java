package com.example.sweets.exception;

public class JsonParsingException extends RuntimeException {
  public JsonParsingException(String message) {
    super(message);
  }

  public JsonParsingException(String message, Throwable cause) {
    super(message, cause);
  }
}
