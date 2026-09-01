package com.ecommerce.auth.application.result;

import java.time.Instant;
import java.util.Objects;

public record AuthenticationResult(
        String accessToken,
        String refreshToken,
        Instant accessTokenExpiresAt,
        Instant refreshTokenExpiresAt
) {

    public AuthenticationResult {
        if (accessToken == null || accessToken.isBlank()) {
            throw new IllegalArgumentException("Access token is required");
        }
        if (refreshToken == null || refreshToken.isBlank()) {
            throw new IllegalArgumentException("Refresh token is required");
        }
        Objects.requireNonNull(accessTokenExpiresAt, "Access-token expiry is required");
        Objects.requireNonNull(refreshTokenExpiresAt, "Refresh-token expiry is required");
    }
}
