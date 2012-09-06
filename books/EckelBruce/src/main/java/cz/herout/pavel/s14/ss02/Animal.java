/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s14.ss02;

/**
 *
 * @author bracek
 */
public class Animal {
    
    private String type;

    public Animal(String type) {
        this.type = type;
    }

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public String getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(String type) {
        this.type = type;
    }

    public void listingInfo(){
        System.out.println(getClass().getName() + ", ");
    }

}

