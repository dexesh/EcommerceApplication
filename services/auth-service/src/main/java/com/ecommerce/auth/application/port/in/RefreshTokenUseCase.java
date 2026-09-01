package com.ecommerce.auth.application.port.in;

import com.ecommerce.auth.application.result.AuthenticationResult;

public interface RefreshTokenUseCase {

    AuthenticationResult refresh(String refreshToken);
}
