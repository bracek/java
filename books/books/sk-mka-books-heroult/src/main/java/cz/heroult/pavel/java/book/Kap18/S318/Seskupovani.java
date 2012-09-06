package cz.heroult.pavel.java.book.Kap18.S318;

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

public class Seskupovani {
  public static void main(String[] argv) throws IOException {

    FileWriter fBuf = new FileWriter("buf.txt");

// nebuferovane - zakomentovat dve radky
//    BufferedWriter bBuf = new BufferedWriter(fBuf);
//    PrintWriter pBuf = new PrintWriter(bBuf);

// buferovane - zakomentovat jednu radku
    PrintWriter pBuf = new PrintWriter(fBuf);

/* // bezny zapis pomoci jedne referencni promenne
    PrintWriter pBuf = new PrintWriter(
                       new BufferedWriter(
                       new FileWriter("buf.txt")));
*/
    
    long z = System.currentTimeMillis();

    for (int i = 0;  i < 500000;  i++)
      pBuf.println(i);

    long k = System.currentTimeMillis();
    System.out.println((k - z));

    pBuf.close();
  }
}


