/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s13.ss06;

/**
 *
 * @author bracek
 */
public class Obdlznik extends Usecka {

    private int sirka;

    public Obdlznik(int delka, int sirka) {
        super(delka);
        this.sirka = sirka;
    }

    /**
     * Get the value of sirka
     *
     * @return the value of sirka
     */
    public int getSirka() {
        return sirka;
    }

    /**
     * Set the value of sirka
     *
     * @param sirka new value of sirka
     */
    public void setSirka(int sirka) {
        this.sirka = sirka;
    }

    @Override
    public void kdoJsem() {
        System.out.println("Obdlznik");
    }
}
