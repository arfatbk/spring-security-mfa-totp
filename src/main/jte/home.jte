@template.layout.main(
    title = "MFA Demo - Home",
    content = @`
        <h1>Multi-Factor Auth</h1>
        <p class="page-subtitle">// TOTP authentication demo</p>

        <p class="info-text">
            Spring Security MFA implementation with Time-based One-Time Password support.
            Compatible with standard authenticator applications.
        </p>

        <div class="demo-users">
            <h3>Test Credentials</h3>
            <ul>
                <li>
                    <code>user</code> / <code>password</code>
                    <small>MFA enabled - requires TOTP verification</small>
                </li>
                <li>
                    <code>newuser</code> / <code>password</code>
                    <small>MFA disabled - triggers setup flow</small>
                </li>
            </ul>
        </div>

        <a href="/login" class="btn">Initialize Session</a>

        <p class="info-text" style="margin-top: 28px; font-size: 12px; color: var(--text-muted);">
            Supported: Google Authenticator, Authy, Microsoft Authenticator, 1Password
        </p>
    `
)
