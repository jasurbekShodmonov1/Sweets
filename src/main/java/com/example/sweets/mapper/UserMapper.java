package com.example.sweets.mapper;


import com.example.sweets.dto.request.user.UserRequestDto;
import com.example.sweets.dto.response.user.UserResponseDto;
import com.example.sweets.entity.user.Role;
import com.example.sweets.entity.user.User;
//import com.example.sweets.util.RoleMapperHelper;
import org.mapstruct.*;



@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {


    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toDto(User user);


    void updateUserFromDto(UserRequestDto userRequestDto, @MappingTarget User user);



}
