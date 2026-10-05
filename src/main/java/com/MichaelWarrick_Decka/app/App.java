package com.MichaelWarrick_Decka.app;

import java.lang.RuntimeException;

/**
 * Driver class for handling input and passing data to CurrencyConverter backend
 */
public class App {
    private static boolean isNumeric(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.printf(
                    "usage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<base>] <currency> <amount>\n");
            return;
        }

        boolean hasProvidedCommand = false;
        boolean hasProvidedCurrency = false;

        String baseCurrencyCode = "GBP";
        String currencyCode = "USD";
        double amount = 1;
        CurrencyConverter converter = null;

        for (String value : args) {
            if (value.equals("-v") || value.equals("--version")) {
                System.out.printf("currency_converter version 1.0.0\n");
                return;
            }

            if (value.equals("-h") || value.equals("--help")) {
                System.out.printf(
                        "usage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<base>] <currency> <amount>\n\nExample with base and currency:\n`currency_converter exchange -b=gpb -c=usd 1`\n");
                return;
            }

            if (value.equals("exchange")) {
                hasProvidedCommand = true;
                continue;
            }

            if (value.contains("-b=") || value.contains("--base=")) {
                String[] baseArgument = value.split("=");
                baseCurrencyCode = baseArgument[1];
                continue;
            }

            if (value.contains("-c=") || value.contains("--currency=")) {
                String[] currencyArgument = value.split("=");
                currencyCode = currencyArgument[1];
                hasProvidedCurrency = true;
                continue;
            }

            if (!hasProvidedCommand) {
                System.err.println(
                        "Unrecognised command provided!\nusage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<base>] <currency> <amount>");
                return;
            }

            if (isNumeric(value)) {
                amount = Double.parseDouble(value);
                break;
            }
        }

        if (!hasProvidedCurrency) {
            System.err.println(
                    "No currency provided!\nusage: currency_converter [-v | --version] [-h |--help] [-l | --list] <command> [<base>] <currency> <amount>");
            return;
        }

        converter = new CurrencyConverter(baseCurrencyCode);
        converter.exchange(currencyCode, amount);
    }
}