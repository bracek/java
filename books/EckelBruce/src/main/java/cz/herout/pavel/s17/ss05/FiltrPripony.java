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
public class FiltrPripony implements FilenameFilter{

    public FiltrPripony(String maska) {
        this.maska = maska;
    }
    
    
    private String maska;

    /**
     * Get the value of maska
     *
     * @return the value of maska
     */
    public String getMaska() {
        return maska;
    }

    /**
     * Set the value of maska
     *
     * @param maska new value of maska
     */
    public void setMaska(String maska) {
        this.maska = maska;
    }

    
    

    public boolean accept(File dir, String name) {
        if(name.lastIndexOf(maska) > 0)
            return true;
        else
            return false;
    }
    

}
