package com.study.my.finalspring.admission.controller;

import com.study.my.finalspring.admission.util.exceptions.ErrorInfo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionHandlingController {

    @ExceptionHandler(Throwable.class)
    public String catchException(HttpServletRequest req, Throwable ex, Model model) {
        ErrorInfo errorInfo = new ErrorInfo(req.getRequestURL().toString(), ex, getRootCause(ex));
        model.addAttribute("errorinfo", errorInfo);
        return "customerror";
    }

    private Throwable getRootCause(Throwable t) {
        Throwable result = t;
        Throwable cause;

        while (null != (cause = result.getCause()) && (result != cause)) {
            result = cause;
        }
        return result;
    }
}
