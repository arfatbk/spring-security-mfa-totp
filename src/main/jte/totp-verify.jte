@import org.springframework.security.web.csrf.CsrfToken
@param String username
@param CsrfToken csrf
@param String error = null

@template.layout.main(
    title = "Verify Identity",
    content = @`
        <h1>Second Factor</h1>
        <p class="page-subtitle">// TOTP verification required</p>

        @if(error != null)
            <div class="error">${error}</div>
        @endif

        <div class="info">
            Session: <strong>${username}</strong>
        </div>

        <p class="info-text">
            Enter the current 6-digit code from your authenticator application.
        </p>

        <form method="post" action="/totp/verify">
            <input type="hidden" name="${csrf.getParameterName()}" value="${csrf.getToken()}">
            <div class="form-group">
                <label for="code">TOTP Code</label>
                <input type="text"
                       id="code"
                       name="code"
                       class="code-input"
                       maxlength="6"
                       pattern="[0-9]{6}"
                       inputmode="numeric"
                       autocomplete="one-time-code"
                       placeholder="000000"
                       required
                       autofocus>
            </div>

            <button type="submit">Verify</button>
        </form>

        <div class="link" style="margin-top: 28px;">
            <form method="post" action="/logout" style="display: inline;">
                <input type="hidden" name="${csrf.getParameterName()}" value="${csrf.getToken()}">
                <button type="submit" class="btn btn-secondary" style="width: auto; padding: 12px 20px;">
                    Switch Account
                </button>
            </form>
        </div>
    `
)
