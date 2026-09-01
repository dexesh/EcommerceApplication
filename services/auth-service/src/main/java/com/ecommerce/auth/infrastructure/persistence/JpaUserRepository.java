package com.ecommerce.auth.infrastructure.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.auth.infrastructure.persistence.entity.UserJpaEntity;

/**
 * Spring Data repository placeholder. It will be parameterized with the persistence entity
 * when the database adapter is implemented.
 */
public interface JpaUserRepository extends JpaRepository<UserJpaEntity,UUID>{
    Optional<UserJpaEntity> findByEmail(String Email);
    boolean existsByEmail(String email);
    
}
