package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    public KassapaateTest() {
    }
    
    Kassapaate paate;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        paate=new Kassapaate();
        kortti=new Maksukortti(0);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {}
     
    @Test
    public void alussaOikeaMaaraRahaa() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void alussaOikeaMaaraEdullisiaMyyty() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void alussaOikeaMaaraMaukkaitaMyyty() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoKasvattaaKassanArvoa() {
        paate.syoEdullisesti(300);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOstoKasvattaaKassanArvoa() {
        paate.syoMaukkaasti(500);
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstoAntaaOikeanVaihtorahan() {
        assertEquals(60, paate.syoEdullisesti(300));
    }
    
    @Test
    public void maukkaanOstoAntaaOikeanVaihtorahan() {
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    
    @Test
    public void edullisenOstoKasvattaaMyytyjenArvoa() {
        paate.syoEdullisesti(300);
        paate.syoEdullisesti(300);
        assertEquals(2, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenOstoKasvattaaMyytyjenArvoa() {
        paate.syoMaukkaasti(1000);
        paate.syoMaukkaasti(1000);
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenMyynninEpaonnistuessaKassaEiKasva() {
        paate.syoEdullisesti(20);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void maukkaanMyynninEpaonnistuessaKassaEiKasva() {
        paate.syoMaukkaasti(0);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisenMyynninEpaonnistuessaRahatPalautetaan() {
        assertEquals(239, paate.syoEdullisesti(239));
    }
    
    @Test
    public void maukkaanMyynninEpaonnistuessaRahatPalautetaan() {
        assertEquals(15, paate.syoMaukkaasti(15));
    }
    
    @Test
    public void edullisenMyynninEpaonnistuessaMyyntienMaaraEiKasva() {
        paate.syoEdullisesti(2000);
        paate.syoEdullisesti(20);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanMyynninEpaonnistuessaMyyntienMaaraEiKasva() {
        paate.syoMaukkaasti(1000);
        paate.syoMaukkaasti(500);
        paate.syoMaukkaasti(0);
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void myydaanEdullinenKunKortillaOnTarpeeksiRahaa() {
        kortti.lataaRahaa(500);
        assertEquals(true, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void myydaanMaukasKunKortillaOnTarpeeksiRahaa() {
        kortti.lataaRahaa(1000);
        assertEquals(true, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void myytyjenEdullistenMaaraKasvaaKortillaMyytaessa() {
        kortti.lataaRahaa(500);
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void myytyjenMaukkaittenMaaraKasvaaKortillaMyytaessa() {
        kortti.lataaRahaa(5000);
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kortinRahaMaaraEiMuutuEdullisenOstonEpaonnistuessa() {
        kortti.lataaRahaa(20);
        paate.syoEdullisesti(kortti);
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void kortinRahaMaaraEiMuutuMaukkaanOstonEpaonnistuessa() {
        kortti.lataaRahaa(50);
        paate.syoMaukkaasti(kortti);
        assertEquals(50, kortti.saldo());
    }
    
    @Test
    public void edullistenMyyntiMaaraEiMuutuKortillaOstonEpaonnistuessa() {
        kortti.lataaRahaa(20);
        paate.syoEdullisesti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaidenMyyntiMaaraEiMuutuKortillaOstonEpaonnistuessa() {
        kortti.lataaRahaa(399);
        paate.syoMaukkaasti(kortti);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void palautetaanFalseEdullisenKortillaOstonEpaonnistuessa() {
        kortti.lataaRahaa(20);
        assertEquals(false, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void palautetaanFalseMaukkaanKortillaOstonEpaonnistuessa() {
        kortti.lataaRahaa(50);
        assertEquals(false, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kassaRahaMaaraEiMuutuEdullistaKortillaOstaessa() {
        kortti.lataaRahaa(5000);
        paate.syoEdullisesti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kassaRahaMaaraEiMuutuMaukastaKortillaOstaessa() {
        kortti.lataaRahaa(10000);
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kassaRahaMaaraKasvaaKortilleLadattaessa() {
        paate.lataaRahaaKortille(kortti, 5000);
        assertEquals(105000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortinArvoNouseeRahaaLadattaessa() {
        paate.lataaRahaaKortille(kortti, 10);
        paate.lataaRahaaKortille(kortti, 1);
        assertEquals(100011, paate.kassassaRahaa());
    }
    
    @Test
    public void kortilleEiVoiLadataNegattivistaArvoa() {
        paate.lataaRahaaKortille(kortti, -5);
        assertEquals(0, kortti.saldo());
    }
}
