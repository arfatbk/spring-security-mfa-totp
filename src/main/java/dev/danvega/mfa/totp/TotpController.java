package dev.danvega.mfa.totp;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/totp")
public class TotpController {

    private final TotpService totpService;
    private final TotpAuthenticationHandler authHandler;

    TotpController(TotpService totpService, TotpAuthenticationHandler authHandler) {
        this.totpService = totpService;
        this.authHandler = authHandler;
    }

    @GetMapping("/setup")
    String showSetup(CsrfToken csrfToken, Model model) {
        var userDetails = getCurrentUser();
        var user = userDetails.getUser();

        if (user.getTotpSecret() == null) {
            user.setTotpSecret(totpService.generateSecret());
        }

        model.addAttribute("qrCode", totpService.generateQrCodeDataUri(user.getUsername(), user.getTotpSecret()));
        model.addAttribute("secret", user.getTotpSecret());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("csrf", csrfToken);
        return "totp-setup";
    }

    @PostMapping("/setup/verify")
    String verifySetup(@RequestParam("code") String code, CsrfToken csrfToken, Model model) {
        var userDetails = getCurrentUser();
        var user = userDetails.getUser();

        if (totpService.verifyCode(user.getTotpSecret(), code)) {
            user.setMfaEnabled(true);
            authHandler.grantTotpFactor(SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid code. Please try again.");
        model.addAttribute("qrCode", totpService.generateQrCodeDataUri(user.getUsername(), user.getTotpSecret()));
        model.addAttribute("secret", user.getTotpSecret());
        model.addAttribute("username", user.getUsername());
        model.addAttribute("csrf", csrfToken);
        return "totp-setup";
    }

    @GetMapping("/verify")
    String showVerify(CsrfToken csrfToken, Model model) {
        model.addAttribute("username", getCurrentUser().getUsername());
        model.addAttribute("csrf", csrfToken);
        return "totp-verify";
    }

    @PostMapping("/verify")
    String verify(@RequestParam("code") String code, CsrfToken csrfToken, Model model) {
        var userDetails = getCurrentUser();

        if (totpService.verifyCode(userDetails.getTotpSecret(), code)) {
            authHandler.grantTotpFactor(SecurityContextHolder.getContext().getAuthentication());
            return "redirect:/dashboard";
        }

        model.addAttribute("error", "Invalid code. Please try again.");
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("csrf", csrfToken);
        return "totp-verify";
    }

    private TotpUserDetails getCurrentUser() {
        return (TotpUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
