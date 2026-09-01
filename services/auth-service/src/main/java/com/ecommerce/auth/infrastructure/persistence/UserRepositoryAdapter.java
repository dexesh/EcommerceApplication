package com.ecommerce.auth.infrastructure.persistence;

import com.ecommerce.auth.application.port.out.UserRepository;
import com.ecommerce.auth.domain.model.Email;
import com.ecommerce.auth.domain.model.User;
import com.ecommerce.auth.domain.model.UserId;
import com.ecommerce.auth.infrastructure.persistence.entity.UserJpaEntity;
import com.ecommerce.auth.infrastructure.persistence.mapper.UserMapper;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Repository;

/**
 * Persistence adapter placeholder. No database mapping is intentionally supplied yet.
 */
@Repository
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpaUserRepository;
    private final UserMapper mapper;

    public UserRepositoryAdapter(
        JpaUserRepository jpaUserRepository,
        UserMapper mapper
    ){
        this.jpaUserRepository=jpaUserRepository;
        this.mapper=mapper;
    }
   @Override
   public boolean existsByEmail(Email email){
        return jpaUserRepository.existsByEmail(email.value());
   }
   @Override
   public User save(User user){
    UserJpaEntity entity=mapper.toEntity(user);
    return mapper.toDomain(jpaUserRepository.save(entity));
   }
   @Override
   public Optional<User> findById(UserId userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findById'");
   }
   @Override
   public Optional<User> findByEmail(Email email) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
   }
   
}
