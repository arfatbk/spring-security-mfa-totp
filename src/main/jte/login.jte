@import org.springframework.security.web.csrf.CsrfToken
@param String error = null
@param String message = null
@param CsrfToken csrf

@template.layout.main(
    title = "Sign In",
    content = @`
        <h1>Authentication</h1>
        <p class="page-subtitle">// Enter credentials</p>

        @if(error != null)
            <div class="error">${error}</div>
        @endif

        @if(message != null)
            <div class="success">${message}</div>
        @endif

        <form method="post" action="/login">
            <input type="hidden" name="${csrf.getParameterName()}" value="${csrf.getToken()}">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" id="username" name="username" placeholder="Enter username" required autofocus>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter password" required>
            </div>

            <button type="submit">Authenticate</button>
        </form>

        <div class="link">
            <a href="/">&larr; Back</a>
        </div>
    `
)
