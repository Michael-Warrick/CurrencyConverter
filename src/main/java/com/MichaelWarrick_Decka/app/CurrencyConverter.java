package com.MichaelWarrick_Decka.app;

import java.io.Closeable;
import java.io.IOException;
import java.util.Currency;
import java.util.Locale;
import java.text.NumberFormat;

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

    void exchange(String currencyCode, double amount) {
        JSONObject rates = (JSONObject) json.get("rates");
        double rate = (Double) rates.get(currencyCode.toUpperCase());

        Currency baseCurrency = Currency.getInstance(this.baseCurrencyCode.toUpperCase());
        String baseCurrencySymbol = baseCurrency.getSymbol();
        String baseCurrencyDisplayName = baseCurrency.getDisplayName();
        int baseCurrencyFractionDigits = baseCurrency.getDefaultFractionDigits();

        NumberFormat baseCurrencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        baseCurrencyFormat.setCurrency(baseCurrency);
        baseCurrencyFormat.setMaximumFractionDigits(baseCurrencyFractionDigits);

        Currency currency = Currency.getInstance(currencyCode.toUpperCase());
        String currencySymbol = currency.getSymbol();
        String currencyDisplayName = currency.getDisplayName();
        int currencyFractionDigits = currency.getDefaultFractionDigits();

        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.UK);
        currencyFormat.setCurrency(currency);
        currencyFormat.setMaximumFractionDigits(currencyFractionDigits);

        System.out.printf("%s (%s) = %s (%s)\n", baseCurrencyFormat.format(amount),
                baseCurrencyDisplayName, currencyFormat.format(rate * amount), currencyDisplayName);
    }

}