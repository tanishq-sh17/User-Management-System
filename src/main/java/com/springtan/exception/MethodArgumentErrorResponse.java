package com.springtan.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class MethodArgumentErrorResponse {
    private LocalDateTime localDateTime;
    private String fieldName;
    private String message;

}
