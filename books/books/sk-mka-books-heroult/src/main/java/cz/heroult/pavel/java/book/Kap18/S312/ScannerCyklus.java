package cz.heroult.pavel.java.book.Kap18.S312;

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

import java.util.Scanner;
import java.util.Locale;
import java.io.File;
import java.io.IOException;

public class ScannerCyklus {
  public static void main(String[] args) throws IOException {
    Scanner sc = new Scanner(new File("buf.txt"));
    int suma = 0;
    while (sc.hasNextInt() == true) {
      suma += sc.nextInt();
    }
    System.out.println("suma = " + suma); 
  }
}
