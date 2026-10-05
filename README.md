# CurrencyConverter
A simple command-line application written in Java 17 to convert popular currencies.

## Usage
```shell
usage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<args>]
```

## Features
- Exchange rate data covering all 161 major currencies used in 200 countries
- Compliance with ISO 4217 Three Letter Currency Codes (e.g. USD, GBP, EUR... etc.)
- Rates are updated everyday and cached locally in-between refreshes
- Offline availability (falls back on most recent cached data)
- Simple, lightweight command-line interface

## Building from Source
### Compilation
```shell
# Plugin to allow copying of archives to target directory
mvn install dependency:copy-dependencies
```

```shell
mvn package
```

## Running
### Unix/Linux
```shell
java -cp target/CurrencyConverter-1.0-SNAPSHOT.jar:target/dependency/* com.MichaelWarrick_Decka.app.App
```

### Windows
```shell
java -cp target/CurrencyConverter-1.0-SNAPSHOT.jar;target/dependency/* com.MichaelWarrick_Decka.app.App
```

## Examples
### Only specifying currency
```shell
java -cp target/CurrencyConverter-1.0-SNAPSHOT.jar:target/dependency/* com.MichaelWarrick_Decka.app.App exchange -c=eur
```
### Specifying base and currency
```shell
java -cp target/CurrencyConverter-1.0-SNAPSHOT.jar:target/dependency/* com.MichaelWarrick_Decka.app.App exchange -b=usd -c=eur
```

### Specifying base, currency and amount
```shell
java -cp target/CurrencyConverter-1.0-SNAPSHOT.jar:target/dependency/* com.MichaelWarrick_Decka.app.App exchange -b=usd -c=eur 12.75
```

### Specifying base, currency and amount (long argument names)
```shell
java -cp target/CurrencyConverter-1.0-SNAPSHOT.jar:target/dependency/* com.MichaelWarrick_Decka.app.App exchange --base=usd --currency=eur 12.75
```

## Credit
<a href="https://www.exchangerate-api.com">Rates By Exchange Rate API</a>