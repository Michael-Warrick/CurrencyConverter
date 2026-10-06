package com.MichaelWarrick_Decka.app;

import java.io.IOException;
import java.io.File;

import java.util.Currency;
import java.util.Locale;

import java.text.NumberFormat;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;

import org.apache.hc.core5.http.io.entity.EntityUtils;

import org.json.simple.JSONObject;
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
        this.baseCurrencyCode = baseCurrencyCode;

        // Check if cache exists for a given base currency
        File cacheFile = new File("target/.cache/exchange_data_" + this.baseCurrencyCode.toLowerCase() + ".json");
        if (cacheFile.exists() && !cacheFile.isDirectory()) {
            String cacheFileContents = "";
            try {
                cacheFileContents = Files.readString(Paths.get("target/.cache/exchange_data_" + this.baseCurrencyCode.toLowerCase() + ".json"), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            this.json = (JSONObject) JSONValue.parse(cacheFileContents);

            long cacheFileNextUpdateUnixTime = (long) this.json.get("time_next_update_unix");
            long currentUnixTime = System.currentTimeMillis() / 1000L;
            if (currentUnixTime < cacheFileNextUpdateUnixTime) {
                // Cached file is valid, perform local lookup.
                return;
            }
        }

        // If cache does not exist, is out of date or not available for the chosen base currency, download the latest available data
        try (CloseableHttpClient httpsClient = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet("https://open.er-api.com/v6/latest/" + baseCurrencyCode.toUpperCase());

            httpsClient.execute(httpGet, response -> {
                String httpResponseBody = EntityUtils.toString(response.getEntity());
                this.json = (JSONObject) JSONValue.parse(httpResponseBody);

                try {
                    saveCachedResultsToDisk("target/.cache/exchange_data_", this.baseCurrencyCode.toLowerCase(),
                            this.json);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exchange(String currencyCode, double amount) {
        JSONObject rates = (JSONObject) this.json.get("rates");
        double rate = (Double) rates.get(currencyCode.toUpperCase());

        Currency baseCurrency = Currency.getInstance(this.baseCurrencyCode.toUpperCase());
        NumberFormat baseCurrencyFormat = generateCurrencyFormat(baseCurrency, Locale.UK);

        Currency currency = Currency.getInstance(currencyCode.toUpperCase());
        NumberFormat currencyFormat = generateCurrencyFormat(currency, Locale.UK);

        System.out.printf("%s (%s) = %s (%s)\n", baseCurrencyFormat.format(amount),
                baseCurrency.getDisplayName(), currencyFormat.format(rate * amount), currency.getDisplayName());
    }

    private static NumberFormat generateCurrencyFormat(Currency currency, Locale locale) {
        int currencyFractionDigits = currency.getDefaultFractionDigits();

        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.UK);
        format.setCurrency(currency);
        format.setMaximumFractionDigits(currencyFractionDigits);

        return format;
    }

    private static void saveCachedResultsToDisk(String pathString, String baseCurrencyCode, JSONObject json)
            throws IOException {
        File cacheJsonFile = new File(pathString + baseCurrencyCode + ".json");
        cacheJsonFile.getParentFile().mkdirs();
        cacheJsonFile.createNewFile();

        Path cachePath = Paths.get(pathString + baseCurrencyCode + ".json");
        String cacheString = JSONValue.toJSONString(json);
        Files.writeString(cachePath, cacheString, StandardCharsets.UTF_8);
    }
}