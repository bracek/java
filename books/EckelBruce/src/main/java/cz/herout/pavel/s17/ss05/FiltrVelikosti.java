/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s17.ss05;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author bracek
 */
public class FiltrVelikosti implements FilenameFilter {
    
    private int velikost;

    public FiltrVelikosti(int velikost) {
        this.velikost = velikost;
    }
    

    /**
     * Get the value of velikost
     *
     * @return the value of velikost
     */
    public int getVelikost() {
        return velikost;
    }

    /**
     * Set the value of velikost
     *
     * @param velikost new value of velikost
     */
    public void setVelikost(int velikost) {
        this.velikost = velikost;
    }


    public boolean accept(File dir, String name) {
        File f = new File(dir, name);
        if(f.length() > velikost)
            return true;
        else
            return false;
    }

}
