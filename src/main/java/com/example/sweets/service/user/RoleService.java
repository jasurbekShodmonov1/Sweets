package com.example.sweets.service.user;

import com.example.sweets.dto.request.user.RoleRequestDto;
import com.example.sweets.dto.response.user.RoleResponseDto;
import com.example.sweets.entity.user.Role;
import com.example.sweets.mapper.RoleMapper;
import com.example.sweets.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public List<RoleResponseDto> getAll(){
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(roleMapper::toDto).toList();
    }

    public RoleResponseDto get(UUID id){
        Role role = roleRepository.findById(id).orElseThrow(()-> new RuntimeException("role  not found"));

        return  roleMapper.toDto(role);
    }

    public RoleResponseDto create(RoleRequestDto roleRequestDto){
        Role role = roleMapper.toEntity(roleRequestDto);
        Role saved = roleRepository.save(role);

        return roleMapper.toDto(saved);
    }

    public RoleResponseDto update(UUID id, RoleRequestDto roleRequestDto){
        Role role = roleRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Role  not found"));

        roleMapper.updateFromDto(roleRequestDto,role);
        Role saved = roleRepository.save(role);

        return roleMapper.toDto(saved);
    }

}
