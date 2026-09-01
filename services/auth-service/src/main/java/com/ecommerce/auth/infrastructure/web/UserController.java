package com.ecommerce.auth.infrastructure.web;

import com.ecommerce.auth.application.port.in.RegisterUserUseCase;
import com.ecommerce.auth.application.port.in.VerifyEmailUseCase;

import java.util.Objects;

/**
 * HTTP adapter placeholder. Endpoint mappings will be added with the web API implementation.
 */
public final class UserController {

    private final RegisterUserUseCase registerUserUseCase;
    private final VerifyEmailUseCase verifyEmailUseCase;

    public UserController(
            RegisterUserUseCase registerUserUseCase,
            VerifyEmailUseCase verifyEmailUseCase
    ) {
        this.registerUserUseCase = Objects.requireNonNull(registerUserUseCase);
        this.verifyEmailUseCase = Objects.requireNonNull(verifyEmailUseCase);
    }
}
