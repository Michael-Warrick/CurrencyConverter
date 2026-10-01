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

public class CurrencyConverter {
    CurrencyConverter(String referenceCurrencyName, String selectedCurrencyName, Currency value) {
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