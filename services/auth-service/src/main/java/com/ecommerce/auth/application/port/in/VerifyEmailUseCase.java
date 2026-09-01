package com.ecommerce.auth.application.port.in;

public interface VerifyEmailUseCase {

    void verifyEmail(String verificationToken);
}
