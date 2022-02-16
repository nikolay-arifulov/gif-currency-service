package com.kooliz.gifcurrencyservice.controller;

import com.kooliz.gifcurrencyservice.service.GifCurrencyService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@Controller
public class GifCurrencyController {

    private final GifCurrencyService gifCurrencyService;

    @GetMapping("/currency/check")
    public String getGif(@RequestParam String currency, Model model) {
        String gifUrl = gifCurrencyService.getGifUrlByCurrency(currency);
        model.addAttribute("gifUrl", gifUrl);
        return "gif";
    }
}

