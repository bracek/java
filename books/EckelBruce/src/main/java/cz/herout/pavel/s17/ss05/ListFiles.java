/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s17.ss05;

import java.io.File;

/**
 *
 * @author bracek
 */
public class ListFiles {
    public static void main(String[] args) {
        
        String jmenoAktDir = System.getProperty("user.dir");
        File aktDir = new File(jmenoAktDir);
        String[] jmena;
        jmena = aktDir.list();
        for (int i = 0; i < jmena.length; i++) {
            String string = jmena[i];
            System.out.println("string = " + string);
            
        }
        
      File[] soubory;
      soubory = aktDir.listFiles();
        for (int i = 0; i < soubory.length; i++) {
            File file = soubory[i];
            System.out.println("file.getName() = " + file.getName());
            System.out.println("file.length = " + file.length());
            
        }
        
    }
}
