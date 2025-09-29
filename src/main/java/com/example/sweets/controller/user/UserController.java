package com.example.sweets.controller.user;

import com.example.sweets.dto.request.user.UserRequestDto;
import com.example.sweets.dto.response.user.UserResponseDto;
import com.example.sweets.service.user.UserService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/v1")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping()
  public ResponseEntity<List<UserResponseDto>> getAllActiveUsers() {
    List<UserResponseDto> users = userService.getActiveAll();
    return ResponseEntity.ok(users);
  }

  @GetMapping("/deactive")
  private ResponseEntity<List<UserResponseDto>> getAllDeactiveUsers() {
    List<UserResponseDto> deactiveUsers = userService.getDeactiveAll();
    return ResponseEntity.ok(deactiveUsers);
  }

  @GetMapping("/{userId}")
  public ResponseEntity<UserResponseDto> getUserById(@PathVariable("userId") UUID id) {
    UserResponseDto user = userService.getUserById(id);
    return ResponseEntity.ok(user);
  }

  @PostMapping()
  public ResponseEntity<UserResponseDto> createUser(@RequestBody UserRequestDto userRequestDto) {
    UserResponseDto newUser = userService.createUser(userRequestDto);
    return ResponseEntity.ok(newUser);
  }

  @PostMapping("/verify")
  public ResponseEntity<String> verify(@RequestParam String email, @RequestParam String otp){
      boolean valid = userService.verifyOtp(email, otp);
      if(valid) {
          return ResponseEntity.ok("Email verified, account activated!");
      }
      return ResponseEntity.badRequest().body("Invalid or expired OTP.");
  }

  @PostMapping("/resendOtp")
  public ResponseEntity<String> resendOtp(@RequestParam String email){
      userService.resendOtp(email);
      return ResponseEntity.ok("New OTP has been sent to your email.");
  }

  @PutMapping("/{userId}")
  public ResponseEntity<UserResponseDto> updateUser(
      @PathVariable("userId") UUID id, @RequestBody UserRequestDto userRequestDto) {
    UserResponseDto updatedUser = userService.updateUser(id, userRequestDto);
    return ResponseEntity.ok(updatedUser);
  }

  @PutMapping("/deactive/{userId}")
  public ResponseEntity<Boolean> deactiveUser(@PathVariable("userId") UUID id) {
    return ResponseEntity.ok(userService.deleteUser(id));
  }

  @PutMapping("/reactive/{userId}")
  public ResponseEntity<Boolean> reactiveUser(@PathVariable("userId") UUID id) {
    return ResponseEntity.ok(userService.activeUser(id));
  }
}
