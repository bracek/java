package cz.heroult.pavel.java.book.Kap03.S45;

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

public class ProgramKonci {
  public static void main(String[] args) throws Exception {
    OutputStreamWriter o = new OutputStreamWriter(System.out, "Cp852");
    /* Cp852 je výstupní kódování èeštiny v DOSovém okénku */
    PrintWriter p = new PrintWriter(o);
    p.print("Program kon\u010D\u00ED!\n\007");
    p.close();
  }
}
