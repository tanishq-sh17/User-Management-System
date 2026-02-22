package com.springtan.mapper;

import com.springtan.dto.UserRequestDto;
import com.springtan.dto.UserResponseDto;
import com.springtan.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "contacts", ignore = true)
    User toEntity(UserRequestDto userRequestDto);

    UserResponseDto toResponseDto(User user);

    UserRequestDto toRequestDto(User user);

    @Mapping(target = "contacts", ignore = true)
    void updateEntityFromRequestDto(
            UserRequestDto userRequestDto,
            @MappingTarget User user
    );


}
