# Arkkitehtuurikuvaus

## Pakkauskaavio

<img src="https://github.com/mfk99/ot-harjoitustyo/blob/master/dokumentaatio/kuvat/pakkauskaavio.png" width="400">

## Sekvenssikaavio

Sekvenssikaavio kuvastaa uuden käyttäjän luomista

```mermaid
sequenceDiagram
    actor User
    participant UI
    participant DDSheetService
    User->>+UI: click createUserButton
    UI->>+DDSheetService: createUser("test","test")
    DDSheetService->>DDSheetService: existingUsername("test")
    DDSheetService->>test: new User("test", "test")
    DDSheetService-->>-UI: "Account successfully created!"
    UI-->>-User: createUserPrompt.setText("Account successfully created!")
```

## Käyttöliittymä

Käyttöliittymässä on seitsemän eri näkymää

-Kirjautumisnäkymä

-Uuden käyttäjän luonnin näkymä

-Käyttäjän hahmojen listan näkymän

-Uuden hahmon luonnin näkymän

-Hahmon tarkastelunäkymän

-Hahmon muokkausnäkymä

-Attribuutin muokkausnäkymän

Jokaisen näkymän luontiin on oma metodinsa esim. buildLogInScene, BuildCreateUserScene jne. Käyttäjän hahmojen listaa sekä hahmon tarkastelu- ja muokkausnäkymiä päivitetään niiden tietojen muuttuessa metodeilla updateUserCharactersGrid, updateInspectionViewGrid ja updateModificationViewGrid. Attribuutin muokkausnäkymää muokataan buildModificationView-metodilla, jotta jokaisen attribuutin muokkaamiselle ei tarvitse luoda uutta Scene-oliota.
