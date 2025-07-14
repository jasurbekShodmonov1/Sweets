package com.example.sweets.service.user;


import com.example.sweets.dto.request.user.UserRequestDto;
import com.example.sweets.dto.response.user.UserResponseDto;
import com.example.sweets.entity.product.Product;
import com.example.sweets.entity.user.Role;
import com.example.sweets.entity.user.User;
import com.example.sweets.mapper.UserMapper;
import com.example.sweets.repository.ProductRepository;
import com.example.sweets.repository.RoleRepository;
import com.example.sweets.repository.UserRepository;
//import com.example.sweets.util.RoleMapperHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    public List<UserResponseDto> getActiveAll(){
        List<User>  users = userRepository.findAllByEnabledTrue();

        return users.stream().map(userMapper :: toDto).toList();
    }

    public List<UserResponseDto> getDeactiveAll(){
        List<User> users = userRepository.findAllByEnabledFalse();

        return users.stream().map(userMapper :: toDto).toList();
    }


    public UserResponseDto getUserById(UUID id){
        User user = userRepository
                .findById(id)
                .orElseThrow(()->new RuntimeException("user not found"));

        return userMapper.toDto(user);
    }

    public UserResponseDto createUser(UserRequestDto userRequestDto){

        User user = userMapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.password()));

        List<Role> roles = roleRepository.findAllById(userRequestDto.roleIds());
        user.setRoles(roles);
//        List<Product> product = productRepository.findAllById(.)
        User saved = userRepository.save(user);

        return userMapper.toDto(saved);
    }

    public UserResponseDto updateUser(UUID userId, UserRequestDto userRequestDto){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("user not found"));

        user.setPassword(passwordEncoder.encode(userRequestDto.password()));

        List<Role> roles= roleRepository.findAllById(userRequestDto.roleIds());
        user.setRoles(roles);

        userMapper.updateUserFromDto(userRequestDto, user);
        User saved = userRepository.save(user);

        return userMapper.toDto(saved);
    }

    public Boolean deleteUser(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("user not found"));

        user.setEnabled(false);
        userRepository.save(user);

        return true;
    }

    public Boolean activeUser(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("user not fount"));

        user.setEnabled(true);
        userRepository.save(user);

        return true;
    }
}

