package cz.heroult.pavel.bohatstvoKnihoven.kap19;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojov� k�d je sou��st� distribuce bal�ku program�,  //
//     poskytovan�ch jako dopl�uj�c� informace ke knize        //
//                                                             //
//                   Java -- bohatstv� knihoven                //
//                II. opraven� a roz���en� vyd�n�              //
//                                                             //
//     P�e�t�te si, pros�m, d�kladn� upozorn�n� v souboru      // 
//                       Cti_me.txt                            //
//        kter� je ned�lnou sou��st� t�to distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      // 
//                                                             //
/////////////////////////////////////////////////////////////////
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.zip.*;

public class ZipCteniZipFile {

    public static void main(String[] args) throws IOException {
        ZipFile zf = new ZipFile("pokus.zip");
        System.out.println("pocet zapakovanych polozek: " + zf.size());
        System.out.println("jmeno: " + zf.getName());
        int cisloPolozky = 0;
        for (Enumeration e = zf.entries(); e.hasMoreElements();) {
            ZipEntry ze = (ZipEntry) e.nextElement();
            System.out.print("Polozka cislo: " + (++cisloPolozky));
            System.out.println(" - " + (ze.isDirectory() ? "adresar" : "soubor"));
            System.out.print("  jmeno: " + ze.getName());
            System.out.print(", komprimovana velikost: " + ze.getCompressedSize());
            System.out.print(", skutecna velikost: " + ze.getSize());
            Date d = new Date(ze.getTime());
            DateFormat df = DateFormat.getDateInstance();
            System.out.println(", datum vzniku: " + df.format(d));
            if (ze.isDirectory()) {
                continue;
            }
            InputStream is = zf.getInputStream(ze);
            int len = (int) ze.getSize();
            byte[] b = new byte[len];
            is.read(b, 0, len);
            System.out.println("Obsah souboru:");
            System.out.println(new String(b));
        }
    }
}
