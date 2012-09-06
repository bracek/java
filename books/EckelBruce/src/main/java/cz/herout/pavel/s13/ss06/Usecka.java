/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s13.ss06;

import cz.herout.pavel.s13.ss03.Info;

/**
 *
 * @author bracek
 */
public class Usecka implements Info {

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
}
