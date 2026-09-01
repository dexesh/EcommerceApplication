package com.ecommerce.auth.application.port.in;

import com.ecommerce.auth.application.command.RegisterUserCommand;
import com.ecommerce.auth.domain.model.UserId;

public interface RegisterUserUseCase {

    UserId register(RegisterUserCommand command);
}
