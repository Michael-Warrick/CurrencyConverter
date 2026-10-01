package com.MichaelWarrick_Decka.app;

import java.io.Closeable;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

public class CurrencyConverter {
    private Currency referenceCurrency;
    private Currency selectedCurrency;
    private Map<String, String> jsonInMemoryCache;

    CurrencyConverter(String referenceCurrencyName, String selectedCurrencyName, Currency value) {
        // Must access currency class statically, hence using `getInstance()`
        this.referenceCurrency = Currency.getInstance(referenceCurrencyName.toUpperCase());
        this.selectedCurrency = Currency.getInstance(selectedCurrencyName.toUpperCase());
        this.jsonInMemoryCache = new HashMap<>();

        try (CloseableHttpClient httpsClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("https://open.er-api.com/v6/latest/USD");

            httpsClient.execute(httpGet, response -> {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.printf("Status code: %d\n", response.getCode());
                System.out.printf("Response body:\n%s\n", responseBody);

                return null;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    Currency convert(Currency value) {
        return value;
    }

}