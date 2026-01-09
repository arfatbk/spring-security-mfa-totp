package dev.danvega.mfa.totp;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class TotpAuthenticationHandler implements AuthenticationSuccessHandler {

    public static final String TOTP_AUTHORITY = "FACTOR_TOTP";

    @Override
    public void onAuthenticationSuccess(@NonNull HttpServletRequest request,
                                        @NonNull HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        var userDetails = (TotpUserDetails) authentication.getPrincipal();

        if (userDetails.isMfaEnabled() && userDetails.getTotpSecret() != null) {
            response.sendRedirect("/totp/verify");
        } else {
            response.sendRedirect("/totp/setup");
        }
    }

    public void grantTotpFactor(Authentication authentication) {
        var authorities = new ArrayList<GrantedAuthority>(authentication.getAuthorities());
        authorities.add(FactorGrantedAuthority.fromFactor("TOTP"));

        var newAuth = new UsernamePasswordAuthenticationToken(
                authentication.getPrincipal(),
                authentication.getCredentials(),
                authorities
        );
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}
