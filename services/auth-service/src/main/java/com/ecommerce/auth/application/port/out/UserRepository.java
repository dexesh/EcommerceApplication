package com.ecommerce.auth.application.port.out;

import com.ecommerce.auth.domain.model.Email;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.model.UserId;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(UserId userId);

    Optional<User> findByEmail(Email email);

    boolean existsByEmail(Email email);

    User save(User user);
}
