package com.ecommerce.auth.domain.policy;

import com.ecommerce.auth.domain.exception.PasswordSizeNotValidException;

public final class PasswordPolicy {

    public static final int DEFAULT_MINIMUM_LENGTH = 12;

    private final int minimumLength;

    public PasswordPolicy() {
        this(DEFAULT_MINIMUM_LENGTH);
    }

    public PasswordPolicy(int minimumLength) {
        if (minimumLength < 8) {
            throw new IllegalArgumentException("Minimum password length cannot be less than 8");
        }
        this.minimumLength = minimumLength;
    }

    public void validate(String rawPassword) {
        if (rawPassword == null || rawPassword.length() < minimumLength) {
            throw new PasswordSizeNotValidException(minimumLength);
        }
    }

    public int minimumLength() {
        return minimumLength;
    }
}
