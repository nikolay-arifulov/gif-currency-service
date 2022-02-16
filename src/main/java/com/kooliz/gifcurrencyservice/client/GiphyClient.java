package com.kooliz.gifcurrencyservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "giphyClient", url = "${giphy.url}")
public interface GiphyClient {

    @GetMapping("/random")
    String getResponseByTag(@RequestParam String apiKey, @RequestParam String tag);
}