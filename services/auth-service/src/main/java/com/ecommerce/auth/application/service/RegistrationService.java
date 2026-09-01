package com.ecommerce.auth.application.service;

import com.ecommerce.auth.application.command.RegisterUserCommand;
import com.ecommerce.auth.application.port.in.RegisterUserUseCase;
import com.ecommerce.auth.application.port.out.PasswordEncoder;
import com.ecommerce.auth.application.port.out.UserRepository;
import com.ecommerce.auth.domain.exception.EmailAlreadyExistsException;
import com.ecommerce.auth.domain.model.Email;
import com.ecommerce.auth.domain.model.Role;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.model.UserId;
import com.ecommerce.auth.domain.policy.PasswordPolicy;

public final class RegistrationService implements RegisterUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordPolicy passwordPolicy;

    public RegistrationService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            PasswordPolicy passwordPolicy
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordPolicy = passwordPolicy;
    }

    @Override
    public UserId register(RegisterUserCommand command) {
        passwordPolicy.validate(command.password());

        Email email = new Email(command.email());
        if (userRepository.existsByEmail(email)) {
            throw new EmailAlreadyExistsException(email);
        }

        String passwordHash = passwordEncoder.encode(command.password());

        User user = new User(UserId.generate(), email, passwordHash);
        user.addRole(Role.CUSTOMER);

        User savedUser = userRepository.save(user);
        return savedUser.id();
    }
}
