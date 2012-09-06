package cz.heroult.pavel.java.book.Kap18.S310;

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

public class FormatovaniVystupu {
  public static void main(String[] argv) throws IOException {
    FileWriter fwForm = new FileWriter("form.txt");
    PrintWriter form = new PrintWriter(fwForm);
    FileWriter fwNeform = new FileWriter("neform.txt");

    for (int i = 65;  i < 75;  i++) {
      System.out.print(i + " ");
      form.print(i + " ");
//      form.format("%d ", i);
      fwNeform.write(i);
    }

    form.close();
    fwNeform.close();
  }
}
