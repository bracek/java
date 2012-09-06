/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s14.ss01;

/**
 *
 * @author bracek
 */
public class Snake extends Animal {
    
        private int bodyLenght;

    public Snake(String typ, int bodyLenght) {
        super(typ);
        this.bodyLenght = bodyLenght;
    }

    @Override
    public void listingLength() {
        System.out.println("snake bodyLenght = " + bodyLenght);
    }
        

    /**
     * Get the value of bodyLenght
     *
     * @return the value of bodyLenght
     */
    public int getBodyLenght() {
        return bodyLenght;
    }

    /**
     * Set the value of bodyLenght
     *
     * @param bodyLenght new value of bodyLenght
     */
    public void setBodyLenght(int bodyLenght) {
        this.bodyLenght = bodyLenght;
    }


}
