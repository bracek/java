/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s13.ss08;

import cz.herout.pavel.s13.ss03.Info;

/**
 *
 * @author bracek
 */
public class Vector implements Info{

    protected  int length;

    public Vector(int length) {
        this.length = length;
    }
    

    /**
     * Get the value of length
     *
     * @return the value of length
     */
    public int getLength() {
        return length;
    }

    /**
     * Set the value of length
     *
     * @param length new value of length
     */
    public void setLength(int length) {
        this.length = length;
    }

    public void kdoJsem() {
        System.out.println("Vector");
    }

}
