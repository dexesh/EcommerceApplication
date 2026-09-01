package com.ecommerce.auth.infrastructure.security;

import com.ecommerce.auth.application.port.out.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Objects;
@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoder {
   
   private BCryptPasswordEncoder encoder;
    public BCryptPasswordEncoderAdapter() {
        this.encoder=new BCryptPasswordEncoder();
    }
    @Override
    public String encode(String rawPassword) {
        // TODO Auto-generated method stub
        return encoder.encode(rawPassword);
    }
    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        // TODO Auto-generated method stub
        return encoder.matches(rawPassword, encodedPassword);
    }

}
