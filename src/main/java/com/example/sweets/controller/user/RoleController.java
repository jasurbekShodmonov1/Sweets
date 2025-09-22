package com.example.sweets.controller.user;

import com.example.sweets.dto.request.user.RoleRequestDto;
import com.example.sweets.dto.response.user.RoleResponseDto;
import com.example.sweets.service.user.RoleService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roles/v1")
@RequiredArgsConstructor
public class RoleController {

  private final RoleService roleService;

  @GetMapping()
  public List<RoleResponseDto> getRoles() {
    List<RoleResponseDto> roleResponseDtos = roleService.getAll();
    return roleResponseDtos;
  }

  @GetMapping("/{roleId}")
  public ResponseEntity<RoleResponseDto> getRole(@PathVariable("roleId") UUID id) {
    RoleResponseDto role = roleService.get(id);
    return ResponseEntity.ok(role);
  }

  @PostMapping()
  public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto roleRequestDto) {
    RoleResponseDto create = roleService.create(roleRequestDto);
    return ResponseEntity.ok(create);
  }

  @PutMapping("/{roleId}")
  public ResponseEntity<RoleResponseDto> updateRole(
      @PathVariable("roleId") UUID id, @RequestBody RoleRequestDto roleRequestDto) {
    RoleResponseDto update = roleService.update(id, roleRequestDto);
    return ResponseEntity.ok(update);
  }
}
