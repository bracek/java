package cz.heroult.pavel.bohatstvoKnihoven.kap19;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                   Java -- bohatství knihoven                //
//                II. opravené a rozšíøené vydání              //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       Cti_me.txt                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2006                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.io.*;
import java.text.*;
import java.util.*;
import java.util.zip.*;

public class ZipCteniZipInputStream {
  public static void main(String[] args) throws IOException {
    File f = new File("pokus.zip");
    FileInputStream fis = new FileInputStream(f);
    ZipInputStream zfis = new ZipInputStream(fis);
    int cisloPolozky = 0;
    ZipEntry ze;
    while ((ze = zfis.getNextEntry()) != null) {
      System.out.println("Polozka cislo: " + (++cisloPolozky));
      System.out.print("  jmeno: " + ze.getName());
      System.out.print(", komprimovana velikost: " + ze.getCompressedSize());
      System.out.print(", skutecna velikost: " + ze.getSize());
      Date d = new Date(ze.getTime());
      DateFormat df = DateFormat.getDateInstance();
      System.out.println(", datum: " + df.format(d));
      if (ze.isDirectory())
        continue;
      int len = (int) ze.getSize();
      byte[] b = new byte[len];
      zfis.read(b);
      System.out.println(new String(b));
    }
    zfis.close();
    fis.close();
  }
}
