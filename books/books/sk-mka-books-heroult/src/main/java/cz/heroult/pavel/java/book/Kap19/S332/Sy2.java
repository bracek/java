package cz.heroult.pavel.java.book.Kap19.S332;

/////////////////////////////////////////////////////////////////
//                                                             //
// Tento zdrojov� k�d je sou��st� distribuce bal�ku program�,  //
//     poskytovan�ch jako dopl�uj�c� informace ke knize        //
//                                                             //
//                  U�ebnice jazyka Java                       //
//                                                             //
//     P�e�t�te si, pros�m, d�kladn� upozorn�n� v souboru      // 
//                       CTI_ME.TXT                            //
//        kter� je ned�lnou sou��st� t�to distribuce           //
//                                                             //
//                 (c) Pavel Herout, 2000                      // 
//                                                             //
/////////////////////////////////////////////////////////////////

import java.io.*;

public class Sy2 {
  public static void main(String[] args) throws IOException {
    FileOutputStream fout = new FileOutputStream("vypisy.txt");
    PrintStream pout = new PrintStream(fout);
    FileOutputStream ferr = new FileOutputStream("chyby.log");
    PrintStream perr = new PrintStream(ferr);

    System.out.println("Out pred presmerovanim");
    System.err.println("Err pred presmerovanim");
    System.setOut(pout);
    System.setErr(perr);
//    System.out = pout;  // nelze
    System.out.println("Out PO presmerovani");
    System.err.println("Err PO presmerovani");

    fout.flush();

    // vyhozeni vyjimky, ktera se zapise do souboru chyby.log
    throw new IOException();
  }
}
