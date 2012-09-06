/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.herout.pavel.s18.ss031;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author bracek
 */
public class MyFileReader {

    public static void main(String[] args) throws FileNotFoundException, IOException {

        String aktDir = System.getProperty("user.dir");
        System.out.println("aktDir = " + aktDir);


        String finalPath = aktDir + "/src/cz/herout/pavel/s18/ss031/";
        File frJm = new File(finalPath, "a.txt");
        FileReader fr = new FileReader(frJm);

        File frw = new File(finalPath, "b.txt");
        FileWriter fw = new FileWriter(frw);

        long delka = frJm.length();
        int c;

        for (long i = 0; i < delka; i++) {
            c = fr.read();
            fw.write(c);
        }

        fr.close();
        fw.close();

    }
}
