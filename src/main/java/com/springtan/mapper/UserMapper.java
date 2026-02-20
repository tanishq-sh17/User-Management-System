package com.springtan.mapper;

import com.springtan.dto.UserRequestDto;
import com.springtan.dto.UserResponseDto;
import com.springtan.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toResponseDto(User user);

    UserRequestDto toRequestDto(User user);

    void updateEntityFromRequestDto(
            UserRequestDto userRequestDto,
            @MappingTarget User user
    );


}
