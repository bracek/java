package cz.heroult.pavel.java.book.Kap18.S309;

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

public class VraceniZnaku {
  public static void main(String[] argv) throws IOException {
    FileReader fr = new FileReader("a.txt");
    PushbackReader in = new PushbackReader(fr);
    int c;

    c = in.read();
    System.out.print((char) c);
    in.unread(c);
    c = in.read();
    System.out.print((char) c);

    in.close();
  }
}
