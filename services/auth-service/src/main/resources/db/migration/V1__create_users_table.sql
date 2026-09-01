CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(320) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    status VARCHAR(32) NOT NULL,

    CONSTRAINT uk_users_email UNIQUE (email),
    CONSTRAINT chk_users_status
        CHECK (status IN ('ACTIVE', 'LOCKED', 'DISABLED'))
);