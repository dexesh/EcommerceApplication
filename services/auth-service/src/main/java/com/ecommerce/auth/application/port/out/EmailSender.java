package com.ecommerce.auth.application.port.out;

import com.ecommerce.auth.domain.model.Email;

public interface EmailSender {

    void sendVerificationEmail(Email recipient, String verificationToken);
}
