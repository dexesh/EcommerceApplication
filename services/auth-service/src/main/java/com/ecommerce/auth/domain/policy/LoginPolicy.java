package com.ecommerce.auth.domain.policy;

import java.time.Duration;
import java.util.Objects;

public record LoginPolicy(int maximumFailedAttempts, Duration lockDuration) {

    public static final int DEFAULT_MAXIMUM_FAILED_ATTEMPTS = 5;
    public static final Duration DEFAULT_LOCK_DURATION = Duration.ofMinutes(15);

    public LoginPolicy {
        if (maximumFailedAttempts < 1) {
            throw new IllegalArgumentException("Maximum failed attempts must be positive");
        }
        Objects.requireNonNull(lockDuration, "Lock duration is required");
        if (lockDuration.isZero() || lockDuration.isNegative()) {
            throw new IllegalArgumentException("Lock duration must be positive");
        }
    }

    public static LoginPolicy defaults() {
        return new LoginPolicy(DEFAULT_MAXIMUM_FAILED_ATTEMPTS, DEFAULT_LOCK_DURATION);
    }
}
