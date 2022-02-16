package com.kooliz.gifcurrencyservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorsController {

    @GetMapping("/error/404")
    public String get404Page() {
        return "/errors/error_404";
    }

    @GetMapping("/error/500")
    public String get500Page() {
        return "/errors/error_500";
    }
}
