package com.MichaelWarrick_Decka.app;

/**
 * Driver class for handling input and passing data to CurrencyConverter backend
 */
public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.printf(
                    "usage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<args>]\n");
            return;
        }

        CurrencyConverter converter = new CurrencyConverter("gbp");
        converter.exchange("usd", 1);
    }
}