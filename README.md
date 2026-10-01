# CurrencyConverter
A simple command-line application written in Java 17 to convert popular currencies.

<a href="https://www.exchangerate-api.com">Rates By Exchange Rate API</a>

# Building from Source
## Compilation
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