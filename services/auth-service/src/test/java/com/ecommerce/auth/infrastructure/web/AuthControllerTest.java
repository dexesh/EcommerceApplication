package com.ecommerce.auth.infrastructure.web;

import com.ecommerce.auth.application.command.RegisterUserCommand;
import com.ecommerce.auth.application.port.in.RegisterUserUseCase;
import com.ecommerce.auth.domain.exception.EmailAlreadyExistsException;
import com.ecommerce.auth.domain.exception.PasswordSizeNotValidException;
import com.ecommerce.auth.domain.model.Email;
import com.ecommerce.auth.domain.model.UserId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AuthControllerTest {

    private StubRegisterUserUseCase registerUserUseCase;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        registerUserUseCase = new StubRegisterUserUseCase();
        mockMvc = MockMvcBuilders
                .standaloneSetup(new AuthController(registerUserUseCase))
                .setControllerAdvice(new ApiExceptionHandler())
                .build();
    }

    @Test
    void returnsConflictWhenEmailIsAlreadyRegistered() throws Exception {
        registerUserUseCase.failure =
                new EmailAlreadyExistsException(new Email("existing@example.com"));

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "existing@example.com",
                                  "passWord": "a-strong-test-password"
                                }
                                """))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.status").value(409))
                .andExpect(jsonPath("$.title").value("Email already registered"))
                .andExpect(jsonPath("$.detail").value("An account already exists for this email!!"))
                .andExpect(jsonPath("$.code").value("EMAIL_ALREADY_EXISTS"));
    }

    @Test
    void returnsBadRequestWhenPasswordDoesNotMeetDomainPolicy() throws Exception {
        registerUserUseCase.failure = new PasswordSizeNotValidException(12);

        mockMvc.perform(post("/api/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "email": "customer@example.com",
                                  "passWord": "123"
                                }
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.title").value("Password does not meet policy"))
                .andExpect(jsonPath("$.detail").value("Password must contain at least 12 characters"))
                .andExpect(jsonPath("$.code").value("INVALID_PASSWORD"))
                .andExpect(jsonPath("$.minimumLength").value(12));
    }

    private static final class StubRegisterUserUseCase implements RegisterUserUseCase {

        private RuntimeException failure;

        @Override
        public UserId register(RegisterUserCommand command) {
            throw failure;
        }
    }
}
