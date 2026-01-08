package gg.jte.generated.ondemand;
@SuppressWarnings("unchecked")
public final class JtehomeGenerated {
	public static final String JTE_NAME = "home.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,0,3,3,31,31,31,32,32,32,32,32,32};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, "MFA Demo - Home", new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n        <h1>Multi-Factor Auth</h1>\n        <p class=\"page-subtitle\">// TOTP authentication demo</p>\n\n        <p class=\"info-text\">\n            Spring Security MFA implementation with Time-based One-Time Password support.\n            Compatible with standard authenticator applications.\n        </p>\n\n        <div class=\"demo-users\">\n            <h3>Test Credentials</h3>\n            <ul>\n                <li>\n                    <code>user</code> / <code>password</code>\n                    <small>MFA enabled - requires TOTP verification</small>\n                </li>\n                <li>\n                    <code>newuser</code> / <code>password</code>\n                    <small>MFA disabled - triggers setup flow</small>\n                </li>\n            </ul>\n        </div>\n\n        <a href=\"/login\" class=\"btn\">Initialize Session</a>\n\n        <p class=\"info-text\" style=\"margin-top: 28px; font-size: 12px; color: var(--text-muted);\">\n            Supported: Google Authenticator, Authy, Microsoft Authenticator, 1Password\n        </p>\n    ");
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
