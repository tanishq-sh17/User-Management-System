package com.springtan.dto;

public record ContactResponseDto(
        Long id,
        String name,
        String phoneNumber,
        String email
//        Long userId
)
{
}
