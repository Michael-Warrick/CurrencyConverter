package com.MichaelWarrick_Decka.app;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverter {
    private Currency referenceCurrency;
    private Currency selectedCurrency;
    private Map<String, String> jsonInMemoryCache;

    CurrencyConverter(String referenceCurrencyName, String selectedCurrencyName, Currency value) {
        // Must access currency class statically, hence using `getInstance()`
        this.referenceCurrency = Currency.getInstance(referenceCurrencyName.toUpperCase());
        this.selectedCurrency = Currency.getInstance(selectedCurrencyName.toUpperCase());
        this.jsonInMemoryCache = new HashMap<>();
    }

    Currency convert(Currency value) {
        return value;
    } 
    
}