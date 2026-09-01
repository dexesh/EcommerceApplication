package com.ecommerce.auth.infrastructure.security;

import com.ecommerce.auth.application.port.out.TokenService;
import com.ecommerce.auth.application.result.AuthenticationResult;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.model.UserId;

/**
 * JWT adapter placeholder. Signing, validation, claims, and key rotation are not implemented.
 */
public final class JwtTokenService implements TokenService {

    @Override
    public AuthenticationResult issueTokens(User user) {
        throw notImplemented();
    }

    @Override
    public String createEmailVerificationToken(User user) {
        throw notImplemented();
    }

    @Override
    public UserId verifyEmailVerificationToken(String token) {
        throw notImplemented();
    }

    private static UnsupportedOperationException notImplemented() {
        return new UnsupportedOperationException("JWT token handling is not implemented");
    }
}
