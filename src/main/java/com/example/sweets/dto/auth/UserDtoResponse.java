package com.example.sweets.dto.auth;

import com.example.sweets.exception.UnauthorizedException;
import java.util.Set;
import java.util.UUID;
import lombok.Builder;
import org.springframework.lang.Nullable;

@Builder
public record UserDtoResponse(
    UUID id,
    String username,
    Set<String> roles,
    @Nullable String surname,
    @Nullable String mobile,
    @Nullable Boolean isActive) {

  public static String getUserRole(UserDtoResponse currentUserData) {
    return currentUserData.roles.stream()
        .findFirst()
        .orElseThrow(() -> new UnauthorizedException("Role not found"));
  }
}
