
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate kassapaate; 
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    @Test
    public void kassapaatteenRahamaaraOK() {
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test
    public void kassapaatteenMyytyjenLounaidenMaaraOK() {
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty()+kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void maukkaidenLounaidenMyyntiToimii() {
        kassapaate.syoMaukkaasti(600);
        assertEquals(100400, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }
    @Test
    public void maukkaidenLounaidenMyyntiPalauttaaOikeanMaaranRahaa() {
        assertEquals(200,kassapaate.syoMaukkaasti(600));
    }
    @Test
    public void josMaukkaidenMyyntiMaksuEiOleRiittavaPalauttaaOikeanMaaranRahaa() {
        assertEquals(240,kassapaate.syoMaukkaasti(240));
    }
    @Test
    public void edullistenLounaidenMyyntiToimii() {
        kassapaate.syoEdullisesti(600);
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void edullistenLounaidenMyyntiPalauttaaOikeanMaaranRahaa() {
        assertEquals(360,kassapaate.syoEdullisesti(600));
    }
    @Test
    public void josEdullistenMyyntiMaksuEiOleRiittavaPalauttaaOikeanMaaranRahaa() {
        assertEquals(210,kassapaate.syoEdullisesti(210)); 
    }
    @Test
    public void eiKasvataMyytyjenMaaraaJosMaksuEiOleRiittava() {
        kassapaate.syoEdullisesti(210);
        kassapaate.syoMaukkaasti(240);
        assertEquals(0,kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(0,kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void edullistenOstoToimiiKortilla() {
        kassapaate.syoEdullisesti(kortti);
        assertEquals(760, kortti.saldo());
        assertEquals(true,kassapaate.syoEdullisesti(kortti));
    }
    @Test
    public void maukkaastiOstoToimiiKortilla() {
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(600, kortti.saldo());
        assertEquals(true,kassapaate.syoMaukkaasti(kortti));
    }
    @Test
    public void josKortillaOntarpeeksiRahaaMyytyjenLounaidenMaaraKasvaa() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(2,kassapaate.maukkaitaLounaitaMyyty()+kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void josKortillaEiOleTarpeeksiRahaaRahaMaaraEiMuutu() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(200,kortti.saldo());
    }
    @Test
    public void josKortillaEiOleTarpeeksiRahaaMyytyjenLounaidenMaaraEiMuutuJaPalautetaanFalse() {
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        kassapaate.syoEdullisesti(kortti);
        assertEquals(false,kassapaate.syoEdullisesti(kortti));
        assertEquals(false,kassapaate.syoMaukkaasti(kortti));
        assertEquals(2,kassapaate.maukkaitaLounaitaMyyty()+kassapaate.edullisiaLounaitaMyyty());
    }
    @Test
    public void kassassaOlevanRahanmaaraEiMuutuKortillaOstettaessa() {
        kassapaate.syoEdullisesti(kortti);
        kassapaate.syoMaukkaasti(kortti);
        assertEquals(100000, kassapaate.kassassaRahaa());
    }
    @Test
    public void kortilleRahaaLadatessaKortinSaldoMuuttuuJaKassassaOlevanRahanMaaraKasvaa() {
        kassapaate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, kassapaate.kassassaRahaa());
        assertEquals(1100, kortti.saldo());
    }
    @Test
    public void kortilleEiVoiLadataNegatiivistaSummaa() {
        kassapaate.lataaRahaaKortille(kortti, -100);
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(1000, kortti.saldo());
        
    }
    
    
    
    
    
    
}
