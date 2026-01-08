@import org.springframework.security.web.csrf.CsrfToken
@param String qrCode
@param String secret
@param String username
@param CsrfToken csrf
@param String error = null

@template.layout.main(
    title = "Set Up Authenticator",
    content = @`
        <h1>Configure TOTP</h1>
        <p class="page-subtitle">// Step 1: Link authenticator app</p>

        @if(error != null)
            <div class="error">${error}</div>
        @endif

        <p class="info-text">
            Scan the QR code below with your authenticator application to register this account.
        </p>

        <div class="qr-container">
            <img src="${qrCode}" alt="QR Code" width="180" height="180">
        </div>

        <p class="info-text" style="font-size: 12px; color: var(--text-muted);">Manual entry key:</p>
        <div class="secret-key">${secret}</div>

        <form method="post" action="/totp/setup/verify">
            <input type="hidden" name="${csrf.getParameterName()}" value="${csrf.getToken()}">
            <div class="form-group">
                <label for="code">Verification Code</label>
                <input type="text"
                       id="code"
                       name="code"
                       class="code-input"
                       maxlength="6"
                       pattern="[0-9]{6}"
                       inputmode="numeric"
                       autocomplete="one-time-code"
                       placeholder="000000"
                       required>
            </div>

            <button type="submit">Verify & Activate</button>
        </form>

        <div class="link" style="margin-top: 28px;">
            <form method="post" action="/logout" style="display: inline;">
                <input type="hidden" name="${csrf.getParameterName()}" value="${csrf.getToken()}">
                <button type="submit" class="btn btn-secondary" style="width: auto; padding: 12px 20px;">
                    Cancel
                </button>
            </form>
        </div>
    `
)
