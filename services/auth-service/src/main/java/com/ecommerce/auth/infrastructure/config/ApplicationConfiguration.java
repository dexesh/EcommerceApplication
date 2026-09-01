package com.ecommerce.auth.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ecommerce.auth.application.port.in.RegisterUserUseCase;
import com.ecommerce.auth.application.port.out.PasswordEncoder;
import com.ecommerce.auth.application.port.out.UserRepository;
import com.ecommerce.auth.application.service.RegistrationService;
import com.ecommerce.auth.domain.policy.PasswordPolicy;

@Configuration
public class ApplicationConfiguration {
    @Bean
    RegisterUserUseCase registerUserUseCase(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder,
        PasswordPolicy passwordPolicy
    ){
        return new RegistrationService(userRepository, passwordEncoder, passwordPolicy);
    }

    @Bean
    PasswordPolicy passwordPolicy() {
        return new PasswordPolicy();
    }
}