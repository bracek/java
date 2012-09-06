/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s17.ss03;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bracek
 */
public class CreateFileOrDirectory {

    public static void main(String[] args) {
        try {

            String aktDir = System.getProperty("user.dir");
            File novySoub = new File("b.txt");
            File novyAdr = new File("TMP");
            if (novySoub.exists() == true) {
                System.out.println("b.txt uz existuje");
            } else {
                novySoub.createNewFile();
            }

            if (novyAdr.exists() == true) {
                System.out.println("TMP existuje");
            } else {
                novyAdr.mkdir();
            }
            if (novyAdr.isDirectory() == true) {
                System.out.println("TMP je adresar");
            }
        } catch (IOException ex) {
            Logger.getLogger(CreateFileOrDirectory.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
