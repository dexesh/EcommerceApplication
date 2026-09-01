package com.ecommerce.auth.application.port.out;

import com.ecommerce.auth.domain.model.UserId;

import java.time.Instant;
import java.util.Optional;

public interface RefreshTokenRepository {

    void save(String token, UserId userId, Instant expiresAt);

    Optional<UserId> findActiveUserId(String token, Instant now);

    void revoke(String token);

    void revokeAllForUser(UserId userId);
}
