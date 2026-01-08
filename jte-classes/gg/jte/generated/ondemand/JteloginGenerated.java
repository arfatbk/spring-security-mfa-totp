package gg.jte.generated.ondemand;
import org.springframework.security.web.csrf.CsrfToken;
@SuppressWarnings("unchecked")
public final class JteloginGenerated {
	public static final String JTE_NAME = "login.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,1,1,1,5,5,8,8,12,12,13,13,13,14,14,16,16,17,17,17,18,18,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,21,38,38,38,39,39,39,1,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String error, String message, CsrfToken csrf) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, "Sign In", new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n        <h1>Authentication</h1>\n        <p class=\"page-subtitle\">// Enter credentials</p>\n\n        ");
				if (error != null) {
					jteOutput.writeContent("\n            <div class=\"error\">");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(error);
					jteOutput.writeContent("</div>\n        ");
				}
				jteOutput.writeContent("\n\n        ");
				if (message != null) {
					jteOutput.writeContent("\n            <div class=\"success\">");
					jteOutput.setContext("div", null);
					jteOutput.writeUserContent(message);
					jteOutput.writeContent("</div>\n        ");
				}
				jteOutput.writeContent("\n\n        <form method=\"post\" action=\"/login\">\n            <input type=\"hidden\"");
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
				jteOutput.writeContent(">\n            <div class=\"form-group\">\n                <label for=\"username\">Username</label>\n                <input type=\"text\" id=\"username\" name=\"username\" placeholder=\"Enter username\" required autofocus>\n            </div>\n\n            <div class=\"form-group\">\n                <label for=\"password\">Password</label>\n                <input type=\"password\" id=\"password\" name=\"password\" placeholder=\"Enter password\" required>\n            </div>\n\n            <button type=\"submit\">Authenticate</button>\n        </form>\n\n        <div class=\"link\">\n            <a href=\"/\">&larr; Back</a>\n        </div>\n    ");
			}
		});
		jteOutput.writeContent("\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String error = (String)params.getOrDefault("error", null);
		String message = (String)params.getOrDefault("message", null);
		CsrfToken csrf = (CsrfToken)params.get("csrf");
		render(jteOutput, jteHtmlInterceptor, error, message, csrf);
	}
}
