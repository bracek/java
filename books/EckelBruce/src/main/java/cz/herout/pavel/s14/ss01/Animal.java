/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s14.ss01;

/**
 *
 * @author bracek
 */
public abstract  class Animal {

    private String type;
    
    public Animal(String typ) {
        this.type = typ;
    }
    
    public void listingInfo(){
        System.out.println("typ = " + type);
        listingLength();
        
    }
    public abstract void listingLength();
    
    

    /**
     * Get the value of typ
     *
     * @return the value of typ
     */
    public String getTyp() {
        return type;
    }

    /**
     * Set the value of typ
     *
     * @param typ new value of typ
     */
    public void setTyp(String typ) {
        this.type = typ;
    }


}
