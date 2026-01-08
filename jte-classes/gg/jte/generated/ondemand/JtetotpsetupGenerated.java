package gg.jte.generated.ondemand;
import org.springframework.security.web.csrf.CsrfToken;
@SuppressWarnings("unchecked")
public final class JtetotpsetupGenerated {
	public static final String JTE_NAME = "totp-setup.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,1,7,7,10,10,14,14,15,15,15,16,16,23,23,23,23,23,23,23,23,23,27,27,27,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,30,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,56,56,56,57,57,57,1,2,3,4,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String qrCode, String secret, String username, CsrfToken csrf, String error) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, "Set Up Authenticator", new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n        <h1>Configure TOTP</h1>\n        <p class=\"page-subtitle\">// Step 1: Link authenticator app</p>\n\n        ");
				if (error != null) {
					jteOutput.writeContent("\n            <div class=\"error\">");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(error);
					jteOutput.writeContent("</div>\n        ");
				}
				jteOutput.writeContent("\n\n        <p class=\"info-text\">\n            Scan the QR code below with your authenticator application to register this account.\n        </p>\n\n        <div class=\"qr-container\">\n            <img");
				var __jte_html_attribute_0 = qrCode;
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" src=\"");
					jteOutput.setContext("img", "src");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("img", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" alt=\"QR Code\" width=\"180\" height=\"180\">\n        </div>\n\n        <p class=\"info-text\" style=\"font-size: 12px; color: var(--text-muted);\">Manual entry key:</p>\n        <div class=\"secret-key\">");
				jteOutput.setContext("div", null);
				jteOutput.writeUserContent(secret);
				jteOutput.writeContent("</div>\n\n        <form method=\"post\" action=\"/totp/setup/verify\">\n            <input type=\"hidden\"");
				var __jte_html_attribute_1 = csrf.getParameterName();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" name=\"");
					jteOutput.setContext("input", "name");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				var __jte_html_attribute_2 = csrf.getToken();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_2)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_2);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n            <div class=\"form-group\">\n                <label for=\"code\">Verification Code</label>\n                <input type=\"text\"\n                       id=\"code\"\n                       name=\"code\"\n                       class=\"code-input\"\n                       maxlength=\"6\"\n                       pattern=\"[0-9]{6}\"\n                       inputmode=\"numeric\"\n                       autocomplete=\"one-time-code\"\n                       placeholder=\"000000\"\n                       required>\n            </div>\n\n            <button type=\"submit\">Verify & Activate</button>\n        </form>\n\n        <div class=\"link\" style=\"margin-top: 28px;\">\n            <form method=\"post\" action=\"/logout\" style=\"display: inline;\">\n                <input type=\"hidden\"");
				var __jte_html_attribute_3 = csrf.getParameterName();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_3)) {
					jteOutput.writeContent(" name=\"");
					jteOutput.setContext("input", "name");
					jteOutput.writeUserContent(__jte_html_attribute_3);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				var __jte_html_attribute_4 = csrf.getToken();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_4)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_4);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n                <button type=\"submit\" class=\"btn btn-secondary\" style=\"width: auto; padding: 12px 20px;\">\n                    Cancel\n                </button>\n            </form>\n        </div>\n    ");
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String qrCode = (String)params.get("qrCode");
		String secret = (String)params.get("secret");
		String username = (String)params.get("username");
		CsrfToken csrf = (CsrfToken)params.get("csrf");
		String error = (String)params.getOrDefault("error", null);
		render(jteOutput, jteHtmlInterceptor, qrCode, secret, username, csrf, error);
	}
}
