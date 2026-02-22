package com.springtan.dto;

import com.springtan.util.AppConstants;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ContactRequestDto(
        @NotBlank(message = AppConstants.BLANK_NAME_FIELD)
        String name,

        @NotBlank(message = AppConstants.BLANK_PHONE_NUMBER_FIELD)
        String phoneNumber,

        @Email(
                message = AppConstants.INVALID_EMAIL,
                regexp = AppConstants.VALID_EMAIL_REGEX
        )
        String email

) {
}
