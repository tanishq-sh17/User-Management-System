package com.springtan.dto;

import com.springtan.util.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDto(

        @NotBlank(message = "Name can't be empty")
        String name,
        @Email(message = "Invalid email", regexp = AppConstants.VALID_EMAIL_REGEX)
        String email,

        String about,
        @NotBlank(message = "Password can't be empty")
        @Size(min = 8, max = 16)
        String password
)
{
}
