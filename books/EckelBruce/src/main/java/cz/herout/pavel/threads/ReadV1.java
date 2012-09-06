/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.threads;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 *
 * @author bracek
 */
public class ReadV1 extends Thread {

    FileReader fr;
    BufferedReader in;
    String filename;
    File f;
    static public long suma = 0;
    static public boolean hotovo = false;

    public ReadV1(String filename) {
        super("Vlakno pre citanie");
        this.filename = filename;
    }

    ReadV1(File fread) {
        f = fread;
    }

    public void run() {
        String radka;

        try {

            fr = new FileReader(f);
            in = new BufferedReader(fr);
            while ((radka = in.readLine()) != null) {
                suma += Integer.parseInt(radka);
//                System.out.println("ReadV1 run");
                Thread.yield();
            }
            fr.close();
            hotovo = true;


        } catch (Exception e) {
            System.out.println("Chyba v subore");
            hotovo = true;
        }
    }
}
