package com.springtan.dto;

import com.springtan.entity.Contact;
import com.springtan.util.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UserRequestDto(

        @NotBlank(message = AppConstants.BLANK_NAME_FIELD)
        String name,

        @Email(
                message = AppConstants.INVALID_EMAIL,
                regexp = AppConstants.VALID_EMAIL_REGEX
        )
        String email,

        String about,

        @NotBlank(message = AppConstants.BLANK_PASSWORD_FIELD)
        @Size(min = 8, max = 20)
        String password,

        String role,

        List<Contact> contacts
)
{
}
