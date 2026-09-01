package com.ecommerce.auth.domain.exception;

import com.ecommerce.auth.domain.model.Email;

public final class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(Email email) {
        super("An account already exists for this email!!");
    }
}
