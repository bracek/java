/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s13.ss03;

/**
 *
 * @author bracek
 */
public class Koule implements Info {

    public void kdoJsem() {
        System.out.println("Koule");
    }
    private int polomer;

    /**
     * Get the value of polomer
     *
     * @return the value of polomer
     */
    public int getPolomer() {
        return polomer;
    }

    /**
     * Set the value of polomer
     *
     * @param polomer new value of polomer
     */
    public void setPolomer(int polomer) {
        this.polomer = polomer;
    }
}
