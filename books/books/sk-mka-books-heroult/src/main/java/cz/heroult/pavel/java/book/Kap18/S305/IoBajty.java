package cz.heroult.pavel.java.book.Kap18.S305;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojový kód je souèástí distribuce balíku programù,  //
//     poskytovaných jako doplòující informace ke knize        //
//                                                             //
//                  Uèebnice jazyka Java                       //
//                                                             //
//     Pøeètìte si, prosím, dùkladnì upozornìní v souboru      // 
//                       CTI_ME.TXT                            //
//        který je nedílnou souèástí této distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2000                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.io.*;

public class IoBajty {
  public static void main(String[] args) throws IOException {
    File frJm = new File("a.txt");
    File fwJm = new File("c.txt");

    if (frJm.exists() == true) {
      FileInputStream fr = new FileInputStream(frJm);
      FileOutputStream fw = new FileOutputStream(fwJm);

      int c;

      while ((c = fr.read()) != -1)
        fw.write(c);

      fr.close();
      fw.close();
    }
  }
}
