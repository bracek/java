package cz.heroult.pavel.java.book.Kap09.S186;

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

public class Charact2 {
  public static void main(String[] args) throws Exception {
    char c, d = 'A';

    c = Character.toLowerCase(d);
    System.out.println("c = "+ c +" d = "+ d);   // c = a d = A
    d = Character.toUpperCase('\u00FD');         // '\u00FD' je 'ý' 
    System.out.println("d = " + d);              // d = Ý

    OutputStreamWriter o = new OutputStreamWriter(System.out, "Cp852");
    /* Cp852 je výstupní kódování èeštiny v DOSovém okénku */
    PrintWriter p = new PrintWriter(o);
    p.print("d po zmìnì èeštiny = " + d);
    p.close();
  }
}
