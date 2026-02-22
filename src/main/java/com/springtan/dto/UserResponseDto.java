package com.springtan.dto;

import org.mapstruct.Mapping;

import java.util.List;

public record UserResponseDto(
        Long id,
        String name,
        String email,
        String about,
        List<ContactResponseDto> contacts
)
{
}
