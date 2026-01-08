package dev.danvega.mfa.totp;

import dev.samstevens.totp.code.*;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
class TotpService {

    private static final String ISSUER = "MFA Demo App";

    private final SecretGenerator secretGenerator;
    private final CodeVerifier codeVerifier;
    private final ZxingPngQrGenerator qrGenerator;

    TotpService() {
        this.secretGenerator = new DefaultSecretGenerator(32);
        this.qrGenerator = new ZxingPngQrGenerator();

        var codeGenerator = new DefaultCodeGenerator(HashingAlgorithm.SHA1, 6);
        var verifier = new DefaultCodeVerifier(codeGenerator, new SystemTimeProvider());
        verifier.setTimePeriod(30);
        verifier.setAllowedTimePeriodDiscrepancy(1);
        this.codeVerifier = verifier;
    }

    String generateSecret() {
        return secretGenerator.generate();
    }

    String generateQrCodeDataUri(String username, String secret) {
        var qrData = new QrData.Builder()
                .label(username)
                .secret(secret)
                .issuer(ISSUER)
                .algorithm(HashingAlgorithm.SHA1)
                .digits(6)
                .period(30)
                .build();

        try {
            byte[] imageData = qrGenerator.generate(qrData);
            return "data:image/png;base64," + Base64.getEncoder().encodeToString(imageData);
        } catch (QrGenerationException e) {
            throw new RuntimeException("Failed to generate QR code", e);
        }
    }

    boolean verifyCode(String secret, String code) {
        if (secret == null || code == null) {
            return false;
        }
        return codeVerifier.isValidCode(secret, code.replaceAll("[\\s-]", ""));
    }
}
