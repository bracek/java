/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.edu.gymspmkr.bike;

/**
 *
 * @author bracek
 */
public class Bicykel {
    // trieda Bicykel má tri vlastnosti
    public int tempo;
    public int prevod;
    public int rychlost;
    // trieda Bicykel má jeden konštruktor
    public Bicykel() {
        System.out.println(". constructor Bicykel .");
    }

    public Bicykel(int zacTempo, int zacRychlost, int zacPrevod) {
        prevod = zacPrevod;
        tempo = zacTempo;
        rychlost = zacRychlost;
    }
    // trieda Bicykel má štyri metódy
    public void nastavTempo(int novaHodnota) {
        tempo = novaHodnota;
    }

    public int getPrevod() {
        return prevod;
    }

    public void setPrevod(int prevod) {
        this.prevod = prevod;
    }

    public int getRychlost() {
        return rychlost;
    }

    public void setRychlost(int rychlost) {
        this.rychlost = rychlost;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public void nastavPrevod(int novaHodnota) {
        prevod = novaHodnota;
    }

    public void brzdi(int spomalenie) {
        rychlost -= spomalenie;
    }

    public void pridaj(int zrychlenie) {
        rychlost += zrychlenie;
    }
}
