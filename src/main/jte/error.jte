@param String message
@param Integer status

@template.layout.main(
    title = "Error",
    content = @`
        <div class="error-page">
            <h1>Error: ${status}</h1>
            <p class="error-message">${message}</p>
            <a href="/login" class="btn btn-primary">Return to login</a>
        </div>
    `
)
