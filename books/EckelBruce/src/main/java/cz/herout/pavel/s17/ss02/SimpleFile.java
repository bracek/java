/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.herout.pavel.s17.ss02;

import java.io.File;

/**
 *
 * @author bracek
 */
public class SimpleFile {
    public static void main(String[] args) {
        String aktDir = System.getProperty("user.dir");
        File fileAbs = new File(aktDir, "a.txt");
        File soubRe1 = new File("TMP" + File.separator + "a.txt");
        File soub = new File("a.txt");
        
        System.out.println(soubRe1.getAbsolutePath());
        System.out.println(soubRe1.getName());
        System.out.println(soubRe1.getParent());
        
        System.out.println(fileAbs.getAbsolutePath());
        System.out.println(fileAbs.getName());
        System.out.println(fileAbs.getParent());
    }

}
