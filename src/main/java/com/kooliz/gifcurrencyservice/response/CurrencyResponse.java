package com.kooliz.gifcurrencyservice.response;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CurrencyResponse {

    private String disclaimer;

    private String license;

    private int timestamp;

    private String base;

    private Map<String, Double> rates;
}
