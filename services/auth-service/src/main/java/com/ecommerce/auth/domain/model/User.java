package com.ecommerce.auth.domain.model;

import com.ecommerce.auth.domain.exception.UserLockedException;
import com.ecommerce.auth.domain.policy.LoginPolicy;

import java.io.ObjectInputFilter.Status;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class User {

    private final UserId id;
    private final Email email;
    private String passwordHash;
    private UserStatus status;
    private Set<Role> roles=new HashSet<>();

    public User(
            UserId id,
            Email email,
            String passwordHash
    ) {
        this.id = Objects.requireNonNull(id, "User ID is required");
        this.email = Objects.requireNonNull(email, "Email is required");
        this.passwordHash=passwordHash;
        this.roles = new HashSet<>(roles);
        this.status = UserStatus.ACTIVE;
    }

public boolean canLogin(){
    return status==UserStatus.ACTIVE;
}
public void lock(){
    this.status=UserStatus.LOCKED;
}
public UserStatus status(){
    return status;
}
public void disable(){
    this.status=UserStatus.DISABLED;
}
public void addRole(Role role) {
    roles.add(role);
}

public boolean hasRole(Role role) {
    return roles.contains(role);
}
    public UserId id() {
        return id;
    }

    public Email email() {
        return email;
    }

    public String passwordHash() {
        return passwordHash;
    }
     public Set<Role> roles() {
        return Set.copyOf(roles);
    }
}
