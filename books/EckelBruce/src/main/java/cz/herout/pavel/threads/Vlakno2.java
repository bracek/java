/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.threads;

import java.io.File;

/**
 *
 * @author bracek
 */
public class Vlakno2 extends Thread {

    public void run() {
        while (ReadV1.hotovo == false) {

            System.out.println(ReadV1.suma + "\r");
            Thread.yield();
        }
        System.out.println(ReadV1.suma);
    }

    public static void main(String[] args) {

        String aktDir = System.getProperty("user.dir");
        String finalPath = aktDir + "/src/cz/herout/pavel/threads/";
        File fread = new File(finalPath, "data.txt");

        ReadV1 vlCteni = new ReadV1(fread);
        vlCteni.start();

        Vlakno2 vlVypis = new Vlakno2();
        vlVypis.start();
    }
}
