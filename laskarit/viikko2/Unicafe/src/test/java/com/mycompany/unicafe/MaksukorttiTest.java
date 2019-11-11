package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }
    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        assertEquals(1000,  kortti.saldo());
    }
    @Test
    public void rahanLataaminenKasvasttaaSaldonOikein() {
        kortti.lataaRahaa(400);
        assertEquals("saldo: 14.0",  kortti.toString());
    }
    @Test
    public void saldoVaheneeOikein() {
        kortti.otaRahaa(200);
        assertEquals("saldo: 8.0",  kortti.toString());
    }
    @Test
    public void saldoEiMuutuJosRahaaLiianVahan() {
        kortti.otaRahaa(1200);
        assertEquals("saldo: 10.0",  kortti.toString());
    }
    @Test
    public void metodiPalauttaaTrueJosRahatRiittivät() {
        assertEquals(true, kortti.otaRahaa(800) );
    }
    

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
}
