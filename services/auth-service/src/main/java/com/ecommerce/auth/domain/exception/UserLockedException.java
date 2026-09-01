package com.ecommerce.auth.domain.exception;

import com.ecommerce.auth.domain.model.UserId;

import java.time.Instant;

public final class UserLockedException extends RuntimeException {

    private final UserId userId;
    private final Instant lockedUntil;

    public UserLockedException(UserId userId, Instant lockedUntil) {
        super(lockedUntil == null
                ? "User account is locked"
                : "User account is locked until " + lockedUntil);
        this.userId = userId;
        this.lockedUntil = lockedUntil;
    }

    public UserId userId() {
        return userId;
    }

    public Instant lockedUntil() {
        return lockedUntil;
    }
}
