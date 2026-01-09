package dev.danvega.mfa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {

    private static final Logger log = LoggerFactory.getLogger(ErrorControllerAdvice.class);

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(Model model, AccessDeniedException e) {

        model.addAttribute("message", "Access Denied: You do not have permission to access this page.");
        model.addAttribute("status", 403);
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneralError(Model model, Exception e) {
        log.error(e.getMessage(), e);
        model.addAttribute("message", "Something went wrong. Please try again later.");
        model.addAttribute("status", 500);
        return "error";
    }

}
