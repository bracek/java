package cz.heroult.pavel.java.book.Kap18.S304;

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

public class IoZnaky2 {
  public static void main(String[] args) throws IOException {
    File frJm = new File("a.txt");
    FileReader fr = new FileReader(frJm);

    FileWriter fw = new FileWriter("b.txt");

    long delka = frJm.length();
    int c;

    for (long i = 0;  i < delka;  i++) {
      c = fr.read();
      fw.write(c);
    }
    fr.close();
    fw.close();
  }
}
