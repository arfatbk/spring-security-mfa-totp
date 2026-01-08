package dev.danvega.mfa.totp;

class User {

    private String username;
    private String password;
    private String totpSecret;
    private boolean mfaEnabled = false;

    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }

    String getTotpSecret() {
        return totpSecret;
    }

    void setTotpSecret(String totpSecret) {
        this.totpSecret = totpSecret;
    }

    boolean isMfaEnabled() {
        return mfaEnabled;
    }

    void setMfaEnabled(boolean mfaEnabled) {
        this.mfaEnabled = mfaEnabled;
    }
}
