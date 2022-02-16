package com.kooliz.gifcurrencyservice.service;

import com.kooliz.gifcurrencyservice.client.GiphyClient;
import com.kooliz.gifcurrencyservice.client.OpenExchangeRatesClient;
import com.kooliz.gifcurrencyservice.exception.CurrencyNotFoundException;
import com.kooliz.gifcurrencyservice.exception.ServerException;
import com.kooliz.gifcurrencyservice.response.CurrencyResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class GifCurrencyServiceImpl implements GifCurrencyService {

    private final OpenExchangeRatesClient openExchangeRatesClient;

    private final GiphyClient giphyClient;

    private final ObjectMapper objectMapper;

    @Value("${currency.base}")
    private String baseCurrency;

    @Value("${giphy.api.key}")
    private String apiKey;

    @Value("${giphy.tag.rich}")
    private String richTag;

    @Value("${giphy.tag.broke}")
    private String brokeTag;

    @Value("${openexchangerates.app.id}")
    private String appId;

    @Value("${gif.json.key.data}")
    private String dataJsonKey;

    @Value("${gif.json.key.embed.url}")
    private String urlJsonKey;

    @Override
    public String getGifUrlByCurrency(String currency) {
        int result = compareCurrencyRates(currency.toUpperCase(Locale.ROOT));
        return (result > 0) ? getGifUrlByTag(richTag) : getGifUrlByTag(brokeTag);
    }

    private int compareCurrencyRates(String currency) {
        String yesterdayDate = LocalDate.now().minusDays(1).toString();
        String todayDate = LocalDate.now().toString();
        CurrencyResponse yesterdayCurrencyResponse = openExchangeRatesClient.getRatesByDate(yesterdayDate, appId);
        CurrencyResponse todayCurrencyResponse = openExchangeRatesClient.getRatesByDate(todayDate, appId);
        double yesterdayCurrencyRate = getCurrencyRateToBaseRate(currency, yesterdayCurrencyResponse.getRates());
        double todayCurrencyRate = getCurrencyRateToBaseRate(currency, todayCurrencyResponse.getRates());
        return Double.compare(todayCurrencyRate, yesterdayCurrencyRate);
    }

    /**
     * @param currency проверяемая валюта
     * @param rates курсы валют относительно USD
     * @return курс проверяемой валюты относительно курса базовой валюты
     */
    private double getCurrencyRateToBaseRate(String currency, Map<String, Double> rates) {
        try {
            return rates.get(currency) / rates.get(baseCurrency);
        } catch (NullPointerException e) {
            throw new CurrencyNotFoundException(e);
        } catch (ArithmeticException e) {
            throw new ServerException(e);
        }
    }

    private String getGifUrlByTag(String tag) {
        String response = giphyClient.getResponseByTag(apiKey, tag);
        try {
            JsonNode jsonNode = objectMapper.readTree(response);
            return jsonNode.get(dataJsonKey).get(urlJsonKey).asText();
        } catch (JsonProcessingException e) {
            throw new ServerException(e);
        }
    }
}
