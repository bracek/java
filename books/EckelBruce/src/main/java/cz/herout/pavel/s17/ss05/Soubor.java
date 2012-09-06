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
public class Soubor {
    public static void main(String[] args) {
        String jmenoAktDir = System.getProperty("user.dir");
        System.out.println("jmenoAktDir = " + jmenoAktDir);
        
        File aktDir = new File(jmenoAktDir);
        
        String[] jmena;
        FiltrPripony filtrPr = new FiltrPripony(".java");
        jmena = aktDir.list(filtrPr);
        for (int i = 0; i < jmena.length; i++) {
            String string = jmena[i];
            System.out.println("string = " + string);
        }
        
        System.out.println("xxxxxxxxxxx");
        
        File[] soubory;
        FiltrVelikosti filtrVel = new FiltrVelikosti(1000);
        soubory = aktDir.listFiles(filtrVel);
        for (int i = 0; i < soubory.length; i++) {
            File file = soubory[i];
            System.out.println("file.getName = " + file.getName());
            System.out.println("file.length() = " + file.length());
        }

    }

}

