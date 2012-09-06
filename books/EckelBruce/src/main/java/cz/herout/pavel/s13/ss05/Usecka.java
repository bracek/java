/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s13.ss05;

import cz.herout.pavel.s13.ss03.Info;
import cz.herout.pavel.s13.ss04.InfoDalsi;

/**
 *
 * @author bracek
 */
public class Usecka implements Info, InfoDalsi {

    private int delka;

    public Usecka(int delka) {
        this.delka = delka;
    }
    

    /**
     * Get the value of delka
     *
     * @return the value of delka
     */
    public int getDelka() {
        return delka;
    }

    /**
     * Set the value of delka
     *
     * @param delka new value of delka
     */
    public void setDelka(int delka) {
        this.delka = delka;
    }

    public void kdoJsem() {
        System.out.println("Usecka");
    }

    public void vlastnosti() {
        System.out.println(" = " + delka);
    }
}
