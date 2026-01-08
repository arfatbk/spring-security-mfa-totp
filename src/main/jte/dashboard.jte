@import org.springframework.security.web.csrf.CsrfToken
@param String username
@param CsrfToken csrf

@template.layout.main(
    title = "Dashboard",
    content = @`
        <h1>Secure Area</h1>
        <p class="page-subtitle">// Access granted</p>

        <div class="success">
            MFA verification complete
        </div>

        <p class="info-text">
            Authenticated as <strong>${username}</strong>. Full access to protected resources enabled.
        </p>

        <div class="status-box">
            <h3>Auth Status</h3>
            <div class="status-item">
                <span class="check">&#10004;</span>
                <span>Factor 1: Credentials verified</span>
            </div>
            <div class="status-item">
                <span class="check">&#10004;</span>
                <span>Factor 2: TOTP confirmed</span>
            </div>
        </div>

        <p class="info-text" style="font-size: 12px; color: var(--text-muted);">
            This resource requires <code>FACTOR_SECOND</code> authority.
        </p>

        <form method="post" action="/logout">
            <input type="hidden" name="${csrf.getParameterName()}" value="${csrf.getToken()}">
            <button type="submit">End Session</button>
        </form>
    `
)
