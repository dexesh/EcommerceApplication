package com.ecommerce.auth.infrastructure.persistence.entity;

import java.util.UUID;

import com.ecommerce.auth.domain.model.UserStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class UserJpaEntity {
    @Id
    private UUID id;
    
    @Column(
        nullable = false,
        unique = true
    )
    private String email; 
    public UserJpaEntity() {
    }

    @Column(
        name = "password_hash",
        nullable = false
    )
    private String passwordHash;
 
    @Enumerated(EnumType.STRING)
    private UserStatus status;


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    
}
