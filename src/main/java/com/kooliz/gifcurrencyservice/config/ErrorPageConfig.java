package com.kooliz.gifcurrencyservice.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error/404");
        ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500");
        registry.addErrorPages(page404);
        registry.addErrorPages(page500);
    }
}
