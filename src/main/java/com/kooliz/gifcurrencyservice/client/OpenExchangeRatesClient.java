package com.kooliz.gifcurrencyservice.client;

import com.kooliz.gifcurrencyservice.response.CurrencyResponse;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "openExchangeRatesClient", url = "${openexchangerates.method}")
public interface OpenExchangeRatesClient {

    @GetMapping("/{date}.json")
    CurrencyResponse getRatesByDate(@PathVariable String date, @RequestParam(value = "app_id") String appId);
}