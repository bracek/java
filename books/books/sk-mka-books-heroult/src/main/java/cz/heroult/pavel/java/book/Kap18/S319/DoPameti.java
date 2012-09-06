package cz.heroult.pavel.java.book.Kap18.S319;

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

public class DoPameti {
  public static void main(String[] args) throws IOException {
    StringWriter sProud = new StringWriter();
    PrintWriter form = new PrintWriter(sProud);

    for (int i = 1;  i <= 3;  i++) {
      form.print(i + ". ahoj " + (4 - i) + "\n");
      System.out.println(sProud);
    }
    form.close();
  }
}


