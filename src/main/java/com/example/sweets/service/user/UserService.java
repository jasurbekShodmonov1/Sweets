package com.example.sweets.service.user;

import com.example.sweets.dto.emailOtp.OtpData;
import com.example.sweets.dto.request.user.UserRequestDto;
import com.example.sweets.dto.response.user.UserResponseDto;
import com.example.sweets.entity.user.Role;
import com.example.sweets.entity.user.User;
import com.example.sweets.mapper.UserMapper;
import com.example.sweets.repository.ProductRepository;
import com.example.sweets.repository.RoleRepository;
import com.example.sweets.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import com.example.sweets.util.OTPGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  private final RoleRepository roleRepository;
  private final JavaMailSender mailSender;
  private final ProductRepository productRepository;
  private final PasswordEncoder passwordEncoder;
  private final Map<String, OtpData> otpStorage = new ConcurrentHashMap<>();


  public List<UserResponseDto> getActiveAll() {
    List<User> users = userRepository.findAllByEnabledTrue();

    return users.stream().map(userMapper::toDto).toList();
  }

  public List<UserResponseDto> getDeactiveAll() {
    List<User> users = userRepository.findAllByEnabledFalse();

    return users.stream().map(userMapper::toDto).toList();
  }

  public UserResponseDto getUserById(UUID id) {
    User user =
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

    return userMapper.toDto(user);
  }

  public UserResponseDto createUser(UserRequestDto userRequestDto) {

    User user = userMapper.toEntity(userRequestDto);

    String rawPassword = userRequestDto.password();
    if (rawPassword.length() < 8
            || !rawPassword.matches(".*[A-Z].*")
            || !rawPassword.matches(".*[^a-zA-Z0-9].*")) {
          throw new IllegalArgumentException("Password must be at least 8 characters, contain one uppercase letter, and one special symbol");
    }
    user.setPassword(passwordEncoder.encode(userRequestDto.password()));

    List<Role> roles = roleRepository.findAllById(userRequestDto.roleIds());
    user.setRoles(roles);
    user.setEnabled(false);
    User saved = userRepository.save(user);

    sendOpt(user.getEmail());
    return userMapper.toDto(saved);
  }

  public UserResponseDto updateUser(UUID userId, UserRequestDto userRequestDto) {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));

    user.setPassword(passwordEncoder.encode(userRequestDto.password()));

    List<Role> roles = roleRepository.findAllById(userRequestDto.roleIds());
    user.setRoles(roles);

    userMapper.updateUserFromDto(userRequestDto, user);
    User saved = userRepository.save(user);

    return userMapper.toDto(saved);
  }

  public Boolean deleteUser(UUID id) {
    User user =
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));

    user.setEnabled(false);
    userRepository.save(user);

    return true;
  }

  public Boolean activeUser(UUID id) {
    User user =
        userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not fount"));

    user.setEnabled(true);
    userRepository.save(user);

    return true;
  }

  public void sendOpt(String email){
      String otp = OTPGenerator.generateOtp();
      LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);
      otpStorage.put(email, new OtpData(otp, expiresAt, email));

      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(email);
      message.setSubject("Verify your email");
      message.setText("Your OTP code is: " + otp + "\nIt expires in 5 minutes.");
      mailSender.send(message);
  }

  public void resendOtp(String email){
      otpStorage.remove(email);
      String otp = OTPGenerator.generateOtp();
      LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(5);
      otpStorage.put(email, new OtpData(otp, expiresAt, email));

      SimpleMailMessage message = new SimpleMailMessage();
      message.setTo(email);
      message.setSubject("Verify your email");
      message.setText("Your  OTP code is: " + otp + "\nIt expires in 5 minutes.");
      mailSender.send(message);

  }

  public boolean verifyOtp(String email, String otp) {
      OtpData data = otpStorage.get(email);
      if (data == null) return false;

      if (data.expiresAt().isBefore(LocalDateTime.now())) {
          otpStorage.remove(email);
          return false;
      }

      if (!data.otp().equals(otp)) return false;


      User user = userRepository.findByEmail(email)
              .orElseThrow(() -> new RuntimeException("User not found"));
      user.setEnabled(true);
      userRepository.save(user);

      otpStorage.remove(email);
      return true;
    }
}
