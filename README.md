# CurrencyConverter
A simple command-line application written in Java 17 to exchange popular currencies.

## Usage
```shell
usage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<base>] <currency> [<amount>]
```

## Features
- Exchange rate data covering all 161 major currencies used in 200 countries
- Compliance with ISO 4217 Three Letter Currency Codes (e.g. USD, GBP, EUR... etc.)
- Rates are updated everyday and cached locally in-between refreshes
- Offline availability (falls back on most recent cached data)
- Simple, lightweight command-line interface

## Quickstart guide
### Compilation
```shell
mvn package
```

## Running
```shell
java -jar target/CurrencyConverter-1.0-SNAPSHOT.jar exchange -b=gbp -c=eur 1
```
```shell
# Example output
£1.00 (British Pound) = €1.18 (Euro)
```

## Commands
`-v` or `--version`: Prints application version to console.

`-h` or `--help`: Prints helpful usage and example information to console.

`exchange [--b=<currency> | --base=<currency>] -c=<currency> | --currency=<currency> [<amount>]`: Prints exchange rate for a given base, in the desired currency for a provided amount.

## Credit
<a href="https://www.exchangerate-api.com">Rates By Exchange Rate API</a>