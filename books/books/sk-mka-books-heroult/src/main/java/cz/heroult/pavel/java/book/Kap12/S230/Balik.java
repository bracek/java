package cz.heroult.pavel.java.book.Kap12.S230;

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

import java.io.FileReader;
import java.io.LineNumberReader;
import java.io.IOException;

public class Balik {
  public static void main(String[] args)
                                 throws IOException {
    FileReader f = new FileReader("a.txt");
    LineNumberReader lf = new LineNumberReader(f);
    System.out.println(lf.readLine());
    f.close();
  }
}
