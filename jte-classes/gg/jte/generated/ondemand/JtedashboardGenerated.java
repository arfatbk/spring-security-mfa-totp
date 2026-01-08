package gg.jte.generated.ondemand;
import org.springframework.security.web.csrf.CsrfToken;
@SuppressWarnings("unchecked")
public final class JtedashboardGenerated {
	public static final String JTE_NAME = "dashboard.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,1,4,4,7,7,16,16,16,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,36,39,39,39,40,40,40,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String username, CsrfToken csrf) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, "Dashboard", new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n        <h1>Secure Area</h1>\n        <p class=\"page-subtitle\">// Access granted</p>\n\n        <div class=\"success\">\n            MFA verification complete\n        </div>\n\n        <p class=\"info-text\">\n            Authenticated as <strong>");
				jteOutput.setContext("strong", null);
				jteOutput.writeUserContent(username);
				jteOutput.writeContent("</strong>. Full access to protected resources enabled.\n        </p>\n\n        <div class=\"status-box\">\n            <h3>Auth Status</h3>\n            <div class=\"status-item\">\n                <span class=\"check\">&#10004;</span>\n                <span>Factor 1: Credentials verified</span>\n            </div>\n            <div class=\"status-item\">\n                <span class=\"check\">&#10004;</span>\n                <span>Factor 2: TOTP confirmed</span>\n            </div>\n        </div>\n\n        <p class=\"info-text\" style=\"font-size: 12px; color: var(--text-muted);\">\n            This resource requires <code>FACTOR_SECOND</code> authority.\n        </p>\n\n        <form method=\"post\" action=\"/logout\">\n            <input type=\"hidden\"");
				var __jte_html_attribute_0 = csrf.getParameterName();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" name=\"");
					jteOutput.setContext("input", "name");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				var __jte_html_attribute_1 = csrf.getToken();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(">\n            <button type=\"submit\">End Session</button>\n        </form>\n    ");
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String username = (String)params.get("username");
		CsrfToken csrf = (CsrfToken)params.get("csrf");
		render(jteOutput, jteHtmlInterceptor, username, csrf);
	}
}
