/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.vyjimky.s01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bracek
 */
public class VyjimkaNeosetrena {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File("cisla.txt"));
            System.out.println("Precte cislo zo suboru");
            int i = sc.nextInt();
            System.out.println("Cislo je  = " + i);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VyjimkaNeosetrena.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
