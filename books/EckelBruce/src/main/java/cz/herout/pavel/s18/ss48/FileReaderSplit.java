/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s18.ss48;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author bracek
 */
public class FileReaderSplit {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String aktDir = System.getProperty("user.dir");
        String finalPath = aktDir + "/src/cz/herout/pavel/s18/ss48/";
        File fr = new File(finalPath, "buf.txt");
        BufferedReader bfr = new BufferedReader(new FileReader(fr));

        String radka;
        int suma = 0;
        while ((radka = bfr.readLine()) != null) {
            String[] cisla = radka.split(" ");
            for (String cislo : cisla) {
                suma += Integer.parseInt(cislo);
            }
        }
        System.out.println("sucet je = " + suma);
        bfr.close();

    }
}
