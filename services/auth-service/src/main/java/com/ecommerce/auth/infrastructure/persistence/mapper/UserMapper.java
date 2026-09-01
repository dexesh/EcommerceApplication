package com.ecommerce.auth.infrastructure.persistence.mapper;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.ecommerce.auth.domain.model.Email;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.model.UserId;
import com.ecommerce.auth.infrastructure.persistence.entity.UserJpaEntity;

@Component
public class UserMapper {
    public UserJpaEntity toEntity(User user){
        UserJpaEntity entity=new UserJpaEntity();
        entity.setId(user.id().value());
        entity.setEmail(user.email().value());
        entity.setPasswordHash(user.passwordHash());
        entity.setStatus(user.status());
        return entity;

        
    }
    public User toDomain(UserJpaEntity entity){
    return new User(
            new UserId(entity.getId()),new Email(entity.getEmail()) ,entity.getPasswordHash());

    }
    
}
