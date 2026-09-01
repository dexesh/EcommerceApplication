package com.ecommerce.auth.application.port.out;

import com.ecommerce.auth.application.result.AuthenticationResult;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.model.UserId;

public interface TokenService {

    AuthenticationResult issueTokens(User user);

    String createEmailVerificationToken(User user);

    UserId verifyEmailVerificationToken(String token);
}
