package com.MichaelWarrick_Decka.app;

/**
 * Driver class for handling input and passing data to CurrencyConverter backend
 */
public class App {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.printf("usage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<args>]\n");
            return;
        }

        // Prints each string in `argv[]`
        for (String value : args) {
            System.out.printf("%s\n", value);
        }
    }
}