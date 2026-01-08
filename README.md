# Spring Security 7 MFA with TOTP Authenticator App

A minimal, clean example of implementing Multi-Factor Authentication (MFA) in Spring Boot 4 using Spring Security 7's `@EnableMultiFactorAuthentication` with TOTP (Time-based One-Time Passwords) compatible with Google Authenticator, Authy, 1Password, and other authenticator apps.

## Quick Start

### Prerequisites
- Java 21+
- Maven 3.x

### Run the Application

```bash
mvn spring-boot:run
```

Open http://localhost:8080

## Demo Walkthrough

### Test Credentials

| Username | Password | MFA Status |
|----------|----------|------------|
| `user` | `password` | Already enabled (secret: `JBSWY3DPEHPK3PXP`) |
| `newuser` | `password` | Not configured |

### Try It Out

**Flow 1: New User MFA Setup**
1. Login with `newuser` / `password`
2. Scan the QR code with your authenticator app (or enter the secret manually)
3. Enter the 6-digit code from your app
4. You're in the dashboard with full MFA protection

**Flow 2: Existing MFA User**
1. Login with `user` / `password`
2. Enter the 6-digit code from your authenticator app
3. Access granted to dashboard

> **Tip:** For the pre-configured `user` account, add the secret `JBSWY3DPEHPK3PXP` to your authenticator app manually, or use `newuser` to scan a fresh QR code.

## How It Works

### Spring Security 7 MFA Framework

This demo uses Spring Security 7's built-in MFA support with `@EnableMultiFactorAuthentication`:

```java
@EnableMultiFactorAuthentication(authorities = {
    FactorGrantedAuthority.PASSWORD_AUTHORITY,  // Built-in: password factor
    "FACTOR_TOTP"                               // Custom: TOTP factor
})
```

### FactorGrantedAuthority

Spring Security 7 introduces `FactorGrantedAuthority` for tracking authentication factors:

- **Built-in factors**: `PASSWORD_AUTHORITY`, `OTT_AUTHORITY`, `WEBAUTHN_AUTHORITY`, `X509_AUTHORITY`
- **Custom factors**: Create with `FactorGrantedAuthority.fromFactor("TOTP")` → `FACTOR_TOTP`

### Authentication Flow

```
┌─────────────┐     ┌──────────────────┐     ┌──────────────┐     ┌───────────┐
│   Login     │────►│ FACTOR_PASSWORD  │────►│ TOTP Verify  │────►│ Dashboard │
│  (user/pw)  │     │    granted       │     │ FACTOR_TOTP  │     │ (protected)│
└─────────────┘     └──────────────────┘     └──────────────┘     └───────────┘
                           │
                           ▼
                    ┌──────────────┐
                    │  TOTP Setup  │ (if MFA not configured)
                    │  (scan QR)   │
                    └──────────────┘
```

## Project Structure

```
src/main/java/dev/danvega/mfa/
├── Application.java           # Spring Boot entry point
├── SecurityConfig.java        # @EnableMultiFactorAuthentication config
├── HomeController.java        # Home and dashboard pages
├── LoginController.java       # Login page
└── totp/                      # MFA feature package
    ├── User.java              # User model (package-private)
    ├── UserStore.java         # In-memory user storage + UserDetailsService
    ├── TotpUserDetails.java   # Spring Security user wrapper
    ├── TotpService.java       # TOTP generation & verification (package-private)
    ├── TotpController.java    # MFA setup and verification endpoints
    └── TotpAuthenticationHandler.java  # Grants FACTOR_TOTP after verification
```

## Key Files

| File | Purpose |
|------|---------|
| `SecurityConfig.java` | `@EnableMultiFactorAuthentication` with PASSWORD + TOTP factors |
| `TotpAuthenticationHandler.java` | Grants `FactorGrantedAuthority.fromFactor("TOTP")` after verification |
| `TotpService.java` | TOTP secret generation, QR codes, and code verification |
| `UserStore.java` | Stores users in memory, implements `UserDetailsService` |
| `TotpController.java` | MFA setup and verification endpoints |

## Technology Stack

- **Spring Boot 4.0** with Spring Security 7
- **`@EnableMultiFactorAuthentication`** for declarative MFA
- **`FactorGrantedAuthority`** for factor tracking
- **JTE** (Java Template Engine) for views
- **In-memory user storage** (no database required)
- **dev.samstevens.totp** library for RFC 6238 TOTP

## Spring Security 7 MFA vs Previous Approach

| Aspect | Spring Security 6 (Manual) | Spring Security 7 (Built-in) |
|--------|---------------------------|------------------------------|
| Setup | Custom authorities (`FACTOR_FIRST`, `FACTOR_SECOND`) | `@EnableMultiFactorAuthentication` |
| Factor tracking | `SimpleGrantedAuthority` | `FactorGrantedAuthority` with `issuedAt` |
| Built-in factors | None | PASSWORD, OTT, WEBAUTHN, X509 |
| Custom factors | Manual string authorities | `FactorGrantedAuthority.fromFactor()` |
| Time-based expiry | Manual implementation | Built-in `validDuration()` support |

## Production Considerations

This is a demo application. Before using in production, address the following:

### Security Enhancements

- [ ] **Add a real database** - Replace `UserStore` with JPA/JDBC persistence
- [ ] **Encrypt TOTP secrets at rest** - Don't store secrets as plain Base32
- [ ] **Add rate limiting** - Protect against brute-force attacks on the 6-digit code
- [ ] **Implement account lockout** - Lock after N failed TOTP attempts
- [ ] **Add backup/recovery codes** - Allow account recovery if authenticator is lost

### Feature Additions

- [ ] **Optional MFA** - Let users choose whether to enable MFA
- [ ] **MFA management UI** - Allow users to disable/reset their MFA
- [ ] **Remember device** - Use Spring Security 7's factor duration support
- [ ] **Multiple authenticator support** - Allow registering backup authenticators

### Infrastructure

- [ ] **HTTPS** - Always use TLS in production
- [ ] **Session management** - Configure secure session handling

## Resources

- [Spring Security 7 MFA Documentation](https://docs.spring.io/spring-security/reference/servlet/authentication/mfa.html)
- [Spring Security 7 MFA Blog Post](https://spring.io/blog/2025/10/21/multi-factor-authentication-in-spring-security-7/)
- [FactorGrantedAuthority API](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/authority/FactorGrantedAuthority.html)
- [RFC 6238 - TOTP Algorithm](https://datatracker.ietf.org/doc/html/rfc6238)
- [samstevens/totp Library](https://github.com/samstevens/java-totp)
- [JTE Template Engine](https://jte.gg/)

## License

MIT
