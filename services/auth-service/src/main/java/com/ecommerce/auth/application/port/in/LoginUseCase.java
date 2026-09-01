package com.ecommerce.auth.application.port.in;

import com.ecommerce.auth.application.command.LoginCommand;
import com.ecommerce.auth.application.result.AuthenticationResult;

public interface LoginUseCase {

    AuthenticationResult login(LoginCommand command);
}
