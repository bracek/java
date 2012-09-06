package cz.heroult.pavel.java.book.Kap18.S308;

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

public class PoRadcich {
  public static void main(String[] argv) throws IOException {
    FileReader fr = new FileReader("a.txt");
    BufferedReader in = new BufferedReader(fr);
    FileWriter fw = new FileWriter("b.txt");
    BufferedWriter out = new BufferedWriter(fw);
    String radka;

    while((radka = in.readLine()) != null) {
      System.out.println(radka);
      out.write(radka);
      out.newLine();
    }

    fr.close();
    out.close();
  }
}
