# EcommerceApplication

Monorepo for the EcommerceApplication microservices and shared deployment infrastructure.

## Services

- `services/auth-service` — authentication and account-security service

## Build the auth service

```powershell
cd services/auth-service
$env:DB_PASSWORD = "your-local-postgres-password"
./mvnw.cmd test
```

Each future microservice should be created as a separate directory under `services/`.

