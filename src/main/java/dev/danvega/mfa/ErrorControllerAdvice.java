package dev.danvega.mfa;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorControllerAdvice {


    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDenied(Model model, AccessDeniedException e) {

        model.addAttribute("message", "Access Denied: You do not have permission to access this page.");
        model.addAttribute("status", 403);
        return "error";
    }

}
