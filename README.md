# DDSheet

## Dokumentaatio

[Työaikakirjanpito](https://github.com/mfk99/ot-harjoitustyo/blob/master/dokumentaatio/aikakirjanpito.md)

[Arkktehtuurikuvaus](https://github.com/mfk99/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Changelog](https://github.com/mfk99/ot-harjoitustyo/blob/master/dokumentaatio/changelog.md)

## Käyttöohje
Ohjelma käynnistyy DDSheet-hakemistossa komennolla

```
mvn compile exec:java -Dexec.mainClass=ddsheet.Main
```

tai jar-tiedostolla luomalla jar-tiedosto komennolla
```
mvn package
```
ja käynnistämällä target-hakemistosta komennolla
```
java -jar DDSheet-1.0-SNAPSHOT.jar 
```

Ohjelman jacoco-raportin saa käskyllä

```
mvn test jacoco:report 
```

ja checkstyle-raportin käskyllä

```
mvn jxr:jxr checkstyle:checkstyle
```
