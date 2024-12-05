package com.aidar.prodcat.mappers;


import com.aidar.prodcat.dtos.User.UserRequestDTO;
import com.aidar.prodcat.dtos.User.UserResponseDTO;
import com.aidar.prodcat.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDTO userRequestDTO);


    UserResponseDTO toResponse(User user);
}
