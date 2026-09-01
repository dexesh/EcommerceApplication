package com.ecommerce.auth.infrastructure.web;

import com.ecommerce.auth.domain.exception.EmailAlreadyExistsException;
import com.ecommerce.auth.domain.exception.PasswordSizeNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class ApiExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    ProblemDetail handleEmailAlreadyExists(EmailAlreadyExistsException exception) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                exception.getMessage()
        );
        problem.setTitle("Email already registered");
        problem.setProperty("code", "EMAIL_ALREADY_EXISTS");
        return problem;
    }

    @ExceptionHandler(PasswordSizeNotValidException.class)
    ProblemDetail handleInvalidPasswordSize(PasswordSizeNotValidException exception) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                exception.getMessage()
        );
        problem.setTitle("Password does not meet policy");
        problem.setProperty("code", "INVALID_PASSWORD");
        problem.setProperty("minimumLength", exception.minimumLength());
        return problem;
    }
}
