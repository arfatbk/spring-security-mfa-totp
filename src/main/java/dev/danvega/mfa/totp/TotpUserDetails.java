package dev.danvega.mfa.totp;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class TotpUserDetails implements UserDetails {

    private final User user;

    TotpUserDetails(User user) {
        this.user = user;
    }

    User getUser() {
        return user;
    }

    public boolean isMfaEnabled() {
        return user.isMfaEnabled();
    }

    public String getTotpSecret() {
        return user.getTotpSecret();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }
}
