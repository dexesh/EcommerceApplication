package com.ecommerce.auth.application.command;

public record RegisterUserCommand(String email, String password) {

    public RegisterUserCommand {}
}
