```mermaid
sequenceDiagram
    main ->>+ laitehallinto: new HKLLaitehallinto()
    laitehallinto ->>+ lataajat: new ArrayList()
    lataajat ->>- laitehallinto: lataajat
    laitehallinto ->>+ lukijat: new ArrayList()
    lukijat ->>- laitehallinto: lukijat
    laitehallinto ->>- main: laitehallinto
    main ->>+ rautatietori: new Lataajalaite()
    rautatietori ->>- main: rautatietori
    main ->>+ ratikka6: new Lukijalaite()
    ratikka6 ->>- main: ratikka6
    main ->>+ bussi244: new Lukijalaite()
    bussi244 ->>- main: bussi244
    
    main ->> laitehallinto: lisaaLataaja(rautatietori)
    laitehallinto ->> lataajat: add(rautatietori)
    main ->> laitehallinto: lisaaLukija(ratikka6)
    laitehallinto ->> lukijat: add(ratikka6)
    main ->> laitehallinto: lisaaLukija(bussi244)
    laitehallinto ->> lukijat: add(bussi244)
    
    main ->>+ lippuLuukku: new Kioski()
    lippuLuukku ->>- main: lippuLuukku
    main ->>+ lippuLuukku: ostaMatkakortti("Arto")
    lippuLuukku ->>+uusiKortti: new Matkakortti("Arto")
    uusiKortti ->>- lippuLuukku: uusiKortti
    lippuLuukku ->>- main: artonKortti
    
    main ->> rautatietori: lataaArvoa (artonKortti, 3)
    rautatietori ->> artonKortti: kasvataArvoa(3)
    
    main ->>+ ratikka6: ostaLippu(artonKortti, 0)
    ratikka6 ->>+ artonKortti: getArvo()
    artonKortti ->>- ratikka6: 3
    ratikka6 ->> artonKortti: vahennaArvoa(1.5)
    ratikka6->>- main: true
    main ->>+ bussi244: ostaLippu(artonKortti, 2)
    bussi244 ->>+ artonKortti: getArvo()
    artonKortti ->>- bussi244: 1.5
    bussi244 ->>- main: false
