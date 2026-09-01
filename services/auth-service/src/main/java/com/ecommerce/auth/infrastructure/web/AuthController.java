package com.ecommerce.auth.infrastructure.web;

import com.ecommerce.auth.application.command.RegisterUserCommand;
import com.ecommerce.auth.application.port.in.RegisterUserUseCase;
import com.ecommerce.auth.domain.model.UserId;
import com.ecommerce.auth.infrastructure.web.request.RegisterRequest;
import com.ecommerce.auth.infrastructure.web.response.RegisterResponse;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
public final class AuthController {
    private final RegisterUserUseCase registerUserUseCase;
    public AuthController(RegisterUserUseCase registerUserUseCase){
        this.registerUserUseCase=registerUserUseCase;
    }
    
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest registerRequest) {
        UserId userId = registerUserUseCase.register(
                new RegisterUserCommand(registerRequest.email(), registerRequest.passWord())
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(new RegisterResponse(userId.value()));
    }
}
