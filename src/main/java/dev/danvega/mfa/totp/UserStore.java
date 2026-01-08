package dev.danvega.mfa.totp;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserStore implements UserDetailsService {

    // BCrypt hash for "password"
    private static final String PASSWORD_HASH = "$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG";

    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();

    public UserStore() {
        // Pre-configured user with MFA already enabled
        var user = new User("user", PASSWORD_HASH);
        user.setTotpSecret("JBSWY3DPEHPK3PXP");
        user.setMfaEnabled(true);
        users.put(user.getUsername(), user);

        // New user without MFA configured
        users.put("newuser", new User("newuser", PASSWORD_HASH));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findByUsername(username)
                .map(TotpUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }

    Optional<User> findByUsername(String username) {
        return Optional.ofNullable(users.get(username));
    }
}
