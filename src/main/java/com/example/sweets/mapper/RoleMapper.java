package com.example.sweets.mapper;

import com.example.sweets.dto.request.user.RoleRequestDto;
import com.example.sweets.dto.response.user.RoleResponseDto;
import com.example.sweets.entity.user.Role;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

  Role toEntity(RoleRequestDto roleRequestDto);

  RoleResponseDto toDto(Role role);

  List<RoleResponseDto> toDtoList(List<Role> roles);

  @Mapping(target = "id", ignore = true)
  void updateFromDto(RoleRequestDto roleRequestDto, @MappingTarget Role role);
}
