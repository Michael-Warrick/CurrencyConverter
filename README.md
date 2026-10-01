# CurrencyConverter
A simple command-line application written in Java 17 to convert popular currencies.

## Usage
```shell
usage: currency_converter [-v | --version] [-h | --help] [-l | --list] <command> [<args>]
```

## Features
- Exchange rate data covering all 161 major currencies used in 200 countries
- Compliance with ISO 4217 Three Letter Currency Codes (e.g. USD, GBP, EUR... etc.)
- Data is refreshed everyday and cached locally in-between refreshes
- Offline availability (will fallback on most recent cached data)
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

## Credit
<a href="https://www.exchangerate-api.com">Rates By Exchange Rate API</a>