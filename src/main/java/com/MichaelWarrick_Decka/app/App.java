package com.MichaelWarrick_Decka.app;

import java.lang.RuntimeException;

/**
 * Driver class for handling input and passing data to CurrencyConverter backend
 */
public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.printf(
                    "usage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<base>] <currency> <amount>\n");
            return;
        }

        boolean isInVersionMode = false;
        boolean isInHelpMode = false;
        boolean shouldErrorOut = false;

        CurrencyConverter converter = null;
        String currencyCode = "GBP";
        double amount = 1;


        for (String value : args) {
            if (value.equals("-v") || value.equals("--version")) {
                isInVersionMode = true;
                System.out.printf("currency_converter version 1.0.0\n");
                break;
            }

            if (value.equals("-h") || value.equals("--help")) {
                System.out.printf(
                        "usage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<base>] <currency> <amount>\n\nExample with base and currency:\n`currency_converter exchange -b=gpb -c=usd 1`\n");
                break;
            }

            if (value.equals("exchange")) {
                continue;
            } else {
                shouldErrorOut = true;
                System.out.println("Error: No command provided!");
            }

            if (value.contains("-b=") || value.contains("--base=")) {
                String[] baseArgument = value.split("=");
                String baseCurrencyCode = baseArgument[1];

                converter = new CurrencyConverter(baseCurrencyCode);
                continue;
            } else {
                // Default to gbp
                converter = new CurrencyConverter("gbp");
            }

            if (value.contains("-c=") || value.contains("--currency=")) {
                String[] currencyArgument = value.split("=");
                currencyCode = currencyArgument[1];
                continue;
            } else {
                shouldErrorOut = true;
                System.err.println("No currency provided!\nusage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<base>] <currency> <amount>");
            }

            try {
                amount = Double.parseDouble(value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!shouldErrorOut && !isInVersionMode && !isInHelpMode) {
            converter.exchange(currencyCode, amount);
        }
    }
}