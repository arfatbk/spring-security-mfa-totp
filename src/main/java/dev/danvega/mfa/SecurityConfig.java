package dev.danvega.mfa;

import dev.danvega.mfa.totp.TotpAuthenticationHandler;
import dev.danvega.mfa.totp.UserStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authorization.EnableMultiFactorAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.FactorGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMultiFactorAuthentication(authorities = {})
public class SecurityConfig {

    private final UserStore userStore;
    private final TotpAuthenticationHandler totpAuthenticationHandler;

    public SecurityConfig(UserStore userStore, TotpAuthenticationHandler totpAuthenticationHandler) {
        this.userStore = userStore;
        this.totpAuthenticationHandler = totpAuthenticationHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Public pages
                        .requestMatchers("/", "/login", "/error", "/css/**", "/js/**").permitAll()
                        // TOTP pages - only need password factor
                        .requestMatchers("/totp/**").hasAuthority(FactorGrantedAuthority.PASSWORD_AUTHORITY)
                        // Protected pages - need TOTP factor (which implies password was done first)
                        .requestMatchers("/dashboard/**").hasAuthority(TotpAuthenticationHandler.TOTP_AUTHORITY)
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .successHandler(totpAuthenticationHandler)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/?logout")
                        .permitAll()
                )
                .userDetailsService(userStore);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
