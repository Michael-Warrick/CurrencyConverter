package com.MichaelWarrick_Decka.app;

import java.io.Closeable;
import java.io.IOException;
import java.util.Currency;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

/**
 * CurrencyConverter
 * 
 * @brief A class for retrieving currency conversion information
 */
public class CurrencyConverter {
    private JSONObject json;
    private String baseCurrencyCode;

    CurrencyConverter(String baseCurrencyCode) {
        try (CloseableHttpClient httpsClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("https://open.er-api.com/v6/latest/" + baseCurrencyCode.toUpperCase());

            httpsClient.execute(httpGet, response -> {
                int httpStatusCode = response.getCode();
                String httpResponseBody = EntityUtils.toString(response.getEntity());

                json = (JSONObject) JSONValue.parse(httpResponseBody);
                System.out.println("Result: " + (String) json.get("result"));

                this.baseCurrencyCode = baseCurrencyCode;

                return null;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void exchange(String targetCurrencyCode, double amount) {
        JSONObject rates = (JSONObject) json.get("rates");
        double rate = (Double) rates.get(targetCurrencyCode.toUpperCase());
        
        System.out.printf("%s %f = %s %f\n", this.baseCurrencyCode.toUpperCase(), amount, targetCurrencyCode.toUpperCase(),
                rate);
    }

}