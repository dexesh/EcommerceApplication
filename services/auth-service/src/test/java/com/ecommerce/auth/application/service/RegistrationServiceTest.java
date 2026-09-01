package com.ecommerce.auth.application.service;

import com.ecommerce.auth.application.command.RegisterUserCommand;
import com.ecommerce.auth.application.port.out.PasswordEncoder;
import com.ecommerce.auth.application.port.out.UserRepository;
import com.ecommerce.auth.domain.exception.EmailAlreadyExistsException;
import com.ecommerce.auth.domain.exception.PasswordSizeNotValidException;
import com.ecommerce.auth.domain.model.Email;
import com.ecommerce.auth.domain.model.Role;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.model.UserId;
import com.ecommerce.auth.domain.policy.PasswordPolicy;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RegistrationServiceTest {

    @Test
    void registersUserWithNormalizedEmailHashedPasswordAndCustomerRole() {
        CapturingUserRepository userRepository = new CapturingUserRepository(false);
        RecordingPasswordEncoder passwordEncoder = new RecordingPasswordEncoder();
        RegistrationService registrationService = new RegistrationService(
                userRepository,
                passwordEncoder,
                new PasswordPolicy()
        );

        UserId registeredUserId = registrationService.register(
                new RegisterUserCommand(
                        "  Customer@Example.COM  ",
                        "a-strong-test-password"
                )
        );

        User savedUser = userRepository.savedUser;
        assertNotNull(savedUser);
        assertEquals(savedUser.id(), registeredUserId);
        assertEquals("customer@example.com", userRepository.checkedEmail.value());
        assertEquals("customer@example.com", savedUser.email().value());
        assertEquals("a-strong-test-password", passwordEncoder.rawPassword);
        assertEquals("encoded:a-strong-test-password", savedUser.passwordHash());
        assertTrue(savedUser.hasRole(Role.CUSTOMER));
    }

    @Test
    void rejectsRegistrationWhenEmailAlreadyExists() {
        CapturingUserRepository userRepository = new CapturingUserRepository(true);
        RecordingPasswordEncoder passwordEncoder = new RecordingPasswordEncoder();
        RegistrationService registrationService = new RegistrationService(
                userRepository,
                passwordEncoder,
                new PasswordPolicy()
        );

        assertThrows(
                EmailAlreadyExistsException.class,
                () -> registrationService.register(
                        new RegisterUserCommand(
                                "existing@example.com",
                                "a-strong-test-password"
                        )
                )
        );

        assertEquals("existing@example.com", userRepository.checkedEmail.value());
        assertNull(userRepository.savedUser);
        assertNull(passwordEncoder.rawPassword);
    }

    @Test
    void rejectsPasswordThatDoesNotMeetDomainPolicy() {
        CapturingUserRepository userRepository = new CapturingUserRepository(false);
        RecordingPasswordEncoder passwordEncoder = new RecordingPasswordEncoder();
        RegistrationService registrationService = new RegistrationService(
                userRepository,
                passwordEncoder,
                new PasswordPolicy()
        );

        PasswordSizeNotValidException exception = assertThrows(
                PasswordSizeNotValidException.class,
                () -> registrationService.register(
                        new RegisterUserCommand("customer@example.com", "123")
                )
        );

        assertEquals(12, exception.minimumLength());
        assertNull(userRepository.checkedEmail);
        assertNull(userRepository.savedUser);
        assertNull(passwordEncoder.rawPassword);
    }

    private static final class CapturingUserRepository implements UserRepository {

        private final boolean emailExists;
        private Email checkedEmail;
        private User savedUser;

        private CapturingUserRepository(boolean emailExists) {
            this.emailExists = emailExists;
        }

        @Override
        public Optional<User> findById(UserId userId) {
            return Optional.empty();
        }

        @Override
        public Optional<User> findByEmail(Email email) {
            return Optional.empty();
        }

        @Override
        public boolean existsByEmail(Email email) {
            checkedEmail = email;
            return emailExists;
        }

        @Override
        public User save(User user) {
            savedUser = user;
            return user;
        }
    }

    private static final class RecordingPasswordEncoder implements PasswordEncoder {

        private String rawPassword;

        @Override
        public String encode(String rawPassword) {
            this.rawPassword = rawPassword;
            return "encoded:" + rawPassword;
        }

        @Override
        public boolean matches(String rawPassword, String encodedPassword) {
            return ("encoded:" + rawPassword).equals(encodedPassword);
        }
    }
}
