# DDSheet

Sovellus tallentaa käyttäjän Dungeons & Dragons-hahmoja, joita voi muokata, tarkastella ja poistaa.
Käyttäjiä voi olla useita, joille kirjautumiseen vaaditaan oikea salasana. 
Sovellus tulee käyttämään SQLiteä käyttäjien ja hahmojen tallentamiseen, mutta tätä ei olla vielä implementoitu.

## Dokumentaatio

[Määrittelydokumentti](https://github.com/mfk99/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)

[Työaikakirjanpito](https://github.com/mfk99/ot-harjoitustyo/blob/master/dokumentaatio/aikakirjanpito.md)

[Arkktehtuurikuvaus](https://github.com/mfk99/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)

[Changelog](https://github.com/mfk99/ot-harjoitustyo/blob/master/dokumentaatio/changelog.md)

[Release](https://github.com/mfk99/ot-harjoitustyo/releases/tag/viikko5)

[Käyttöohje](https://github.com/mfk99/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)

## Käynnistysohje
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

Checkstyle-raportin käskyllä

```
mvn jxr:jxr checkstyle:checkstyle
```

ja JavaDocin käskyllä
```
mvn javadoc:javadoc
```

