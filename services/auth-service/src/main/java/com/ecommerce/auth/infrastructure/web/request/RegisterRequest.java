package com.ecommerce.auth.infrastructure.web.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

//Handeles Api level validation
public record RegisterRequest (
    @NotBlank
    @Email
    String email,
    @NotBlank
    String passWord
){}
