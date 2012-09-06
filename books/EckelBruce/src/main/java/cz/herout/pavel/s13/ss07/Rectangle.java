/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s13.ss07;

import cz.herout.pavel.s13.ss04.InfoDalsi;

/**
 *
 * @author bracek
 */
public class Rectangle extends Vector implements InfoDalsi {

    private int width;

    public Rectangle(int length, int width) {
        super(length);
        this.width = width;
    }

    /**
     * Get the value of width
     *
     * @return the value of width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Set the value of width
     *
     * @param width new value of width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    public void vlastnosti() {
        System.out.println(" = " + length + "," + width);
    }

    @Override
    public void kdoJsem() {
        System.out.println("Rectangle");
    }
    
    public void writeWidth(){ // nie je v ziadnom rozhrani
        System.out.println(width);
    }
}
