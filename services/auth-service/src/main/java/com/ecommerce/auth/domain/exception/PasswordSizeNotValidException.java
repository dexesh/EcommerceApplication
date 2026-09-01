package com.ecommerce.auth.domain.exception;

public final class PasswordSizeNotValidException extends RuntimeException {

    private final int minimumLength;

    public PasswordSizeNotValidException(int minimumLength) {
        super("Password must contain at least " + minimumLength + " characters");
        this.minimumLength = minimumLength;
    }

    public int minimumLength() {
        return minimumLength;
    }
}
